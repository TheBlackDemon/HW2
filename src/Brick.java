import java.awt.*;

public class Brick {
    private Game game;
    private double x;
    private double y;
    private double width;
    private double height;
    private int number;
    private double vecY = 0;
    private int score;
    public Brick(double x, double y, double width, double height , int number , Game game ) {
        this.x = x;
        this.y = y;
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
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(Color.red);
        g.drawString(String.valueOf(number), (int) (x + width/2) , (int) (y + height/2));
    }

    public void update(){
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
}
