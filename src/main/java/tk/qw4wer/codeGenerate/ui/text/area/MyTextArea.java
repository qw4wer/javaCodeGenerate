package tk.qw4wer.codeGenerate.ui.text.area;

import com.google.common.eventbus.Subscribe;
import lombok.extern.log4j.Log4j;
import tk.qw4wer.codeGenerate.pojo.Event;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;

import javax.swing.*;

@Log4j
public class MyTextArea extends JTextArea {

    private String controlId;

    public MyTextArea(String controlId) {
        super(10, 10);
        this.controlId = controlId;
        setEditable(false);
		EventBusUtils.register(this);
    }

    @Subscribe
    public void sub(Event<String> event) {
        if (this.controlId.equals(event.getControlId())) {

            this.append(event.getData() + "\n");
        }

    }

}
