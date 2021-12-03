package tk.qw4wer.codeGenerate.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class Events {

    private String url;

    private String port;
    @JSONField(serialize = false)
    private String database;

    private String user;

    private String pwd;

    private String groupId;

    private String artifactId;
    @JSONField(serialize = false)
    private String selectDb;
    @JSONField(serialize = false)
    private List<String> selectTable;

    private String dirPath;
    @JSONField(serialize = false)
    private String code;
    @JSONField(serialize = false)
    private boolean emptyDir;

    @JSONField(serialize = false)
    private boolean isLombok;

}
