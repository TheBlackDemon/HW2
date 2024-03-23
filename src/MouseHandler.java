import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private Game game;
    private int firstX;
    private int secondX;
    private int firstY;
    private int secondY;
    public MouseHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (game.getCondition().equals("Before_Aiming")) {
            firstX = e.getX();
            firstY = e.getY();
            game.setCondition("Aiming");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (game.getCondition().equals("Aiming")){
            secondX = e.getX();
            secondY = e.getY();
            int norm = (int) Math.sqrt((secondX - firstX)*(secondX - firstX) + (secondY - firstY)*(secondY - firstY));
            if (norm != 0) {
                for (int i = 0 ; i < game.getBalls().size(); i++) {
                    Ball ball = game.getBalls().get(i);
                    ball.setVecX(-(secondX - firstX) * 8 / norm);
                    ball.setVecY(-(secondY - firstY) * 8 / norm);
                }
                game.setCondition("Sending_Ball");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getFirstX() {
        return firstX;
    }

    public void setFirstX(int firstX) {
        this.firstX = firstX;
    }

    public int getFirstY() {
        return firstY;
    }

    public void setFirstY(int firstY) {
        this.firstY = firstY;
    }
}
