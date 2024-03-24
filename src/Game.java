import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class Game extends JPanel implements Runnable {
    private LinkedList<SpecialItem> specialItems = new LinkedList<>();

    private LinkedList<Item> items = new LinkedList<>();
    private int score = 0;

    private boolean paused = false;
    private int numberOfBrick;
    private int round = 1;
    private double lastX = MainFrame.MENU_WIDTH/2;

    private LinkedList<Ball> balls = new LinkedList<>();
    private LinkedList<Brick> bricks = new LinkedList<>();

    //Before_Aiming, Aiming , Sending_Ball , Collision , Game_Over
    private String condition = "Before_Aiming";
    private boolean running = false;
    private Brick minBrick;
    private Color color;
    private String name;
    private String state;
    private double rate;
    private long startTime;
    private long endTime;
    private long elapsedTime;
    private long sigmaTime = 0;
    private MouseHandler mouseHandler;
    private double secondX;
    private double secondY;
    private boolean gameOver = false;
    private int addBall = 0;
    private boolean moveFast = false;
    private int firstTimeMoveFast;
    private boolean stopMoveFast = false;
    private boolean strength = false;
    private int firstTimeStrength;
    private boolean stopStrength = false;
    private boolean deez = false;
    private boolean deezRound = false;
    private boolean zelzele = false;
    private int firstTimeZelzele;
    private boolean stopZelzele = false;
    private boolean noor = false;
    private int firstTimeNoor;
    private boolean stopNoor = false;

    public Game(Color color , String name , String state){
        this.color = color;
        this.name = name;
        this.state = state;
        init();
        this.setSize(1080, 771);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();
        this.start();
    }
    public void changeRound(){
        condition = "Before_Aiming";
        round++;
        if (this.isDeez()){
            this.setDeezRound(true);
            this.setDeez(false);
        } else if (this.isDeezRound()) {
            this.setDeezRound(false);
        }
        for (int i = 0; i < round + addBall; i++){
            balls.add(new Ball(lastX , MainFrame.MENU_HEIGHT - 150+15 , 15 , this , i , color));
        }
        for (int i = 0; i < bricks.size(); i++){
            Brick brick = bricks.get(i);
            brick.setY(brick.getY() + brick.getHeight());
        }
        for (int i = 0; i < specialItems.size(); i++){
            SpecialItem specialItem = specialItems.get(i);
            specialItem.setY(specialItem.getY() + specialItem.getBrick().getHeight());
        }
        for (int i = 0; i < 3; i++){
            Item item = new Item(new Random().nextInt(MainFrame.MENU_WIDTH - 30) +15 ,
                    new Random().nextInt((MainFrame.MENU_HEIGHT - 30)/2) +15 , 15 ,
                    new Random().nextInt(5), this);
            while (accident(item)){
                item = new Item(new Random().nextInt(MainFrame.MENU_WIDTH - 30) +15 ,
                        new Random().nextInt((MainFrame.MENU_HEIGHT - 30)/2) +15 , 15 ,
                        new Random().nextInt(4), this);
            }
            items.add(item);
        }
    }
    private boolean accident(Item item){
        for (int i = 0; i < bricks.size(); i++){
            Brick brick = bricks.get(i);
            if (item.getBounds().intersects(brick.getBounds())){
                return true;
            }
        }
        return false;
    }
    public void init(){
        Resources.setMute(false);
        Resources.getThemeSong().play();
        if (state.equalsIgnoreCase("Easy")){
            numberOfBrick = 1;
            rate = 0.6;
        } else if (state.equalsIgnoreCase("Normal")) {
            numberOfBrick = 2;
            rate = 1.1;
        } else if (state.equalsIgnoreCase("Hard")) {
            numberOfBrick = 3;
            rate = 1.6;
        }
        for (int i = 0; i < numberOfBrick; i++){
            int j = bricks.size();
            Brick brick = new Brick(new Random().nextInt(MainFrame.MENU_WIDTH/numberOfBrick - 50)+
                    MainFrame.MENU_WIDTH/numberOfBrick*i, 0 , 60 , 40 , 1 ,j ,this);
            bricks.add(brick);
            minBrick = brick;
        }
        Ball ball = new Ball(lastX , MainFrame.MENU_HEIGHT - 150 + 15, 15 , this , 0 , color);
        balls.add(ball);
    }
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        Thread thread = new Thread(this , "Thread");
        thread.start();
    }
    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
    }

    @Override
    public void run() {
        mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER){
                    paused =! paused;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();
        startTime = System.nanoTime()/1000000000L;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int updates = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while (delta >= 1){
                try {
                    update();
                } catch (AWTException e) {
                    throw new RuntimeException(e);
                }
                updates++;
                delta--;
            }
            repaint();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(name , 20 , MainFrame.MENU_HEIGHT - 100);
        g.drawString( "Time : " + String.valueOf(elapsedTime), 140 , MainFrame.MENU_HEIGHT - 100 );
        g.drawString("Score : " + score , 210 , MainFrame.MENU_HEIGHT - 100);
        for (int i = 0 ; i < balls.size(); i++) {
            balls.get(i).paint(g);
        }
        for (int i = 0 ; i < bricks.size(); i++) {
            bricks.get(i).paint(g);
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).paint(g);
        }
        for (int i = 0; i < specialItems.size(); i++) {
            specialItems.get(i).paint(g);
        }
        if(condition == "Aiming" && MainFrame.isAim()){
            g.setColor(Color.BLACK);
            g.fillOval((int) (getMouseHandler().getFirstX()-8), (int) (getMouseHandler().getFirstY()-8), 16 , 16);
            g.fillOval((int) (secondX-8), (int) (secondY-8), 16 , 16);
            g.drawLine((getMouseHandler().getFirstX()) ,(getMouseHandler().getFirstY()) , (int) (secondX), (int) (secondY));
        }
        if (gameOver){
            g.setFont(new Font("Courier", Font.BOLD, 20));
            g.setColor(Color.RED);
            g.drawString("Game Over" , MainFrame.MENU_WIDTH/2 , MainFrame.MENU_HEIGHT/2);
            MainFrame.getInstance().setContentPane(new GameOverPanel(state , color , name));

        }
    }
    public void gameOver(){
        if (MainFrame.isSave() && !gameOver){
            LocalDateTime date = java.time.LocalDateTime.now();
            Data data = new Data((int)elapsedTime , name , date , score );
            MainFrame.getDatas().add(data);
        }
        if (!gameOver){
            Resources.getThemeSong().stop();
        }
        paused = true;
        gameOver = true;
    }
    public void update() throws AWTException {
        if(!paused) {
            endTime = System.nanoTime() / 1000000000L;
            elapsedTime = endTime - startTime + sigmaTime;

            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).update();
            }
            for (int i = 0; i < bricks.size(); i++) {
                bricks.get(i).update();
            }
            for (int i = 0; i < items.size(); i++) {
                items.get(i).update();
            }
            for (int i = 0; i < specialItems.size(); i++) {
                specialItems.get(i).update();
            }
            if (noor && !stopNoor){
                noor = false;
                stopNoor = true;
                firstTimeNoor = (int) getElapsedTime();
            }
            if (stopNoor && getElapsedTime()-firstTimeNoor>=10){
                stopNoor = false;
            }
            if(condition == "Aiming" && MainFrame.isAim()){
                double vecX = 0;
                double vecY = 0;
                if (!this.isDeezRound()){
                PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                Point screenLocation = pointerInfo.getLocation();
                Point componentLocation = MainFrame.getInstance().getLocationOnScreen();
                int xRelativeToComponent = (int) (screenLocation.getX() - componentLocation.getX())-7;
                int yRelativeToComponent = (int) (screenLocation.getY() - componentLocation.getY())-30;
                secondX = getMouseHandler().getFirstX();
                secondY = getMouseHandler().getFirstY();
                int norm = (int) Math.sqrt((xRelativeToComponent - secondX)*(xRelativeToComponent - secondX) +
                        (yRelativeToComponent - secondY)*(yRelativeToComponent - secondY));
                if (norm != 0) {
                    vecX = (-(xRelativeToComponent - secondX) * 8 / norm);
                    vecY = (-(yRelativeToComponent - secondY) * 8 / norm);
                    boolean accident = false;
                    while (!accident) {
                        if ((secondX <= 15 || secondX >= MainFrame.MENU_WIDTH - 30 ||
                                secondY <= 15 || secondY >= MainFrame.MENU_HEIGHT - 50)) {
                            vecX = 0;
                            vecY = 0;
                            accident = true;
                        } else {
                            for (Brick brick : getBricks()) {
                                if ((new Rectangle((int) (secondX - 8), (int) (secondY - 8), 16, 16)).getBounds().intersects((brick).getBounds())) {
                                    vecX = 0;
                                    vecY = 0;
                                    accident = true;
                                }
                            }
                        }
                        secondX += vecX;
                        secondY += vecY;
                    }
                }
                }else {
                    secondX = getMouseHandler().getFirstX();
                    secondY = getMouseHandler().getFirstY();
                    vecX = -new Random().nextInt(10);
                    vecY = -new Random().nextInt(10);
                    double norm = Math.sqrt(Math.pow(vecX,2)+Math.pow(vecY,2));
                    if (norm != 0) {
                        vecX = vecX / norm;
                        vecY = vecY / norm;
                        boolean accident = false;
                        while (!accident) {
                            if ((secondX <= 15 || secondX >= MainFrame.MENU_WIDTH - 30 ||
                                    secondY <= 15 || secondY >= MainFrame.MENU_HEIGHT - 50)) {
                                vecX = 0;
                                vecY = 0;
                                accident = true;
                            } else {
                                for (Brick brick : getBricks()) {
                                    if ((new Rectangle((int) (secondX - 8), (int) (secondY - 8), 16, 16)).getBounds().intersects((brick).getBounds())) {
                                        vecX = 0;
                                        vecY = 0;
                                        accident = true;
                                    }
                                }
                            }
                            secondX += vecX;
                            secondY += vecY;
                        }
                    }
                }


//                secondX = getMouseHandler().getFirstX();
//                secondY = getMouseHandler().getFirstY();
//                PointerInfo pointerInfo = MouseInfo.getPointerInfo();
//                Point mouseLocation = pointerInfo.getLocation();
//                int posX = (int) mouseLocation.getX();
//                int posY = (int) mouseLocation.getY();
////                System.out.println("posX " + posX);
////                System.out.println("posY " + posY);
//                int norm = (int) Math.sqrt((posX - getMouseHandler().getPosX())*(posX - getMouseHandler().getPosX()) +
//                        (posY - getMouseHandler().getPosY())*(posY - getMouseHandler().getPosY()));
//                if (norm != 0) {
//                    vecX = (-(posX - getMouseHandler().getPosX()) * 8 / norm);
//                    vecY = (-(posY - getMouseHandler().getPosY()) * 8 / norm);
//                }
//                secondX = getMouseHandler().getFirstX();
//                secondY = getMouseHandler().getFirstY();
//                while (! (secondX <= 10 || secondX >= MainFrame.MENU_WIDTH - 25 ||
//                        secondY <= 15 || secondY >= MainFrame.MENU_HEIGHT - 50)){
//                    secondX += vecX;
//                    secondY += vecY;
//                }
            }

            if (minBrick.getY() > 75) {
                for (int i = 0; i < numberOfBrick; i++) {
                    int j = bricks.size();
                    Brick brick = new Brick(new Random().nextInt(MainFrame.MENU_WIDTH/numberOfBrick - 50)+
                            MainFrame.MENU_WIDTH/numberOfBrick*i, 0, 60, 40, (int) (round * rate),j ,this);
                    bricks.add(brick);
                    minBrick = brick;
                }
            }
        }else {
            sigmaTime = elapsedTime;
            startTime = System.nanoTime()/1000000000L;
        }
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public LinkedList<Brick> getBricks() {
        return bricks;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getState() {
        return state;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }


    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public int getAddBall() {
        return addBall;
    }

    public void setAddBall(int addBall) {
        this.addBall = addBall;
    }

    public boolean isMoveFast() {
        return moveFast;
    }

    public void setMoveFast(boolean moveFast) {
        this.moveFast = moveFast;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getFirstTimeMoveFast() {
        return firstTimeMoveFast;
    }

    public void setFirstTimeMoveFast(int firstTimeMoveFast) {
        this.firstTimeMoveFast = firstTimeMoveFast;
    }

    public boolean isStopMoveFast() {
        return stopMoveFast;
    }

    public void setStopMoveFast(boolean stopMoveFast) {
        this.stopMoveFast = stopMoveFast;
    }

    public boolean isStrength() {
        return strength;
    }

    public void setStrength(boolean strength) {
        this.strength = strength;
    }

    public int getFirstTimeStrength() {
        return firstTimeStrength;
    }

    public void setFirstTimeStrength(int firstTimeStrength) {
        this.firstTimeStrength = firstTimeStrength;
    }

    public boolean isStopStrength() {
        return stopStrength;
    }

    public void setStopStrength(boolean stopStrength) {
        this.stopStrength = stopStrength;
    }

    public boolean isDeez() {
        return deez;
    }

    public void setDeez(boolean deez) {
        this.deez = deez;
    }

    public boolean isDeezRound() {
        return deezRound;
    }

    public void setDeezRound(boolean deezRound) {
        this.deezRound = deezRound;
    }

    public LinkedList<SpecialItem> getSpecialItems() {
        return specialItems;
    }

    public boolean isZelzele() {
        return zelzele;
    }

    public void setZelzele(boolean zelzele) {
        this.zelzele = zelzele;
    }

    public int getFirstTimeZelzele() {
        return firstTimeZelzele;
    }

    public void setFirstTimeZelzele(int firstTimeZelzele) {
        this.firstTimeZelzele = firstTimeZelzele;
    }

    public boolean isStopZelzele() {
        return stopZelzele;
    }

    public void setStopZelzele(boolean stopZelzele) {
        this.stopZelzele = stopZelzele;
    }

    public boolean isNoor() {
        return noor;
    }

    public void setNoor(boolean noor) {
        this.noor = noor;
    }

    public int getFirstTimeNoor() {
        return firstTimeNoor;
    }

    public void setFirstTimeNoor(int firstTimeNoor) {
        this.firstTimeNoor = firstTimeNoor;
    }

    public boolean isStopNoor() {
        return stopNoor;
    }

    public void setStopNoor(boolean stopNoor) {
        this.stopNoor = stopNoor;
    }
}
