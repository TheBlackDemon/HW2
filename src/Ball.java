import java.awt.*;
import java.util.Random;

public class Ball {
    private boolean firstMove = false;
    private double vecX = 0;
    private double vecY = 0;
    private Color color;
    private double x;
    private double y;
    private double radius;
    private Game game;
    private int num;
    private int power = 1;
    public Ball(double x, double y , double r , Game game , int num , Color color) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.game = game;
        this.num = num;
        this.color = color;
    }
    public void paint(Graphics g){
        g.setColor(color);
        if (game.isStopNoor() ){
            g.setColor(new Color(new Random().nextInt(255),
                    new Random().nextInt(255),
                    new Random().nextInt(255)));
        }
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }
    public void update(){
        if (game.isStrength() && !game.isStopStrength()){
            power = 2;
            game.setStrength(false);
            game.setStopStrength(true);
            game.setFirstTimeStrength((int) game.getElapsedTime());
        }
        if (game.isStopStrength() && game.getElapsedTime()-game.getFirstTimeStrength()>=15){
            power = 1;
            game.setStopStrength(false);
        }
        if (game.isMoveFast() && !game.isStopMoveFast()){
            vecX = vecX*2;
            vecY = vecY*2;
            game.setMoveFast(false);
            game.setStopMoveFast(true);
            game.setFirstTimeMoveFast((int) game.getElapsedTime());
        }
        if (game.isMoveFast() && game.getElapsedTime()-game.getFirstTimeMoveFast()>=15){
            vecX = vecX/2;
            vecY = vecY/2;
            game.setStopMoveFast(false);
        }
        if (!firstMove && correctDistance()){
            firstMove = true;
        }
        if(firstMove) {
            x += vecX;
            y += vecY;
        }
        if(x <= 10 || x >= MainFrame.MENU_WIDTH - 25){
            vecX = -vecX;
        }
        if(y <= 15){
            vecY = -vecY;
        }
        if(y >= MainFrame.MENU_HEIGHT - 50 ){
            game.getBalls().remove(this);
            if (game.getBalls().size() == 0){
                game.setLastX(x);
                game.changeRound();
            }
        }

        for(Brick brick : game.getBricks()){
                if(this.getBounds().intersects(( brick).getBounds())){

                    (brick).setNumber((brick).getNumber()-power);
                    vecX = -vecX;
                    vecY = -vecY;
                }
        }
    }
    public boolean correctDistance(){
        if (num == 0){
            return true;
        }else if ((x-game.getBalls().get(num-1).getX())*(x-game.getBalls().get(num-1).getX()) + (y-game.getBalls().get(num-1).getY())*(y-game.getBalls().get(num-1).getY()) >= 3*radius*radius){
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }

    public double getVecX() {
        return vecX;
    }

    public void setVecX(double vecX) {
        this.vecX = vecX;
    }

    public double getVecY() {
        return vecY;
    }

    public void setVecY(double vecY) {
        this.vecY = vecY;
    }

    public double getRadius() {
        return radius;
    }
}
