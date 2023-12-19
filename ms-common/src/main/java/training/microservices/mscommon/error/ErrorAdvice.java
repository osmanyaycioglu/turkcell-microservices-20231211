package training.microservices.mscommon.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handleExp(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withErrorDesc(exp.getMessage())
                       .withErrorCode(1022)
                       .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleExp(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorDesc(exp.getMessage())
                       .withErrorCode(1023)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleExp(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorDesc("Validation error")
                       .withErrorCode(1024)
                       .withSubErrors(exp.getAllErrors()
                                         .stream()
                                         .map(e -> ErrorObj.builder()
                                                           .withErrorDesc(e.toString())
                                                           .withErrorCode(1025)
                                                           .build())
                                         .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(RemoteCallException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handleExp(RemoteCallException exp) {
        return ErrorObj.builder()
                       .withErrorDesc("Error on rest client")
                       .withErrorCode(3066)
                       .withSubErrors(Collections.singletonList(exp.getErrorObj()))
                       .build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleExp(Exception exp) {
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                 .body(ErrorObj.builder()
                                               .withErrorDesc(exp.getMessage())
                                               .withErrorCode(5001)
                                               .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorDesc(exp.getMessage())
                                           .withErrorCode(5000)
                                           .build());
    }


}
