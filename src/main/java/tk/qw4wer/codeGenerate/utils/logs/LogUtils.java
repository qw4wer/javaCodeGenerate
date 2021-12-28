package tk.qw4wer.codeGenerate.utils.logs;

import tk.qw4wer.codeGenerate.pojo.Event;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;

public class LogUtils {

    public static void log2Area(String log) {
        Event<String> event = Event.<String>builder()
                .controlId("log")
                .data(log)
                .build();

        EventBusUtils.post(event);

    }

}
