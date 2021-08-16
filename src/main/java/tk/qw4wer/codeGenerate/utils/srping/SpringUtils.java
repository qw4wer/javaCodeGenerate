package tk.qw4wer.codeGenerate.utils.srping;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import tk.qw4wer.codeGenerate.utils.DbUtilsTemplate;
import tk.qw4wer.codeGenerate.utils.schemas.SchemaUtils;
import tk.qw4wer.codeGenerate.utils.schemas.handler.MysqlTableSchemaHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpringUtils {

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

	public static void setContext(ConfigurableApplicationContext context) {
		SpringUtils.context = context;
	}

	public static void registerBean(String id, Class<?> cls, Map<String, Object> par) {
		DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) context.getBeanFactory();
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
		for (String key : par.keySet()) {
			builder.addConstructorArgValue(par.get(key));
		}
		dbf.registerBeanDefinition(id, builder.getBeanDefinition());
	}

	public static void registerAndRefreshBean(String id, Class<?> cls, Map<String, Object> par,String propertyReference) {
		DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) context.getBeanFactory();
		dbf.destroySingleton(id);
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
		for (String key : par.keySet()) {
			builder.addConstructorArgValue(par.get(key));
		}
		builder.addPropertyReference(propertyReference, propertyReference);

		dbf.registerBeanDefinition(id, builder.getBeanDefinition());


	}

	public static void registerBeanAndInit(String id, Class<?> cls, Map<String, Object> par) {
		DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) context.getBeanFactory();
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
		for (String key : par.keySet()) {
			builder.addConstructorArgValue(par.get(key));
		}
		dbf.registerBeanDefinition(id, builder.getBeanDefinition());

		context.getBean(cls);
	}

	public static void init() {
		SpringUtils.registerBean("dbUtilsTemplate", DbUtilsTemplate.class, new LinkedHashMap<String, Object>());

		SpringUtils.registerBean("tableSchemaHandler", MysqlTableSchemaHandler.class, new LinkedHashMap<String, Object>());

		SpringUtils.registerBeanAndInit("schemaUtils", SchemaUtils.class, new LinkedHashMap<String, Object>());
	}
}
