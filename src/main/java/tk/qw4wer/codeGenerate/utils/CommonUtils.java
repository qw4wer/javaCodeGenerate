package tk.qw4wer.codeGenerate.utils;

import java.text.MessageFormat;
import java.util.Collection;

public class CommonUtils {

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String upperCaseFristChar(String str) {
		if (str.length() == 1)
			return str.toUpperCase();
		return MessageFormat.format("{0}{1}",
				str.substring(0, 1).toUpperCase(),
				str.substring(1, str.length()));
	}
	
	/**
	 * 首字母小写，下划线后大写
	 * @param str
	 * @return
	 */
	public static String firstLowerUnderUpper(String str){
		String[] strs =  str.split("_");
		if(strs.length ==1)
			return str;
		StringBuffer sb = new StringBuffer(strs[0]);
		for (int i =1;i<strs.length;i++) {
			sb.append(CommonUtils.upperCaseFristChar(strs[i]));
		}
		return sb.toString();
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String lowerCaseFristChar(String str){
		if (str.length() == 1)
			return str.toLowerCase();
		return MessageFormat.format("{0}{1}",
				str.substring(0, 1).toLowerCase(),
				str.substring(1, str.length()));
	}
	
	/**
	 * 判断String是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStrEmpty(String str) {
		// 直接用String的isEmpty()判断null是会出错
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断Collection是否为null或空
	 * @param coll
	 * @return
	 */
	public static boolean isCollectionEmpty(Collection<?> coll){
		
		if(coll == null || coll.size() <= 0){
			
			return true;
		}
		
		return false;
	}
}
