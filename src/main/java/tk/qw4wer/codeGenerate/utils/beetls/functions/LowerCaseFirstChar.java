package tk.qw4wer.codeGenerate.utils.beetls.functions;

import org.beetl.core.Context;
import org.beetl.core.Function;

import tk.qw4wer.codeGenerate.utils.CommonUtils;

/**
 * 自定义函数:首字母小写
 * @author qw4wer
 *
 */
public class LowerCaseFirstChar implements Function{

	@Override
	public Object call(Object[] paras, Context ctx) {
		String str = paras[0].toString();
		
		str = CommonUtils.lowerCaseFirstChar(str);
		
		return str;
	}

}
