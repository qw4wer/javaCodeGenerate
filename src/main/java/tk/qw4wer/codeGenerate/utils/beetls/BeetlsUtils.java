package tk.qw4wer.codeGenerate.utils.beetls;

import java.io.File;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import tk.qw4wer.codeGenerate.enums.EnumBindingType;
import tk.qw4wer.codeGenerate.pojo.Pojo;
import tk.qw4wer.codeGenerate.utils.builds.BuildUtils;

public class BeetlsUtils {

	public static void bindingToFile(Pojo pojo, String path, EnumBindingType bindingType) throws Exception {
		// path =
		// BuildUtils.mkdirs(path+File.separatorChar+bindingType.getDir());
		path = BuildUtils.mkdirs(MessageFormat.format("{0}{1}{2}{3}{4}", path, File.separatorChar, pojo.getDirPath(), File.separatorChar, bindingType.getDir()));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("pojo", pojo);
		FileWriter fileWriter = new FileWriter(MessageFormat.format("{0}{1}{2}", path, File.separatorChar, MessageFormat.format(bindingType.getFormatting(), pojo.getPojoName())));

		fileWriter.write(bindingToStr(bindingType.getTemplate(), data));

		fileWriter.flush();

		fileWriter.close();
	}

	public static String bindingToStr(String template, Map<?, ?> data) {

		GroupTemplate gt = BeetlGroupUtilConfiguration.getGt();
		Template t = gt.getTemplate(template);
		t.binding(data);
		return t.render();
	}

}
