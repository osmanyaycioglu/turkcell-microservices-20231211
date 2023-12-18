package training.microservices.mscommon.error;

public class ClientCallException extends RuntimeException {
    private final ErrorObj errorObj;

    public ClientCallException(final String message,
                               final ErrorObj errorObjParam) {
        super(message);
        errorObj = errorObjParam;
    }

    public ErrorObj getErrorObj() {
        return errorObj;
    }
}
