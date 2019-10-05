package yogibear;

import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javax.swing.ImageIcon;

public class Ranger extends Moveable{
    
    private static int width = 40;
    private static int height = 70;
    private Circle circle = new Circle();
    
    /**
     * Új Rangert hoz létre
     * @param x X koordináta
     * @param y Y koordináta
     */
    public Ranger(int x, int y){
        super(x, y, width, height, new ImageIcon("data/images/ranger.png").getImage(), 8);
        circle.setRadius(YogiBear.getYogiBearWidth() * 1.5);
    }
    
    /**
     * A Ranger körül lévő kör koordinátáit állítja be, középpont a Ranger közepe.
     */
    public void circleCoords(){
        circle.setCenterX(x + (height / 2));
        circle.setCenterY(y + (width / 2));
    }
    
    /**
     * Ellenőrzi, hogy a képernyő szélére ért-e, ha igen, akkor az ellenkező irányba fordul.
     * Ellenőrzi azt is, hogy fának ütközik-e, ha igen, akkor az ellenkező irányba fordul.
     * @param sprites Sprite-ok tömbje, csak a fákra van belőle szüksége.
     */
    public void setOwnDirection(ArrayList<Sprite> sprites){
        boolean turned = false;
        if(getDirection() == Direction.LEFT){
            if(getX() - getStep() <= 0){
                setDirection(getDirection().opposite());
                turned = true;
            }
        }
        else if(getDirection() == Direction.RIGHT){
            if(getX() + getStep() + getWidth() >= GameEngine.getW_WIDTH()){
                setDirection(getDirection().opposite());
                turned = true;
            }
        }
        else if(getDirection() == Direction.UP){
            if(getY() - getStep() <= 0){
                setDirection(getDirection().opposite());
                turned = true;
            }
        }
        else if(getDirection() == Direction.DOWN){
            if(getY() + getStep() + getHeight() >= GameEngine.getW_HEIGHT()){
                setDirection(getDirection().opposite());
                turned = true;
            }
        }
        
        if(!turned){
            for(Sprite sprite : sprites){
                if(!this.equals(sprite)){
                    if(sprite instanceof Tree){
                        if(collides(sprite)){
                            setDirection(getDirection().opposite());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static int getRangerWidth() {
        return width;
    }

    public static int getRangerHeight() {
        return height;
    }

    public Circle getCircle() {
        return circle;
    }
    
}
