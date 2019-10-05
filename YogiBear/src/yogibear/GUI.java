package yogibear;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{
    
    private GameEngine engine;
    private JLabel info;
    
    /**
     * Létrehozza a Frame-t.
     */
    public GUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        pack();
        startGame();
        setVisible(true);
    }
    
    /**
     * Alaphelyzetbe állítja a játékteret.
     */
    public void reset(){
        remove(engine);
        startGame();
        validate();
        repaint();
    }
    
    /**
     * Elindítja a játékot.
     */
    public void startGame(){
        info = new JLabel();
        engine = new GameEngine(this);
        getContentPane().add(engine);
        
        info.setOpaque(true);
        info.setBackground(Color.white);
        engine.add(info);
    }

    public JLabel getInfo() {
        return info;
    }
    
}
