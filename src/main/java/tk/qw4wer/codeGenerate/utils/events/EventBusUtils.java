package tk.qw4wer.codeGenerate.utils.events;

import com.google.common.eventbus.EventBus;
import tk.qw4wer.codeGenerate.utils.logs.LogUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class EventBusUtils {

    private static EventBus eventBus = new EventBus((exception, context) -> {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw, true));
        LogUtils.log2Area(sw.toString());
    });

    public static void post(Object event) {
        eventBus.post(event);
    }

    public static void register(Object handler) {
        eventBus.register(handler);
    }

    public static void unregister(Object handler) {
        eventBus.unregister(handler);
    }
}
