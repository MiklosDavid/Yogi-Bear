package yogibear;

import javax.swing.ImageIcon;

/**
 * Fa.
 * @author birom
 */
public class Tree extends Sprite{
    
    private static int width = 72;
    private static int height = 96;
    
    public Tree(int x, int y) {
        super(x, y, width, height, new ImageIcon("data/images/tree.png").getImage());
    }

    public static int getTreeWidth() {
        return width;
    }

    public static int getTreeHeight() {
        return height;
    }
    
}
