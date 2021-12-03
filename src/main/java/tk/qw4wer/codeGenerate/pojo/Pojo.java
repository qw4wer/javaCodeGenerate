package tk.qw4wer.codeGenerate.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
@Builder
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

	private boolean isLombok;

	public String getDirPath(){
		
		return MessageFormat.format("{0}{1}{2}",this.groupId.replace(".", File.separatorChar+""),File.separatorChar,this.artifactId);
	}
	
}
