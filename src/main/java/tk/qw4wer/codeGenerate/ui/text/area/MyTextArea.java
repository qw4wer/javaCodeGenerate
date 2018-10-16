package tk.qw4wer.codeGenerate.ui.text.area;

import javax.swing.JTextArea;

import tk.qw4wer.codeGenerate.pojo.Event;

import com.google.common.eventbus.Subscribe;

public class MyTextArea extends JTextArea {

	private String controlId;

	public MyTextArea(String controlId) {
		super(10, 10);
		this.controlId = controlId;
		setEditable(false);
	}

	@Subscribe
	public void sub(Event<String> event) {

		if (this.controlId.equals(event.getControlId())) {
			this.append(event.getData()+"\n");
		}

	}

}
