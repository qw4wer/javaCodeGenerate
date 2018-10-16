package tk.qw4wer.codeGenerate.ui.table;

import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import tk.qw4wer.codeGenerate.pojo.Event;

import com.google.common.eventbus.Subscribe;

public class DataGridTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -247666484112501981L;

	private String id;

	public DataGridTableModel(String id, Object[][] data, String[] columns) {
		super(data, columns);
		this.id = id;
	}

	public boolean isCellEditable(int row, int column) { // 设置Table单元格是否可编辑
		if (column == 0)
			return true;
		return false;
	}

	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Boolean.class;
		}
		return Object.class;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Subscribe
	public void sub(Event<List<String>> event) {

		if (this.id.equals(event.getControlId())) {
			this.setRowCount(0);
			for (String db : event.getData())
				this.addRow(new Object[] { false, db });
		}

	}

}
