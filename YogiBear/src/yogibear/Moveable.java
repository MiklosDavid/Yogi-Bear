package yogibear;

import java.awt.Image;

/**
 * A mozgó Sprite-ok örökölnek tőle.
 * @author birom
 */
public class Moveable extends Sprite{
    
    protected Direction direction;
    protected final int step;
    
    /**
     * A Moveable class konstruktora.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param image
     * @param step 
     */
    public Moveable(int x, int y, int width, int height, Image image, int step){
        super(x, y, width, height, image);
        this.step = step;
    }
    
    /**
     * Ha az adott Sprite iránya nem null, akkor a megadott irányba lépteti.
     */
    public void move(){
        if(direction != null){
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
    
    public Direction getDirection() {
        return direction;
    }

    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getStep() {
        return step;
    }
    
}
