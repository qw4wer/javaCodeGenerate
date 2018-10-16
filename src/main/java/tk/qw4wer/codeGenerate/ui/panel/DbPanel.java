package tk.qw4wer.codeGenerate.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import tk.qw4wer.codeGenerate.ui.table.DbTable;
import tk.qw4wer.codeGenerate.ui.table.TableTable;

public class DbPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6923434030068844903L;

	private DbTable dbTable = new DbTable();

	private TableTable tableTable = new TableTable();

	private JScrollPane pane;

	public DbPanel() {
		super();
		init(this);
	}

	private void init(DbPanel dbPanel) {

		dbPanel.setLayout(new BorderLayout(5, 5));

		pane = new JScrollPane(dbTable);
		
		dbPanel.add(BorderLayout.NORTH, pane);
		
		

		
		pane = new JScrollPane(tableTable);

		dbPanel.add(BorderLayout.CENTER, pane);

	}

	public DbTable getDbTable() {
		return dbTable;
	}

	public void setDbTable(DbTable dbTable) {
		this.dbTable = dbTable;
	}

	public TableTable getTableTable() {
		return tableTable;
	}

	public void setTableTable(TableTable tableTable) {
		this.tableTable = tableTable;
	}

}