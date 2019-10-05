package yogibear;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Picnic extends Sprite{
    
    private static int width = 27;
    private static int height = 25;
    
    /**
     * Pikink kosár konstruktora.
     * @param x X koordináta
     * @param y Y koordináta
     */
    public Picnic(int x, int y) {
        super(x, y, width, height, new ImageIcon("data/images/picnic.png").getImage());
    }

    public static int getPicnicWidth() {
        return width;
    }

    public static int getPicnicHeight() {
        return height;
    }
    
}
