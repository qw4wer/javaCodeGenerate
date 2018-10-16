package tk.qw4wer.codeGenerate.utils;

import java.text.MessageFormat;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyDataSource extends BasicDataSource {
	private static final Log LOG = LogFactory.getLog(MyDataSource.class);

	private String serverHost;

	private String port;

	private String databaseName;
	
	private String user;
	
	private String pwd;

	public MyDataSource() {

	}
	
	public MyDataSource(String serverHost, String port, String databaseName, String user, String pwd) {
		
		String baseUrl = "jdbc:mysql://{0}?useUnicode=true&amp;characterEncoding=UTF-8&amp;charset=UTF-8&amp;zeroDateTimeBehavior=convertToNull&amp;transformedBitIsBoolean=true";

		String u = "{0}";

		Integer i = 1;

		if (!CommonUtils.isStrEmpty(port)) {
			u += ":{" + i + "}";
			i++;
		}

		if (!CommonUtils.isStrEmpty(databaseName)) {
			u += "/{" + i + "}";
			i++;
		}

		u = MessageFormat.format(u, serverHost, port, databaseName);

		baseUrl = MessageFormat.format(baseUrl, u);

		setUrl(baseUrl);
		setUsername(user);
		setPassword(pwd);
		setDriverClassName("com.mysql.jdbc.Driver");
		setTestWhileIdle(true);
		setValidationQuery("SELECT 1");
		setValidationQueryTimeout(10000);
		LOG.debug("url:/t" + url);
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
