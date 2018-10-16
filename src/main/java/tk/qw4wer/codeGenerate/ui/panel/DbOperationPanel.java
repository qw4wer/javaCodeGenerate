package tk.qw4wer.codeGenerate.ui.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.pojo.Events;
import tk.qw4wer.codeGenerate.ui.processing.DbOperationProcess;
import tk.qw4wer.codeGenerate.ui.text.area.MyTextArea;
import tk.qw4wer.codeGenerate.utils.CommonUtils;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;
import tk.qw4wer.codeGenerate.utils.logs.LogUtils;

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

	private JPanel operationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JButton connect = new JButton("连接");
	private JButton getTable = new JButton("获取表格");
	private JButton generate = new JButton("生成");

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

		operationPanel.add(connect);
		operationPanel.add(getTable);
		operationPanel.add(generate);
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
				if (CommonUtils.isStrEmpty(groupId) || CommonUtils.isStrEmpty(artifactId) || CommonUtils.isStrEmpty(dirPath)) {
					JOptionPane.showMessageDialog(null, "groupId、artifactId、dirPath不能为空", "提示", 2);
					return;
				}
				Events events = EventCentre.getEvent();
				events.setGroupId(groupId);
				events.setArtifactId(artifactId);
				events.setDirPath(dirPath);
				if (CommonUtils.isCollectionEmpty(events.getSelectTable())) {
					JOptionPane.showMessageDialog(null, "请选择要生成的表", "提示", 2);
					return;
				}
				try {
					DbOperationProcess.build2File();
					JOptionPane.showMessageDialog(null, "生成成功", "提示", 2);
				} catch (Exception e1) {
					LogUtils.log2Area(e1.toString());
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

	}

	private void setPreferredSizes(JLabel field) {
		field.setPreferredSize(new Dimension(60, 20));
	}

}
