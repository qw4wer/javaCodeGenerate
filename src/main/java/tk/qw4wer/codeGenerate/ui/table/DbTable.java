package tk.qw4wer.codeGenerate.ui.table;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.utils.events.EventBusUtils;

public class DbTable extends JTable {
	private String[] columns = { "选择", "数据库名称" };

	private Object[][] datas = {};

	public DbTable() {

		DataGridTableModel model = new DataGridTableModel("db", datas, columns);

		this.setModel(model);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addEvent(this);
		setPreferredScrollableViewportSize(new Dimension(250, 100));
		EventBusUtils.register(model);

	}

	private void addEvent(final DbTable table) {
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (table.getSelectedColumn() == 0) {
					int rowCount = table.getRowCount();
					int selectRow = table.getSelectedRow();
					for (int i = 0; i < rowCount; i++) {

						if (table.getValueAt(i, 0).toString().equals("true")) {
							table.setValueAt(false, i, 0);
						}
					}
					EventCentre.getEvent().setSelectDb(table.getValueAt(selectRow, 1).toString());
					table.setValueAt(true, selectRow, 0);
				}
				return;
			}
		});
	}

}
