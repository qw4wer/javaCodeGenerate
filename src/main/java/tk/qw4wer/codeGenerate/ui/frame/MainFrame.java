package tk.qw4wer.codeGenerate.ui.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import tk.qw4wer.codeGenerate.ui.panel.DbOperationPanel;
import tk.qw4wer.codeGenerate.ui.panel.DbPanel;

public class MainFrame extends JFrame {

	public MainFrame() {

		this.setLayout(new BorderLayout(5, 5));

		DbPanel dbPanel = new DbPanel();
		getContentPane().add(BorderLayout.WEST, dbPanel);

		DbOperationPanel operationPanel = new DbOperationPanel();

		getContentPane().add(BorderLayout.CENTER, operationPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle("代码生成器");
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
