package tk.qw4wer.codeGenerate.start;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.qw4wer.codeGenerate.ui.frame.MainFrame;
import tk.qw4wer.codeGenerate.utils.srping.SpringUtils;

public class StartByUI {

    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringUtils.setContext(context);

        SpringUtils.init();

        MainFrame frame = new MainFrame();

        // context.close();
    }

}
