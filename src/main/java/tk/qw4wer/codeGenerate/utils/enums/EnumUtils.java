package tk.qw4wer.codeGenerate.utils.enums;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 枚举类工具类
 * @author qw4wer
 *
 */
public class EnumUtils {


	/**
	 * 将枚举类转换成map
	 * @param ref
	 * @param keyFiled
	 * @param valueFiled
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T,Y,Z> Map<Y,Z> parseEumn2Map(Class<T> ref,String keyFiled,String valueFiled) throws Exception {
		Map<Y, Z> map = new LinkedHashMap<Y, Z>();
		if (ref.isEnum()) {
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				Object key = PropertyUtils.getProperty(t, keyFiled);
				Object value = PropertyUtils.getProperty(t, valueFiled);
				map.put((Y)key, (Z)value);
			}
		}
		return map;
	}

	
	/**
	 * 将需要的枚举类型转换成map
	 * @param ref
	 * @param keyFiled
	 * @param valueFiled
	 * @param has 包含的枚举类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T,Y,Z> Map<Y,Z> parseEnum2Map(Class<T> ref,String keyFiled,String valueFiled,List<T> has) throws Exception{
		Map<Y, Z> map = new LinkedHashMap<Y, Z>();
		if (ref.isEnum()) {
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				if(has.contains(t)){
					Object key = PropertyUtils.getProperty(t, keyFiled);
					Object value = PropertyUtils.getProperty(t, valueFiled);
					map.put((Y)key, (Z)value);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 根据枚举类的key得到相应的枚举类型
	 * @param ref
	 * @param keyFiled
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static <T> T getEnumByKey(Class<T> ref,String keyFiled,String key) throws Exception{
		if(ref.isEnum()){
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				Object k = PropertyUtils.getProperty(t, keyFiled);
				if(key.equals(k.toString()))
					return t;
			}
		}
		return null;
		
	}

}
