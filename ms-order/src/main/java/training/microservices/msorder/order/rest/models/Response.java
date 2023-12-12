package training.microservices.msorder.order.rest.models;

import lombok.Data;
// Don't do this
@Data
public class Response<T> {

    private boolean errorOccurred;
    private String  errorMsg;
    private Integer errorCode;
    private T response;


}
