package tk.qw4wer.codeGenerate.utils.beetls.functions;

import org.beetl.core.Context;
import org.beetl.core.Function;

import tk.qw4wer.codeGenerate.utils.CommonUtils;

/**
 * 
 * 自定义方法:去除下划线并将后面的字母大写，形成驼峰
 * @author qw4wer
 *
 */
public class FirstLowerUnderUpper implements Function {

	@Override
	public Object call(Object[] paras, Context ctx) {
		String filedName = paras[0].toString();
		
		try {
			String str = CommonUtils.firstLowerUnderUpper(filedName);
//			ctx.byteWriter.writeString(str);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			throw new  RuntimeException(e);
		}
	}

	
	
}
