package tk.qw4wer.codeGenerate.utils.builds;

import java.io.File;

public class BuildUtils {

	/**
	 * 新建目录
	 * @param path
	 */
	public static String mkdirs(String path){
		File file = new File(path);
		file.mkdirs();
		return file.getAbsolutePath();
	}
	
	
	
	
}
