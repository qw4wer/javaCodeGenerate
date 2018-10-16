package tk.qw4wer.codeGenerate.pojo;

import java.io.Serializable;

public class Field implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String filedName;
	
	private String access;
	
	private String remark;
	
	private String type;
	
	private String packagePath;

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getAccess() {
		return access== null ? "":access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	
}
