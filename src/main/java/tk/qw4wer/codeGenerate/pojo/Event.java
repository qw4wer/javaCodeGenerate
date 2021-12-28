package tk.qw4wer.codeGenerate.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Event<T> {

    private String controlId;

    private String eventType;

    private T data;


}
