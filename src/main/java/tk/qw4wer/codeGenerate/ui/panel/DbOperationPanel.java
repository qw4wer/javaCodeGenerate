package tk.qw4wer.codeGenerate.ui.panel;

import org.apache.commons.beanutils.BeanUtils;
import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.pojo.Events;
import tk.qw4wer.codeGenerate.ui.processing.DbOperationProcess;
import tk.qw4wer.codeGenerate.ui.text.area.MyTextArea;
import tk.qw4wer.codeGenerate.utils.CommonUtils;
import tk.qw4wer.codeGenerate.utils.ConfigUtils;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;
import tk.qw4wer.codeGenerate.utils.logs.LogUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class DbOperationPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -5059623930420492350L;

    private JLabel urlLabel = new JLabel("url");
    private JTextField urlText = new JTextField(30);
    private JPanel urlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel userLabel = new JLabel("用户名");
    private JTextField userText = new JTextField(30);
    private JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel passLabel = new JLabel("密码");
    private JTextField passText = new JTextField(30);
    private JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JPanel groupIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel groupIdLabel = new JLabel("groupId");
    private JTextField groupIdText = new JTextField(30);

    private JPanel artifactIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel artifactIdLabel = new JLabel("artifactId");
    private JTextField artifactIdText = new JTextField(30);

    private JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel fileLabel = new JLabel("生成目录");
    private JTextField fileText = new JTextField(30);
    private JButton fileButton = new JButton("选择");
    private JFileChooser jfc = null;


    private JPanel emptyDirPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JRadioButton emptyDirRadio = new JRadioButton("清空生成目录");

    private JRadioButton isLombok = new JRadioButton("启用lombok");

    private JPanel operationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JButton connect = new JButton("连接");
    private JButton getTable = new JButton("获取表格");
    private JButton generate = new JButton("生成");
    private JButton saveConfig = new JButton("保存");
    private JButton readConfig = new JButton("读取");

    private JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JTextArea textArea = new MyTextArea("log");

    public DbOperationPanel() {
        super();
        init(this);
    }

    private void init(DbOperationPanel panel) {

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        setPreferredSizes(urlLabel);
        urlPanel.add(urlLabel);
        urlPanel.add(urlText);
        panel.add(urlPanel);

        setPreferredSizes(userLabel);
        userPanel.add(userLabel);
        userPanel.add(userText);
        panel.add(userPanel);

        setPreferredSizes(passLabel);
        passPanel.add(passLabel);
        passPanel.add(passText);
        panel.add(passPanel);

        setPreferredSizes(groupIdLabel);
        groupIdPanel.add(groupIdLabel);
        groupIdPanel.add(groupIdText);
        panel.add(groupIdPanel);

        setPreferredSizes(artifactIdLabel);
        artifactIdPanel.add(artifactIdLabel);
        artifactIdPanel.add(artifactIdText);
        panel.add(artifactIdPanel);

        setPreferredSizes(fileLabel);
        filePanel.add(fileLabel);
        filePanel.add(fileText);
        filePanel.add(fileButton);
        panel.add(filePanel);

        emptyDirPanel.add(emptyDirRadio);
        emptyDirPanel.add(isLombok);
        panel.add(emptyDirPanel);

        operationPanel.add(connect);
        operationPanel.add(getTable);
        operationPanel.add(generate);
        operationPanel.add(new JLabel("|"));
        operationPanel.add(saveConfig);
        operationPanel.add(readConfig);
        panel.add(operationPanel);

        EventBusUtils.register(textArea);
        textPanel.add(textArea);
        panel.add(textArea);

        connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String url = urlText.getText();
                String user = userText.getText();
                String pass = passText.getText();

                if (CommonUtils.isStrEmpty(url) || CommonUtils.isStrEmpty(user) || CommonUtils.isStrEmpty(pass)) {
                    JOptionPane.showMessageDialog(null, "url、user、pass不能为空", "提示", 2);
                    return;
                }

                Events events = EventCentre.getEvent();
                events.setUrl(url);
                events.setUser(user);
                events.setPwd(pass);

                DbOperationProcess.setDbSources();
                DbOperationProcess.refurbishDbTable();

            }
        });

        getTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DbOperationProcess.refurbishTableTable();
            }
        });

        generate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String groupId = groupIdText.getText();
                String artifactId = artifactIdText.getText();
                String dirPath = fileText.getText();
                boolean selected = emptyDirRadio.isSelected();
                boolean isLombokSelected = isLombok.isSelected();


                if (CommonUtils.isStrEmpty(groupId) || CommonUtils.isStrEmpty(artifactId) || CommonUtils.isStrEmpty(dirPath)) {
                    JOptionPane.showMessageDialog(null, "groupId、artifactId、dirPath不能为空", "提示", 2);
                    return;
                }
                Events events = EventCentre.getEvent();
                events.setGroupId(groupId)
                        .setArtifactId(artifactId)
                        .setDirPath(dirPath)
                        .setEmptyDir(selected)
                        .setLombok(isLombokSelected);
                if (CommonUtils.isCollectionEmpty(events.getSelectTable())) {
                    JOptionPane.showMessageDialog(null, "请选择要生成的表", "提示", 2);
                    return;
                }
                try {
                    DbOperationProcess.build2File();
                    JOptionPane.showMessageDialog(null, "生成成功", "提示", 2);
                } catch (Exception e1) {
                    LogUtils.log2Area(e1.getMessage());
                    e1.printStackTrace();
                }

            }
        });

        fileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
                if (state == 1) {
                    return;
                } else {
                    fileText.setText(jfc.getSelectedFile().getAbsolutePath());
                }
            }
        });

        saveConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(CommonUtils.isStrEmpty(urlText.getText())){
                    JOptionPane.showMessageDialog(null, "请输入url", "提示", 2);
                    return;
                }
                String path = "";
                jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
                if (state == 1) {
                    return;
                } else {
                    path = jfc.getSelectedFile().getAbsolutePath();
                }



                Events events = new Events();
                events.setUrl(urlText.getText());
                events.setUser(userText.getText());
                events.setPwd(passText.getText());
                events.setPwd(passText.getText());
                events.setGroupId(groupIdText.getText());
                events.setArtifactId(artifactIdText.getText());
                ConfigUtils.saveConfig(path, events);

            }
        });
        readConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String path = "";
                jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.setFileFilter(new FileFilter() {// FileFilter 为抽象类
                    // 注意：这个不是实例化FileFilter类 ， 这是采用内部类的方式

                    @Override
                    public String getDescription() {// 显示为指定后缀名的文件
                        return ConfigUtils.CONFIG_SUFFIX;
                    }

                    @Override
                    public boolean accept(File f) {// 判断文件是否已java结尾
                        if (f.getName().endsWith(ConfigUtils.CONFIG_SUFFIX)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
                if (state == 1) {
                    return;
                } else {
                    path = jfc.getSelectedFile().getAbsolutePath();
                }


                try {
                    Events events = ConfigUtils.readConfig(path);
                    urlText.setText(events.getUrl());
                    userText.setText(events.getUser());
                    passText.setText(events.getPwd());
                    groupIdText.setText(events.getGroupId());
                    artifactIdText.setText(events.getArtifactId());
                    BeanUtils.copyProperties(events, EventCentre.getEvent());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "读取失败", "提示", 2);
                    ex.printStackTrace();
                }

            }
        });

    }

    private void setPreferredSizes(JLabel field) {
        field.setPreferredSize(new Dimension(60, 20));
    }

}
