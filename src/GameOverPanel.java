import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel implements ActionListener {
    private JButton playAgain;
    private JButton backToGamePreparationPanel;
    private JButton backToMainMenu;
    private String state;
    private Color color;
    private String name;
    private JLabel gameOver;
    public GameOverPanel(String state , Color color , String name){
        this.setSize(1080,771);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();

        this.state = state;
        this.color = color;
        this.name = name;

        playAgain = new JButton("Play Again");
        this.add(playAgain);
        playAgain.setBounds(460,100,260, 80);
        playAgain.setFocusable(false);
        playAgain.addActionListener(this);

        backToGamePreparationPanel = new JButton("Back To Preparation Panel");
        this.add(backToGamePreparationPanel);
        backToGamePreparationPanel.setBounds(460,250,260, 80);
        backToGamePreparationPanel.setFocusable(false);
        backToGamePreparationPanel.addActionListener(this);

        backToMainMenu = new JButton("Back To Main Menu");
        this.add(backToMainMenu);
        backToMainMenu.setBounds(460,400,260, 80);
        backToMainMenu.setFocusable(false);
        backToMainMenu.addActionListener(this);

        gameOver = new JLabel();
        gameOver.setBounds(400 , 60 , 400 , 40);
        this.add(gameOver);
        gameOver.setText("Waisted");
        gameOver.setForeground(Color.RED);
        gameOver.setFont(gameOver.getFont().deriveFont(24.0f));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playAgain)){
            Game game = new Game(color , name , state);
            MainFrame.getInstance().setContentPane(game);
            game.setFocusable(true);
            game.requestFocus();
            game.requestFocusInWindow();
        } else if (e.getSource().equals(backToGamePreparationPanel)) {
            MainFrame.getInstance().setContentPane(new GamePreparationPanel());
        } else if (e.getSource().equals(backToMainMenu)) {
            MainFrame.getInstance().setContentPane(new StartPanel());
        }
    }
}
