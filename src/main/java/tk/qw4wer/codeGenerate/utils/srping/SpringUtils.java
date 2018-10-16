package tk.qw4wer.codeGenerate.utils.srping;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ScopeMetadata;

import tk.qw4wer.codeGenerate.utils.DbUtilsTemplate;
import tk.qw4wer.codeGenerate.utils.schemas.SchemaUtils;
import tk.qw4wer.codeGenerate.utils.schemas.handler.MysqlTableSchemaHandler;

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
