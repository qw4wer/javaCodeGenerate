package tk.qw4wer.codeGenerate.pojo;

import lombok.Data;
import tk.qw4wer.codeGenerate.utils.CommonUtils;

import java.io.Serializable;

@Data
public class Field implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String filedName;

    private String access;

    private String remark;

    private String type;

    private String packagePath;

    public String getEntityFiledName() {
        return CommonUtils.firstLowerUnderUpper(this.filedName);
    }

}
