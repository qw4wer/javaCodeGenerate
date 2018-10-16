package tk.qw4wer.codeGenerate.utils.beetls.functions;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Service;

import tk.qw4wer.codeGenerate.utils.CommonUtils;
/**
 *  自定义方法:首字母大写
 * @author qw4wer
 *
 */
@Service
public class UpperCaseFristChar implements Function {

	public Object call(Object[] objs, Context context) {
		Object o  = objs[0];
		if(null !=o){
			try {
				String str = o.toString();
				str = CommonUtils.upperCaseFristChar(str);
//				context.byteWriter.writeString(str);
				return str;
			} catch (Exception e) {
				e.printStackTrace();
				throw new  RuntimeException(e);
			}
		}
		return "";
	}
	
	
	
}