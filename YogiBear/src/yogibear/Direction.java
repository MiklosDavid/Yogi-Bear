package yogibear;

/**
 * Egy Moveable tipusú Sprite ezekbe az irányokba mehet.
 * @author birom
 */
public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;
    
    public Direction opposite(){
        switch(this){
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            default:
                return null;
        }
    }
    
}
