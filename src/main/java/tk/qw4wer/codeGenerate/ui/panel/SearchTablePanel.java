package tk.qw4wer.codeGenerate.ui.panel;

import tk.qw4wer.codeGenerate.events.EventCentre;
import tk.qw4wer.codeGenerate.pojo.Events;
import tk.qw4wer.codeGenerate.ui.processing.DbOperationProcess;
import tk.qw4wer.codeGenerate.utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchTablePanel extends JPanel {

    private JTextField searchCode = new JTextField(15);
    private JButton search = new JButton("查询");
    private JButton cancel = new JButton("取消");

    public SearchTablePanel() {
        super();
        init(this);
    }

    private void init(SearchTablePanel searchTablePanel) {

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));

        jPanel.add(searchCode);
        jPanel.add(search);
        jPanel.add(cancel);
        searchTablePanel.add(jPanel);

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String code = searchCode.getText();
                Events events = EventCentre.getEvent();
                events.setCode(code);
                DbOperationProcess.refurbishAndSearchTableTable();
            }
        });
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchCode.setText("");
                DbOperationProcess.refurbishTableTable();
            }
        });
    }
}
