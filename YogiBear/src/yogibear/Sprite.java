package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import javafx.scene.shape.Rectangle;

public class Sprite {
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    private static int numOfInstances = 0;
    private final int id;

    /**
     * Létrehoz egy új Sprite-ot
     * @param x X koordináta
     * @param y Y koordináta
     * @param width Sprite szélessége
     * @param height Sprite magassága
     * @param image Sprite képe
     */
    public Sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        numOfInstances++;
        this.id = numOfInstances;
    }
    
    /**
     * Megrajzolja a sprite-ot
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
    /**
     * Ellenőrzi, hogy a Sprite ütközött-e a paraméterként kapott Sprite-al
     * @param other Másik Sprite
     * @return Visszatér azzal, hogy van-e ütközés.
     */
    public boolean collides(Sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect;
        if(other instanceof Tree){
            otherRect = new Rectangle(other.x + 9, other.y + 68, other.width - 9, other.height - 68);
        }else{
            otherRect = new Rectangle(other.x, other.y, other.width, other.height);
        }
        return rect.intersects(otherRect.getBoundsInLocal());
    }
    
    /**
     * Az equals függvény felüldefiniálása: ID alapján ellenőrzi, hogy egyezik-e a két Sprite
     * @param other A paraméterként kapott Object csak Sprite tipusú lehet, különben false-al tér visszas.
     * @return Visszatér, hogy egyezik-e a két Sprite.
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Sprite){
            return this.id == ((Sprite) other).getId();
        }else return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }
    
}
