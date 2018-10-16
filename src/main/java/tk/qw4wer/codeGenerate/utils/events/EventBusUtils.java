package tk.qw4wer.codeGenerate.utils.events;

import com.google.common.eventbus.EventBus;

public class EventBusUtils {

	private static EventBus eventBus = new EventBus();

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
