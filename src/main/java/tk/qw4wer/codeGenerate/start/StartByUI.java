package tk.qw4wer.codeGenerate.start;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.qw4wer.codeGenerate.ui.frame.MainFrame;
import tk.qw4wer.codeGenerate.utils.DbUtilsTemplate;
import tk.qw4wer.codeGenerate.utils.MyDataSource;
import tk.qw4wer.codeGenerate.utils.schemas.SchemaUtils;
import tk.qw4wer.codeGenerate.utils.schemas.handler.MysqlTableSchemaHandler;
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
