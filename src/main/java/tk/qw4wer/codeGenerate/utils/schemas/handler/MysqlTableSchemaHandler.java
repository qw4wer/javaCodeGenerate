package tk.qw4wer.codeGenerate.utils.schemas.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import tk.qw4wer.codeGenerate.pojo.Field;
import tk.qw4wer.codeGenerate.utils.DbUtilsTemplate;

public class MysqlTableSchemaHandler implements BaseTableSchemaHandler {

	@Autowired(required = false)
	@Qualifier("dbUtilsTemplate")
	DbUtilsTemplate dbUtilsTemplate;

	@Override
	public String getTableRemark(String dbName, String tableName) {
		String sql = "SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
		String remark = dbUtilsTemplate.findFirstCloumn(String.class, sql, new Object[] { dbName, tableName });
		return remark;
	}

	@Override
	public List<Field> getTableField(String dbName, String tableName) {
		String sql = "SELECT c.COLUMN_NAME AS filedName,c.COLUMN_COMMENT AS remark,c.DATA_TYPE AS TYPE FROM information_schema.COLUMNS c WHERE c.TABLE_SCHEMA = ? AND c.TABLE_NAME = ?";
		return dbUtilsTemplate.find(Field.class, sql, new Object[] { dbName, tableName });
	}

	@Override
	public String getTableList(String dbName) {
		return null;
	}

	@Override
	public List<String> getDb() {
		String sql = "select s.SCHEMA_NAME as dbName from information_schema.SCHEMATA s";
		List<String> str = dbUtilsTemplate.get(sql, null);

		return str;

	}

	@Override
	public List<String> getTables(String dbName) {
		String sql = "select t.TABLE_NAME from information_schema.TABLES t where t.TABLE_TYPE = 'BASE TABLE' and t.TABLE_SCHEMA =?";
		List<String> str = dbUtilsTemplate.get(sql, new String[] { dbName });
		return str;
	}

}
