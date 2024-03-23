import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel implements ActionListener {
    private ButtonGroup gameAim;
    private JRadioButton aim;
    private JRadioButton noAim;
    private ButtonGroup gameMusic;
    private JRadioButton music;
    private JRadioButton noMusic;
    private ButtonGroup gameSave;
    private JRadioButton save;
    private JRadioButton notSave;
    private JButton back;
    public SettingPanel(){
        this.setSize(1080,771);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();



        gameAim = new ButtonGroup();

        aim = new JRadioButton("aim");
        aim.setBounds(300 , 100 , 240 , 40);
        this.add(aim);
        noAim = new JRadioButton("no aim");
        noAim.setBounds(600 , 100 , 240 , 40);
        this.add(noAim);
        aim.setFont(aim.getFont().deriveFont(24.0f));
        noAim.setFont(noAim.getFont().deriveFont(24.0f));
        gameAim.add(aim);
        gameAim.add(aim);

        gameMusic = new ButtonGroup();

        music = new JRadioButton("unmute");
        music.setBounds(300 , 300 , 240 , 40);
        this.add(music);
        noMusic = new JRadioButton("Mute");
        noMusic.setBounds(600 , 300 , 240 , 40);
        this.add(noMusic);
        music.setFont(music.getFont().deriveFont(24.0f));
        noMusic.setFont(noMusic.getFont().deriveFont(24.0f));
        gameMusic.add(music);
        gameMusic.add(noMusic);

        gameSave = new ButtonGroup();

        save = new JRadioButton("Auto save");
        save.setBounds(300 , 500 , 240 , 40);
        this.add(save);
        notSave = new JRadioButton("Dont Auto save");
        notSave.setBounds(600 , 500 , 240 , 40);
        this.add(notSave);
        save.setFont(save.getFont().deriveFont(24.0f));
        notSave.setFont(notSave.getFont().deriveFont(24.0f));
        gameSave.add(save);
        gameSave.add(notSave);

        back = new JButton("Back");
        this.add(back);
        back.setBounds(460 , 600 , 140 , 80);
        back.setFocusable(false);
        back.addActionListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (aim.isSelected()){
            MainFrame.setAim(true);
        }
        if (music.isSelected()){
            MainFrame.setMusic(true);
        }
        if (save.isSelected()){
            MainFrame.setSave(true);
        }

        if (noAim.isSelected()){
            MainFrame.setAim(false);
        }
        if (noMusic.isSelected()){
            MainFrame.setMusic(false);
        }
        if (notSave.isSelected()){
            MainFrame.setSave(false);
        }
        if (e.getSource().equals(back)){
            MainFrame.getInstance().setContentPane(new StartPanel());
        }
    }
}
