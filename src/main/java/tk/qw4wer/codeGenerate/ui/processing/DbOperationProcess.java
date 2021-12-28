package tk.qw4wer.codeGenerate.ui.processing;

import tk.qw4wer.codeGenerate.enums.EnumBindingType;
import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.pojo.Event;
import tk.qw4wer.codeGenerate.pojo.Events;
import tk.qw4wer.codeGenerate.pojo.Pojo;
import tk.qw4wer.codeGenerate.utils.DbUtilsTemplate;
import tk.qw4wer.codeGenerate.utils.MyDataSource;
import tk.qw4wer.codeGenerate.utils.beetls.BeetlsUtils;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;
import tk.qw4wer.codeGenerate.utils.schemas.SchemaUtils;
import tk.qw4wer.codeGenerate.utils.srping.SpringUtils;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbOperationProcess {

    /**
     * 注册数据源
     */
    public static void setDbSources() {
        Events events = EventCentre.getEvent();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("serverHost", events.getUrl());
        map.put("port", events.getPort());
        map.put("databaseName", "");
        map.put("user", events.getUser());
        map.put("pwd", events.getPwd());
        SpringUtils.registerBean("dataSource", MyDataSource.class, map);
        SpringUtils.registerAndRefreshBean("dbUtilsTemplate", DbUtilsTemplate.class, new LinkedHashMap<String, Object>(), "dataSource");
    }

    /**
     * 获取db列表
     */
    public static void refurbishDbTable() {
        List<String> dbs = SchemaUtils.getDb();
        Event<List<String>> event = Event.<List<String>>builder()
                .controlId("db")
                .data(dbs)
                .build();

        EventBusUtils.post(event);
    }

    public static void refurbishAndSearchTableTable() {
        List<String> tables = SchemaUtils.getTables(EventCentre.getEvent().getSelectDb());
        String code = EventCentre.getEvent().getCode();
        List<String> collect = tables.stream().filter(table -> {
            if (table.startsWith(code)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Event<List<String>> event = Event.<List<String>>builder()
                .controlId("table")
                .data(collect)
                .build();
        EventBusUtils.post(event);
    }

    public static void refurbishTableTable() {
        List<String> tables = SchemaUtils.getTables(EventCentre.getEvent().getSelectDb());

        Event<List<String>> event = Event.<List<String>>builder()
                .controlId("table")
                .data(tables)
                .build();

        EventBusUtils.post(event);
    }

    public static void build2File() throws Exception {
        Events event = EventCentre.getEvent();
        Pojo pojo = null;
        try {

            if (event.isEmptyDir()) {
                Runtime.getRuntime().exec(MessageFormat.format("cmd /c del /f /s /q {0}\\*.*", event.getDirPath())).waitFor();
                Runtime.getRuntime().exec(MessageFormat.format("cmd /c RD /S /q {0}", event.getDirPath())).waitFor();
            }
            for (String table : event.getSelectTable()) {
                pojo = SchemaUtils.getTable2Pojo(event.getSelectDb(), table);
                pojo.setArtifactId(event.getArtifactId())
                        .setGroupId(event.getGroupId())
                        .setLombok(event.isLombok())
                ;

                for (EnumBindingType bindingType : EnumBindingType.values()) {
                    BeetlsUtils.bindingToFile(pojo, event.getDirPath(), bindingType);

                }
            }
            Runtime.getRuntime().exec(MessageFormat.format("cmd /c start explorer {0}", event.getDirPath()));
        } catch (Exception e) {
            throw e;
        }
    }
}
