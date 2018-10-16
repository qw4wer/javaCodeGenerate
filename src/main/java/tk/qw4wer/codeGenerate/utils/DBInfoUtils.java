package tk.qw4wer.codeGenerate.utils;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * DB信息
 * 
 * @author qw4wer
 * 
 */
public class DBInfoUtils {

	private static String serverHost;

	private static String port;

	private static String databaseName;

	private static String userName;

	private static String passWord;

	static {
		// 环境变量，用于appfog
		String databaseInfo = java.lang.System.getenv("VCAP_SERVICES");

		if (databaseInfo != null) {
			try {
				JSONObject jsonObject = (JSONObject) JSONObject.toJSON(databaseInfo);
				JSONObject mysqlObj = jsonObject.getJSONArray("mysql-5.1").getJSONObject(0);
				JSONObject credentials = mysqlObj.getJSONObject("credentials");

				serverHost = credentials.getString("hostname");
				port = String.valueOf(credentials.getIntValue("port"));
				databaseName = credentials.getString("name");
				userName = credentials.getString("username");
				passWord = credentials.getString("password");

			} catch (JSONException ex) {
				Logger.getLogger(DBInfoUtils.class.getName()).error(ex);
			}
		} else {
			serverHost = "172.16.8.34";
			port = "3306";
			databaseName = "crm";
			userName = "webuser";
			passWord = "kik";
		}
	}

	public static String getServerHost() {
		return serverHost;
	}

	public static void setServerHost(String serverHost) {
		DBInfoUtils.serverHost = serverHost;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		DBInfoUtils.port = port;
	}

	public static String getDatabaseName() {
		return databaseName;
	}

	public static void setDatabaseName(String databaseName) {
		DBInfoUtils.databaseName = databaseName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		DBInfoUtils.userName = userName;
	}

	public static String getPassWord() {
		return passWord;
	}

	public static void setPassWord(String passWord) {
		DBInfoUtils.passWord = passWord;
	}
}
