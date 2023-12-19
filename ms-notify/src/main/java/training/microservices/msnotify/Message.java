package training.microservices.msnotify;

import lombok.Data;

@Data
public class Message {
    private String msg;
    private String dest;
}
