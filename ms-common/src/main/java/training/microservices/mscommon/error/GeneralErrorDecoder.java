package training.microservices.mscommon.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;


public class GeneralErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String sParam,
                            final Response responseParam) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorObj errorObjLoc = objectMapper.readValue(responseParam.body()
                                                                       .asInputStream(),
                                                          ErrorObj.class);
            return new RemoteCallException(errorObjLoc);

        } catch (Exception exp) {
            return new RemoteCallException(ErrorObj.builder()
                                                   .withErrorDesc("Error while deserialize")
                                                   .withErrorCode(2055)
                                                   .build());
        }

    }

}
