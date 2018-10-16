package tk.qw4wer.codeGenerate.ui.table;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;

public class TableTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722630449312082431L;

	private String[] columns = { "选择", "表格名称" };

	private Object[][] datas = {};

	public TableTable() {

		DataGridTableModel model = new DataGridTableModel("table", datas, columns);
		this.setModel(model);

		addEvent(this);
		setPreferredScrollableViewportSize(new Dimension(250, 350));
		EventBusUtils.register(model);
	}

	private void addEvent(final TableTable table) {
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					List<String> selectTable = new ArrayList<String>();
					for (int i = 0; i < table.getRowCount(); i++) {

						if (table.getValueAt(i, 0).toString().equals("true")) {
							selectTable.add(table.getValueAt(i, 1).toString());
						}
					}
					EventCentre.getEvent().setSelectTable(selectTable);
				}
			}
		});
	}
}
