import java.awt.*;
import java.util.Random;

public class SpecialItem {
    private double x;
    private double y;
    private double radius;
    private int type;
    private Game game;
    private double vecY = 0;
    private Brick brick;

    public SpecialItem(double x, double y, double radius, int type, Game game , Brick brick) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.type = type;
        this.game = game;
        this.brick = brick;
        if(game.getState().equalsIgnoreCase("Easy")){
            vecY = 0.2;
        } else if (game.getState().equalsIgnoreCase("Normal")) {
            vecY = 0.35;
        } else if (game.getState().equalsIgnoreCase("Hard")) {
            vecY = 0.5;
        }

    }
    public void paint(Graphics g){
        if (type == 0){
            g.setColor(Color.CYAN);
        } else if (type == 1) {
            g.setColor(Color.GREEN);
        }
        if (game.isStopNoor() ){
            g.setColor(new Color(new Random().nextInt(255),
                    new Random().nextInt(255),
                    new Random().nextInt(255)));
        }
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }
    public void update(){
        if (!game.getCondition().equals("Sending_Ball") && !game.getCondition().equals("Game_Over")) {
            y += vecY;
        }
        for(int i = 0 ; i < game.getBalls().size(); i++){
            Ball ball = game.getBalls().get(i);
            if (this.getBounds().intersects(ball.getBounds())){
                if (type == 0 ){
                    game.setNoor(true);
                } else if (type == 1) {
                    game.setZelzele(true);
                }
                game.getSpecialItems().remove(this);
            }
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public Brick getBrick() {
        return brick;
    }
}
