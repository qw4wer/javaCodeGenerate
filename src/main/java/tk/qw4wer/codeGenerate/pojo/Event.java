package tk.qw4wer.codeGenerate.pojo;

public class Event<T> {

	private String controlId;

	private String eventType;

	private T data;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Event [eventType=" + eventType + ", data=" + data + "]";
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

}
