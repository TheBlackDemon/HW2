import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HistoryPanel extends JPanel{
    private final String[] columnNames;
    private final DefaultTableModel tableModel;
    private JTable table;

    public HistoryPanel(){
        this.setSize(1080,771);

        this.setLayout(new BorderLayout());

//        back = new JButton("Back");
//        this.add(back);
//        back.setBounds(440 , 640 , 200 , 50);
//        back.setFocusable(false);
//        back.addActionListener(this);

        columnNames = new String[]{"Time" , "Name" , "Date" , "Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
    //    table.setBounds(0 , 0 , 1080 , 771);
        table.setEnabled(false);

        JScrollPane p = new JScrollPane();
        p.setViewportView(table);
        retrieveData();
        this.add(p, BorderLayout.CENTER);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER){
                    MainFrame.getInstance().setContentPane(new StartPanel());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();
    }
    private void retrieveData(){
        for(int i = 0; i < 1; i ++){
            Object[] rowData = new Object[1];
            rowData[0] = "Press the enter button to exit this page";
            tableModel.addRow(rowData);
        }
        for (int i = 0; i < MainFrame.getDatas().size(); i++){
            Object[] rowData = new Object[columnNames.length];
            //
            rowData[0] = MainFrame.getDatas().get(i).getTime();
            rowData[1] = MainFrame.getDatas().get(i).getName();
            rowData[2] = MainFrame.getDatas().get(i).getDate();
            rowData[3] = MainFrame.getDatas().get(i).getScore();
            //
            tableModel.addRow(rowData);
        }
        for(int i = 0; i < 90; i ++){
            Object[] rowData = new Object[columnNames.length];
            rowData[0] = " ";
            rowData[1] = " ";
            rowData[2] = " ";
            rowData[3] = " ";
            tableModel.addRow(rowData);
        }


     //   tableModel.fireTableDataChanged();
    }

}
