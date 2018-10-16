package tk.qw4wer.codeGenerate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import tk.qw4wer.codeGenerate.pojo.Field;

public class T1 {
	public static void main(String[] args) throws IOException {
		List<Field> list = new ArrayList<Field>();
		Field pojo = new Field();
		pojo.setAccess("private");
		pojo.setFiledName("id");
		pojo.setRemark("主键id");
		pojo.setType("int");
		list.add(pojo);
		pojo = new Field();
		pojo.setAccess("private");
		pojo.setFiledName("name");
		pojo.setRemark("用户名");
		pojo.setType("String");
		list.add(pojo);
		
		String root = System.getProperty("user.dir") + File.separator
				+ "template";
		FileResourceLoader resourceLoader = new FileResourceLoader(root,
				"utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		cfg.setCharset("UTF-8");
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		gt.registerFunction("fn.upperCaseFristChar", new My());
		Template t = gt.getTemplate("/pojo.txt");
		t.binding("list", list);
		t.binding("groupId", "tk.qw4wer");
		t.binding("artifactId", "weixin");
		t.binding("pojoName", "User");
		String str = t.render();
		System.out.println(str);

	}
}
