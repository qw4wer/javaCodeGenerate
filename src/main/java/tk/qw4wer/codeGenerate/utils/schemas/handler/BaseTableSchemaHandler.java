package tk.qw4wer.codeGenerate.utils.schemas.handler;

import java.util.List;
import java.util.Map;

import tk.qw4wer.codeGenerate.pojo.Field;

public interface BaseTableSchemaHandler {

	public String getTableRemark(String dbName, String tableName);

	public List<Field> getTableField(String dbName, String tableName);

	public String getTableList(String dbName);

	public List<String> getDb();

	public List<String> getTables(String dbName);
}
