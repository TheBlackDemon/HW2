import java.awt.*;
import java.util.Random;

public class Brick {
    private Game game;
    private double x;
    private double y;
    private double width;
    private double height;
    private int number;
    private double vecY = 0;
    private int score;
    private SpecialItem specialItem;
    private int i;
    private boolean increasing = false;

    public Brick(double x, double y, double width, double height , int number, int i, Game game ) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.width = width;
        this.height = height;
        this.number = number;
        this.score = number;
        this.game = game;
        if(game.getState().equalsIgnoreCase("Easy")){
            vecY = 0.2;
        } else if (game.getState().equalsIgnoreCase("Normal")) {
            vecY = 0.35;
        } else if (game.getState().equalsIgnoreCase("Hard")) {
            vecY = 0.5;
        }
        if (i % 8 == 1){
            specialItem = new SpecialItem( x + width/2, y+ height/2, 15 , 0 ,game , this );
            game.getSpecialItems().add(specialItem);
        }else if (i % 8 == 5){
            specialItem = new SpecialItem( x + width/2, y+ height/2, 15 , 1 ,game , this);
            game.getSpecialItems().add(specialItem);
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        if (game.isStopNoor() ){
            g.setColor(new Color(new Random().nextInt(255),
                    new Random().nextInt(255),
                    new Random().nextInt(255)));
        }
        g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(Color.red);
        g.drawString(String.valueOf(number), (int) (x + width/2) , (int) (y + height/2));
    }

    public void update(){
        if (game.isZelzele() && !game.isStopZelzele()){
            game.setZelzele(false);
            game.setStopZelzele(true);
            game.setFirstTimeZelzele((int) game.getElapsedTime());
        }
        if (game.isStopZelzele() && game.getElapsedTime()-game.getFirstTimeZelzele()>=10){
            game.setStopZelzele(false);
        }
        if (game.isStopZelzele()){
            if (width <= 42){
                increasing = true;
            }else if (width >= 78){
                increasing = false;
            }
            if (increasing){
                width += 0.1;
                height += 0.1;
            }else {
                width -= 0.1;
                height -= 0.1;
            }
        }

        if(y > MainFrame.MENU_HEIGHT - 190){
            game.gameOver();
        }
        if (!game.getCondition().equals("Sending_Ball") && !game.getCondition().equals("Game_Over")) {
            y += vecY;
        }
        if(number <= 0){
            if (score > game.getElapsedTime()/30);
            game.setScore((int) (game.getScore() + score - game.getElapsedTime()/30));
            game.getBricks().remove(this);
            y+=60;
        }
    }
    public Rectangle getBounds(){
        return  new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public SpecialItem getSpecialItem() {
        return specialItem;
    }

    public void setSpecialItem(SpecialItem specialItem) {
        this.specialItem = specialItem;
    }
}
