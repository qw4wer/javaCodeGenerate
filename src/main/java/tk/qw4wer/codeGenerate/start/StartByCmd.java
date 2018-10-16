package tk.qw4wer.codeGenerate.start;

import java.text.MessageFormat;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.qw4wer.codeGenerate.enums.EnumBindingType;
import tk.qw4wer.codeGenerate.pojo.Pojo;
import tk.qw4wer.codeGenerate.utils.beetls.BeetlGroupUtilConfiguration;
import tk.qw4wer.codeGenerate.utils.beetls.BeetlsUtils;
import tk.qw4wer.codeGenerate.utils.schemas.SchemaUtils;

public class StartByCmd {

	public static void main(String[] args) {
		try {
			
			AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			Pojo pojo = SchemaUtils.getTable2Pojo("draw","user");
			
			pojo.setGroupId("com.ihk");
			pojo.setArtifactId("draw");
			
//			BeetlsUtils.bindingToFile( pojo, "C:\\me\\tmp", EnumBindingType.POJO);
			
			Runtime.getRuntime().exec("cmd /c RD /S /q C:\\me\\tmp");
			
			for(EnumBindingType bindingType : EnumBindingType.values()){
				BeetlsUtils.bindingToFile(pojo, "C:\\me\\tmp", bindingType);
				
			}
			Runtime.getRuntime().exec("cmd /c start explorer C:\\me\\tmp");
			
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void print(String template,Map<?,?> data){
		GroupTemplate gt = BeetlGroupUtilConfiguration.getGt();
		Template t = gt.getTemplate(template);
		t.binding(data);
		String str = t.render();
		System.out.println(MessageFormat.format("============={0}=============", template));
		System.out.println(str);
		
	}
	
}
