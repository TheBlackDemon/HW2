import java.awt.*;
import java.util.Random;

public class Item {
    private double x;
    private double y;
    private double radius;
    private int type;
    private Game game;
    private double vecY = 0;

    public Item(double x, double y, double r, int type , Game game) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.type = type;
        this.game = game;
        if(game.getState().equalsIgnoreCase("Easy")){
            vecY = 0.2;
        } else if (game.getState().equalsIgnoreCase("Normal")) {
            vecY = 0.35;
        } else if (game.getState().equalsIgnoreCase("Hard")) {
            vecY = 0.5;
        }
    }
    public void paint(Graphics g){
        if (type == 0 || type == 4){
            g.setColor(Color.PINK);
        } else if (type == 1) {
            g.setColor(Color.BLUE);
        } else if (type == 2) {
            g.setColor(Color.RED);
        } else if (type == 3) {
            g.setColor(Color.ORANGE);
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
                if (type == 0 || type == 4){
                    game.setAddBall(game.getAddBall() + 1);
                } else if (type == 1) {
                    game.setMoveFast(true);
                } else if (type == 2) {
                    game.setStrength(true);
                } else if (type == 3) {
                    game.setDeez(true);
                }
                game.getItems().remove(this);
            }
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }
}
