import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StartPanel extends JPanel implements ActionListener {
    private JButton startNewGame;
    private JButton allRecords;
    private JButton history;
    private JButton setting;
    private JButton exit;
    public StartPanel(){
        this.setSize(1080,771);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();

        startNewGame = new JButton("Start New Game");
        this.add(startNewGame);
        startNewGame.setBounds(460,100,160, 80);
        startNewGame.setFocusable(false);
        startNewGame.addActionListener(this);

        allRecords = new JButton("All Records");
        this.add(allRecords);
        allRecords.setBounds(460 , 210 , 160 , 80);
        allRecords.setFocusable(false);
        allRecords.addActionListener(this);

        history = new JButton("History");
        this.add(history);
        history.setBounds(460 , 320 , 160 , 80);
        history.setFocusable(false);
        history.addActionListener(this);

        setting = new JButton("Setting");
        this.add(setting);
        setting.setBounds(460 , 430 , 160 , 80);
        setting.setFocusable(false);
        setting.addActionListener(this);

        exit = new JButton("Exit");
        this.add(exit);
        exit.setBounds(460 , 540 , 160 , 80);
        exit.setFocusable(false);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startNewGame)){
            MainFrame.getInstance().setContentPane(new GamePreparationPanel());
        } else if (e.getSource().equals(allRecords)) {
        } else if (e.getSource().equals(history)) {
            MainFrame.getInstance().setContentPane(new HistoryPanel());
        } else if (e.getSource().equals(setting)) {
            MainFrame.getInstance().setContentPane(new SettingPanel());
        } else if (e.getSource().equals(exit)) {
            System.exit(0);
        }
    }
}

