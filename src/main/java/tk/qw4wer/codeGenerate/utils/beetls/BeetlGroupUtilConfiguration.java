package tk.qw4wer.codeGenerate.utils.beetls;

import java.io.IOException;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.Function;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;

public class BeetlGroupUtilConfiguration {

	private static String templateDir;
	
	private static GroupTemplate gt;
	
	private static Map<String,Class<Function>> fns;
	
	private static String prefix;
	
	public static void init() {
		String root =  BeetlGroupUtilConfiguration.class.getResource("/").getFile();
		
		
		FileResourceLoader resourceLoader = new FileResourceLoader(root + templateDir,"utf-8");
		Configuration cfg;
		try {
			cfg = Configuration.defaultConfiguration();
			cfg.setCharset("UTF-8");
			gt = new GroupTemplate(resourceLoader, cfg);
			registerFn();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void registerFn(){
		for (String fn : fns.keySet()) {
			try {
				gt.registerFunction(prefix==null?fn:prefix+"."+fn, fns.get(fn).newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		BeetlGroupUtilConfiguration.templateDir = templateDir;
	}

	public static GroupTemplate getGt() {
		return gt;
	}

	public static Map<String, Class<Function>> getFns() {
		return fns;
	}

	public void setFns(Map<String, Class<Function>> fns) {
		BeetlGroupUtilConfiguration.fns = fns;
	}

	public static String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		BeetlGroupUtilConfiguration.prefix = prefix;
	}

	
}
