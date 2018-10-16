package tk.qw4wer.codeGenerate.utils.beetls.functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.beetl.core.Context;
import org.beetl.core.Function;

import tk.qw4wer.codeGenerate.pojo.Field;

public class ImportType implements Function {

	@SuppressWarnings("unchecked")
	@Override
	public Object call(Object[] paras, Context ctx) {
		List<Field> fields = (List<Field>) paras[0];
		try {
			ctx.byteWriter.writeString(getImports(fields));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getImports(List<Field> fields) {
		List<String> has = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();

		for (Field field : fields) {
			if (!has.contains(field.getPackagePath())) {
				has.add(field.getPackagePath());
				sb.append("import " + field.getPackagePath() + ";\n");
			}
		}
		return sb.toString();
	}
}
