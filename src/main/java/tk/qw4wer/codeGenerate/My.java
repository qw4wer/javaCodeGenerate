package tk.qw4wer.codeGenerate;

import java.text.MessageFormat;

import org.beetl.core.Context;
import org.beetl.core.Function;

public class My implements Function {

	public Object call(Object[] objs, Context context) {
		Object o  = objs[0];
		if(null !=o){
			try {
				String str = o.toString();
				context.byteWriter.writeString(upperCaseFristChar(str));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return "";
	}
	
	private String upperCaseFristChar(String str){
		if(str.length() == 1)
			return str.toUpperCase();
		return MessageFormat.format("{0}{1}", str.substring(0,1).toUpperCase(),str.substring(1, str.length()));
	}
	
}