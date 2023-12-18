package training.microservices.msorder.resilience;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClientResponseException;
import training.microservices.mscommon.error.ClientCallException;
import training.microservices.mscommon.error.ErrorObj;

import java.io.IOException;
import java.util.function.Predicate;

public class CheckClientExceptions implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof RestClientResponseException) {
            RestClientResponseException exp = (RestClientResponseException) throwableParam;
            ObjectMapper objectMapperLoc = new ObjectMapper();
            try {
                ErrorObj errorObjLoc = objectMapperLoc.readValue(exp.getResponseBodyAsByteArray(),
                                                                 ErrorObj.class);
                Integer errorCodeLoc = errorObjLoc.getErrorCode();
                switch (errorCodeLoc) {
                    case 1024:
                    case 1025:
                        return false;
                    case 1048:
                    case 1049:
                        return true;
                }
            } catch (IOException exParam) {
                return true;
            }
        }
        return true;
    }
}
