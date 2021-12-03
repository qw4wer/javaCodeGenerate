package tk.qw4wer.codeGenerate.utils.schemas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import tk.qw4wer.codeGenerate.pojo.Field;
import tk.qw4wer.codeGenerate.pojo.Pojo;
import tk.qw4wer.codeGenerate.utils.CommonUtils;
import tk.qw4wer.codeGenerate.utils.schemas.handler.BaseTableSchemaHandler;
import tk.qw4wer.codeGenerate.utils.schemas.types.EnumMysqlType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchemaUtils {

	private static BaseTableSchemaHandler baseTableSchemaHandler;

	public SchemaUtils() {
		System.out.println();
	}

	/**
	 * 获取表格的备注
	 * 
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	public static String getTableRemark(String dbName, String tableName) {
		return baseTableSchemaHandler.getTableRemark(dbName, tableName);
	}

	/**
	 * 获取对应表格的字段，并生成对应的字段
	 * 
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	public static List<Field> getTableFields(String dbName, String tableName) {
		List<Field> fields = baseTableSchemaHandler.getTableField(dbName, tableName);
		for (Field field : fields) {
			field.setPackagePath(EnumMysqlType.getPackagePath(field.getType()));
			field.setType(EnumMysqlType.getTypeJavaNameByJdbcName(field.getType()));
			field.setAccess("private");
		}
		return fields.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * 获取表格信息生成pojo
	 * 
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	public static Pojo getTable2Pojo(String dbName, String tableName) {
		Pojo pojo = Pojo.builder()
				.remark(getTableRemark(dbName, tableName))
				.fields(getTableFields(dbName, tableName))
				.pojoName(CommonUtils.upperCaseFristChar(CommonUtils.firstLowerUnderUpper(tableName)))
				.srcTableName(tableName).build();
		return pojo;
	}

	public static List<String> getDb() {
		return baseTableSchemaHandler.getDb();
	}

	public static List<String> getTables(String dbName) {
		return baseTableSchemaHandler.getTables(dbName);
	}

	public static BaseTableSchemaHandler getBaseTableSchemaHandler() {
		return baseTableSchemaHandler;
	}

	@Autowired(required = false)
	@Lazy
	public void setBaseTableSchemaHandler(BaseTableSchemaHandler baseTableSchemaHandler) {
		SchemaUtils.baseTableSchemaHandler = baseTableSchemaHandler;
	}

}
