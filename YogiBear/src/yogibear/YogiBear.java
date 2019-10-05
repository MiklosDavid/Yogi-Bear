package yogibear;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javax.swing.ImageIcon;

public class YogiBear extends Moveable{
    
    private int lifes = 4;
    private final static int width = 50;
    private final static int height = 50;
    private int points = 0;
    
    /**
     * Létrehoz egy új YogiBear-t
     * @param x x koordináta
     * @param y y koordináta
     */
    public YogiBear(int x, int y){
        super(x, y, width, height, new ImageIcon("data/images/yogibear.png").getImage(), 8);
    }
    
    /**
     * Ellenőrzi, hogy a paraméterként kapott Ranger elkapta-e YogiBear-t.
     * @param ranger Ranger tipusú objektum
     * @return A visszatérési érték megadja, hogy az adott Ranger elkapta-e YogiBear-t.
     */
    public boolean isCaught(Ranger ranger){
        Circle circle = ranger.getCircle();
        Rectangle rect = new Rectangle(x, y, width, height);
        if(rect.intersects(circle.getBoundsInLocal()))
            return true;
        else return false;
    }
    
    /**
     * YogiBear mozgatását teszi lehetővé
     * @param blockedDirection A kapott irányban akadály található, erre nem mehet.
     */
    public void moved(Direction blockedDirection){
        if(direction != null && direction != blockedDirection){
                switch (direction) {
                case LEFT:
                    if((x - step) > 0) x -= step;
                    break;
                case RIGHT:
                    if((x + step + width) < GameEngine.getW_WIDTH()) x += step;
                    break;
                case UP:
                    if((y - step) > 0) y -= step;
                    break;
                case DOWN:
                    if((y + step + height) < GameEngine.getW_HEIGHT()) y += step;
                    break;
                default:
                    break;
            }
            
        }
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public static int getYogiBearWidth() {
        return width;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}
