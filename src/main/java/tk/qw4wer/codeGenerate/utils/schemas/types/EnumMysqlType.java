package tk.qw4wer.codeGenerate.utils.schemas.types;

import java.math.BigDecimal;
import java.util.Date;

public enum EnumMysqlType {
	VARCHAR("String", String.class),

	CHAR("String", String.class),

	INT("Integer", Integer.class),

	DATETIME("Date", Date.class),

	DATE("Date", Date.class),

	DECIMAL("BigDecimal", BigDecimal.class),

	FLOAT("Float", Float.class),
	
	MEDIUMBLOB("String", String.class),
	
	TEXT("String", String.class),

	;

	public static EnumMysqlType getTypeByName(String name) {
		EnumMysqlType[] types = values();
		for (EnumMysqlType type : types) {
			if (type.name().equalsIgnoreCase(name)) {
				return type;
			}
		}
		return null;
	}

	public static String getPackagePath(String name) {
		Class<?> cls = getTypeByName(name).getCls();

		if (cls.getPackage().getName().startsWith("java.lang"))
			return "";
		return cls.getName();
	}

	public static String getTypeJavaNameByJdbcName(String name) {
		return getTypeByName(name).getTypeName();

	}

	private String typeName;

	private Class<?> cls;

	private EnumMysqlType(String typeName, Class<?> cls) {
		this.typeName = typeName;
		this.cls = cls;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

}
