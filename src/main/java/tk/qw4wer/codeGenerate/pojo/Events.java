package tk.qw4wer.codeGenerate.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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

    public String getSelectDb() {
        return selectDb;
    }

    public void setSelectDb(String selectDb) {
        this.selectDb = selectDb;
    }

    public List<String> getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(List<String> selectTable) {
        this.selectTable = selectTable;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEmptyDir() {
        return emptyDir;
    }

    public void setEmptyDir(boolean emptyDir) {
        this.emptyDir = emptyDir;
    }
}
