//import java.awt.*;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Line2D;
//
//public class Aiming {
//    private double secondX;
//    private double secondY;
//    private double vecX = 0;
//    private double vecY = 0;
//
//    private double x;
//    private double y;
//    private Game game;
//    public Aiming(double x, double y , Game game) {
//        this.x = x;
//        this.y = y;
//        this.secondX = x;
//        this.secondY = y;
//        this.game = game;
//    }
//    public void paint(Graphics g){
//        g.setColor(Color.BLACK);
//
//        g.fillOval((int) (x-8), (int) (y-8), 16 , 16);
//        g.fillOval((int) (secondX-8), (int) (secondY-8), 16 , 16);
//
////        int[] xs = new int[2];
////        xs[0] = (int) x;
////        xs[1] = (int) secondX;
////        int[] ys = new int[2];
////        ys[0] = (int) y;
////        ys[1] = (int) secondY;
////        g.fillPolygon(new Polygon(xs , ys , 2));
//
//    //    g.drawLine((int) x, (int) y, (int) secondX, (int) secondY);
//
////        Line2D.Double line = new Line2D.Double(x, y, secondX, secondY);
////        Polygon arrowHead = new Polygon();
////        arrowHead.addPoint((int) x, (int) (y+5));
////        arrowHead.addPoint((int) (x-5), (int) (y-5));
////        arrowHead.addPoint((int) (x+5), (int) (y-5));
////
////        AffineTransform tx = new AffineTransform();
////        double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
////        tx.translate(line.x2, line.y2);
////        tx.rotate(angle - Math.PI / 2d);
////
////        Graphics2D g2d = (Graphics2D) g.create();
////        g2d.setTransform(tx);
////        g2d.fill(arrowHead);
//    }
//    private boolean accident(){
//        if(secondX <= 10 || secondX >= MainFrame.MENU_WIDTH - 25){
//            vecX = 0;
//            vecY = 0;
//            return true;
//        }
//        if(secondY <= 15){
//            vecX = 0;
//            vecY = 0;
//            return true;
//        }
//        if(secondY >= MainFrame.MENU_HEIGHT - 50 ){
//            vecX = 0;
//            vecY = 0;
//            return true;
//        }
//
//        for(Brick brick : game.getBricks()){
//            if(this.getBounds().intersects(( brick).getBounds())){
//                vecX = 0;
//                vecY = 0;
//                return true;
//            }
//        }
//        return false;
//    }
//    public void update(){
//        secondX = x;
//        secondY = y;
//        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
//        Point mouseLocation = pointerInfo.getLocation();
//        int posX = (int) mouseLocation.getX();
//        int posY = (int) mouseLocation.getY();
//        int norm = (int) Math.sqrt((posX - game.getMouseHandler().getPosX())*(posX - game.getMouseHandler().getPosX()) +
//                (posY - game.getMouseHandler().getFirstY())*(posY - game.getMouseHandler().getFirstY()));
//        if (norm != 0) {
//            setVecX((-(posX - game.getMouseHandler().getFirstX()) * 8 / norm)*100);
//            setVecY((-(posY - game.getMouseHandler().getFirstY()) * 8 / norm)*100);
//        }
//        while (!accident()){
//            secondX += vecX;
//            secondY += vecY;
//        }
//        secondX = x;
//        secondY = y;
////        secondY += vecX;
////        secondY += vecY;
////        if(secondX <= 10+40 || secondX >= MainFrame.MENU_WIDTH - 25 - 40){
////            vecX = 0;
////            vecY = 0;
////        }
////        if(secondY <= 15 + 40){
////            vecX = 0;
////            vecY = 0;
////        }
////        if(secondY >= MainFrame.MENU_HEIGHT - 50 - 40 ){
////            vecX = 0;
////            vecY = 0;
////        }
////
////        for(Brick brick : game.getBricks()){
////            if(this.getBounds().intersects(( brick).getBounds())){
////                vecX = 0;
////                vecY = 0;
////            }
////        }
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public Rectangle getBounds(){
//        return new Rectangle((int) (secondX - 8), (int) (secondY - 8), 16 , 16);
//    }
//
//    public double getVecX() {
//        return vecX;
//    }
//
//    public void setVecX(double vecX) {
//        this.vecX = vecX;
//    }
//
//    public double getVecY() {
//        return vecY;
//    }
//
//    public void setVecY(double vecY) {
//        this.vecY = vecY;
//    }
//}
