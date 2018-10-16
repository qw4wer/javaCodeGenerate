package tk.qw4wer.codeGenerate.pojo;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class Pojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pojoName;
	
	private String remark;
	
	private List<Field> fields;
	
	private String groupId;
	
	private String artifactId;
	
	private String srcTableName;

	public String getSrcTableName() {
		return srcTableName;
	}

	public void setSrcTableName(String srcTableName) {
		this.srcTableName = srcTableName;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	
	public String getDirPath(){
		
		return MessageFormat.format("{0}{1}{2}",this.groupId.replace(".", File.separatorChar+""),File.separatorChar,this.artifactId);
	}
	
}
