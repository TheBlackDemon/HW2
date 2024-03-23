import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePreparationPanel extends JPanel implements ActionListener {
    private JButton start;
    private JButton save;
    private TextField nameOfPlayer;
    private JLabel colp;
    private Button pickColor;
    private JLabel selectMode;
    private ButtonGroup gameMode;
    private JRadioButton easy;
    private JRadioButton normal;
    private JRadioButton hard;
    private String state;
    private Color color;
    private String name;
    private JButton back;

    public  GamePreparationPanel(){
        this.setSize(1080,771);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();

        selectMode = new JLabel("Select Mode");
        selectMode.setBounds(440 , 40 , 200 , 40);
        this.add(selectMode);
        selectMode.setForeground(Color.BLUE);
        selectMode.setFont(selectMode.getFont().deriveFont(24.0f));
        //        inst1.setBounds(40,180,420,30);
//        this.add(inst1);
//        inst1.setForeground(Color.BLUE);

        gameMode = new ButtonGroup();

        easy = new JRadioButton("easy");
        easy.setBounds(230 , 100 , 240 , 40);
        this.add(easy);
        normal = new JRadioButton("normal");
        normal.setBounds(500 , 100 , 240 , 40);
        this.add(normal);
        hard = new JRadioButton("hard");
        hard.setBounds(770 , 100 , 240 , 40);
        this.add(hard);
        easy.setFont(easy.getFont().deriveFont(24.0f));
        normal.setFont(normal.getFont().deriveFont(24.0f));
        hard.setFont(hard.getFont().deriveFont(24.0f));
        gameMode.add(easy);
        gameMode.add(normal);
        gameMode.add(hard);

        colp = new JLabel();
        colp.setBounds(400 , 260 , 400 , 40);
        this.add(colp);
        colp.setText("Select Colour Player :D");
        colp.setForeground(Color.PINK);
        colp.setFont(colp.getFont().deriveFont(24.0f));

        pickColor = new Button("Pick a color");
        this.add(pickColor);
        pickColor.setBounds(80 , 260 , 120 , 40);
        pickColor.setFocusable(false);
        pickColor.addActionListener(this);
        pickColor.setBackground(Color.PINK);

        start = new JButton("Start new game !");
        this.add(start);
        start.setBounds(440, 570, 200, 50);
        start.setFocusable(false);
        start.addActionListener(this);

        nameOfPlayer = new TextField("Enter your name");
        this.add(nameOfPlayer);
        nameOfPlayer.setBounds(230,420,650,60);

        save = new JButton("save");
        this.add(save);
        save.setBounds(80,426,120,40);
        save.setFocusable(false);
        save.addActionListener(this);


        back = new JButton("Back");
        this.add(back);
        back.setBounds(440 , 640 , 200 , 50);
        back.setFocusable(false);
        back.addActionListener(this);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(start)){
//            if(q1 == false || q2 == false){
//                if(q2 == true){
//                    JOptionPane.showMessageDialog(this, "click on save for player 1");
//                }
//                else if(q1 == true){
//                    JOptionPane.showMessageDialog(this, "click on save for player 2");
//                }
//                else{
//                    JOptionPane.showMessageDialog(this, "click on save for player 1 & 2");
//                }
//            }
//            else {
//                if (!f1) {
//                    JOptionPane.showMessageDialog(this, "Select colour of player 1");
//                } else if (!f2) {
//                    JOptionPane.showMessageDialog(this, "Select colour of player 2");
//                } else {
//                    dispose();
//                    AirHockey airHockey = new AirHockey();
//                    airHockey.setVisible(true);
//                }
//            }
//        }
//        else if(e.getSource().equals(save)) {
//            //AirHockey.players.get(AirHockey.numberOfGames + 1).get(0) = nameOfPlayer.getText();
//            linkedList.add(nameOfPlayer.getText());
//            q1 = true;
//
//        }
//        else if (e.getSource().equals(button2)) {
//            linkedList.add(textField2.getText());
//            q2 = true;
//            AirHockkey.players.add(linkedList);
//        }
//        else if (e.getSource().equals(back)) {
//            dispose();
//            Frame21 frame21 = new Frame21();
//            frame21.setVisible(true);
//        }
//        else if(e.getSource().equals(pickColor)){
//            JColorChooser colorChooser = new JColorChooser();
//            Color color = JColorChooser.showDialog(null, "Color Player1" , Color.BLACK);
//            Frame31.color = color;
//            colp.setForeground(color);
//            f1 =true;
//        }
//        else if(e.getSource().equals(colop2)){
//            JColorChooser colorChooser = new JColorChooser();
//            Color color = JColorChooser.showDialog(null, "Color Player2" , Color.BLACK);
//            Frame31.colorP2 = color;
//            colp2.setForeground(color);
//            f2 = true;
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (easy.isSelected()){
            state = "Easy";
        } else if (normal.isSelected()) {
            state = "Normal";
        } else if (hard.isSelected()) {
            state = "Hard";
        }
        if(e.getSource().equals(pickColor)){
            JColorChooser colorChooser = new JColorChooser();
            color = JColorChooser.showDialog(null, "Color Player1" , Color.BLACK);
            colp.setForeground(color);
            pickColor.setBackground(color);
        } else if (e.getSource().equals(save)) {
            name = nameOfPlayer.getText();
        } else if (e.getSource().equals(start)) {
            if (name == null){
                JOptionPane.showMessageDialog(this, "Enter your name");
            } else if (state == null) {
                JOptionPane.showMessageDialog(this, "Select the mode");
            } else if (color == null) {
                JOptionPane.showMessageDialog(this, "Select the colour");
            } else {
                Game game = new Game(color , name , state);
                MainFrame.getInstance().setContentPane(game);
                game.setFocusable(true);
                game.requestFocus();
                game.requestFocusInWindow();
            }
        }else if (e.getSource().equals(back)){
            MainFrame.getInstance().setContentPane(new StartPanel());
        }
    }
}
