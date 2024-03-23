import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {
    private static MainFrame instance;
    private StartPanel startPanel;
    public static final int MENU_WIDTH = 1080;
    public static final int MENU_HEIGHT = 771;
    private static boolean save = false;
    private static boolean music = false;
    private static boolean aim = false;

    public MainFrame() {
        this.setSize(1080 , 771);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        startPanel = new StartPanel();
        this.setContentPane(startPanel);
        instance = this;
        this.setVisible(true);



    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public void setStartPanel(StartPanel startPanel) {
        this.startPanel = startPanel;
    }

    public static boolean isSave() {
        return save;
    }

    public static void setSave(boolean save) {
        MainFrame.save = save;
    }

    public static boolean isMusic() {
        return music;
    }

    public static void setMusic(boolean music) {
        MainFrame.music = music;
    }

    public static boolean isAim() {
        return aim;
    }

    public static void setAim(boolean aim) {
        MainFrame.aim = aim;
    }

    public static MainFrame getInstance() {
        return instance;
    }

}


