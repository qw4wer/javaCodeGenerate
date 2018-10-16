package tk.qw4wer.codeGenerate.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

import tk.qw4wer.codeGenerate.pojo.Event;
import tk.qw4wer.codeGenerate.pojo.Events;

@Component
public class EventCentre {

	private static Events events;

	public static Events getEvent() {
		return events;
	}

	@Autowired
	public void setEvent(Events event) {
		EventCentre.events = event;
	}

	@Subscribe
	public void sub(Event<?> event) {
		System.out.println(event);
	}

}
