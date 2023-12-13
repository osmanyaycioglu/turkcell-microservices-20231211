package training.microservices.mscommon.error;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
@Jacksonized
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String errorDesc;
    private Integer errorCode;
}
