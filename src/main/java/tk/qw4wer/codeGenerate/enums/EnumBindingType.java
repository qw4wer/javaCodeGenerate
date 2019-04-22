package tk.qw4wer.codeGenerate.enums;

/**
 * 生成的路径
 */
public enum EnumBindingType {

    POJO("pojo", "pojo/pojo.template", "{0}.java"),
    POJOCOND("pojo", "pojo/pojoCond.template", "{0}Cond.java"),
    MAPPER("mappers", "mappers/Mapper.template", "{0}Mapper.xml"),
    IMAPPER("", "IMapper.template", "I{0}Mapper.java"),
    ISERVICES("services", "services/IServices.template", "I{0}Services.java"),
    SERVICES("services\\impl", "services/impl/Services.template", "{0}Services.java"),
    ;

    private String dir;

    private String template;


    private String formatting;


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


    public String getFormatting() {
        return formatting;
    }

    public void setFormatting(String formatting) {
        this.formatting = formatting;
    }

    private EnumBindingType(String dir, String template,
                            String formatting) {
        this.dir = dir;
        this.template = template;
        this.formatting = formatting;
    }

}
