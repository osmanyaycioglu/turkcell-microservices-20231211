package training.microservices.mscommon.error;

public class RemoteCallException extends RuntimeException {
    private final ErrorObj errorObj;

    public RemoteCallException(final ErrorObj errorObjParam) {
        errorObj = errorObjParam;
    }

    public ErrorObj getErrorObj() {
        return errorObj;
    }
}
