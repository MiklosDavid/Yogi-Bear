package yogibear;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameEngine extends JPanel{
    
    private final int DELAY = 20;
    private final int INFO_REFRESH_MS = 100;
    private static int W_WIDTH;
    private static int W_HEIGHT;
    
    private int picnicCollected = 0;
    private double time = 0;
    private Timer newFrameTimer;
    private Timer infoRefresh;
    private YogiBear yogiBear;
    private Image background;
    private ArrayList<Sprite> sprites;
    private Picnic toRemove = null;
    private JLabel info;
    private GUI gui;
    private Generator generator;
    private boolean pause = true;
    private GameMode gamemode = GameMode.MEDIUM;
    
    /**
     * Létrehoz egy új GameEngine-t.
     * @param gui Paraméterként megkapja a
     */
    public GameEngine(GUI gui){
        super();
        this.info = gui.getInfo();
        this.gui = gui;
        background = new ImageIcon("data/images/background.jpg").getImage();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        W_WIDTH = gd.getDisplayMode().getWidth();
        W_HEIGHT = gd.getDisplayMode().getHeight();
        setFocusable(true);
        requestFocusInWindow();
        repaint();
        new Menu(this);
    }
    
    /**
     * Elindítja a játékot.
     * @param gamemode lehet EASY, MEDIUM, HARD
     */
    public void start(GameMode gamemode){
        this.gamemode = gamemode;
        pause = false;
        addKeyListener(new ControlListener(this));
        yogiBear = new YogiBear(0, 0);
        generator = new Generator(gamemode, yogiBear);
        sprites = generator.generate();
        info.setText("Lifes: " + yogiBear.getLifes() + " Picnic baskets: " + picnicCollected + " / " + generator.getPicnic_num() + "Points: " + yogiBear.getPoints() + " Time elapsed: " + Math.round(time * 10.0) / 10.0 + " s");
        repaint();
        newFrameTimer = new Timer(DELAY, new NewFrameListener(this));
        newFrameTimer.start();
        infoRefresh = new Timer(INFO_REFRESH_MS, new InforRefreshListener());
        infoRefresh.start();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, W_WIDTH, W_HEIGHT, null);
        if(!pause){
            for(Sprite sprite : sprites){
                sprite.draw(g);
            }
            if(toRemove != null){
                sprites.remove(toRemove);
                toRemove = null;
            }
        }
    }
    
    /**
     * Frissíti a felül megjelenő információkat.
     */
    class InforRefreshListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            time += INFO_REFRESH_MS / 1000.0;
            yogiBear.setPoints(yogiBear.getPoints() - (int)((Math.round((INFO_REFRESH_MS / 1000.0) * 10.0) / 10.0) * 10));
            info.setText("Lifes: " + yogiBear.getLifes() + " Picnic baskets: " + picnicCollected + " / " + generator.getPicnic_num() + "Points: " + yogiBear.getPoints() + " Time elapsed: " + Math.round(time * 10.0) / 10.0 + " s");
        }
        
    }
    
    /**
     * Új Frame-t generál, ha a játék nincs megállítva.
     * Végig iterál a Sprite tömbön, amiben az összes Sprite benne van.
     * A mozgatható Sprite-ok új koordinátát kapnak.
     */
    class NewFrameListener implements ActionListener{
        
        private GameEngine engine;
        private Tree treeCollided = null;
        private Direction blockedDirection = null;
        
        NewFrameListener(GameEngine engine){
            this.engine = engine;
        }
        
        @Override
        public void actionPerformed(ActionEvent event){
            if(!pause){
                boolean caught = false;
                for(Sprite sprite : sprites){
                    if(sprite instanceof Moveable){
                        if(sprite instanceof Ranger){
                            ((Ranger) sprite).setOwnDirection(sprites);
                            ((Ranger) sprite).circleCoords();
                        }else if(sprite.equals(yogiBear)){
                            for(Sprite sp : sprites){
                                if(sp instanceof Picnic){
                                    if(sprite.collides(sp)){
                                        toRemove = (Picnic)sp;
                                        picnicCollected++;
                                        yogiBear.setPoints(yogiBear.getPoints() + 1000);
                                        info.setText("Lifes: " + yogiBear.getLifes() + " Picnic baskets: " + picnicCollected + " / " + generator.getPicnic_num() + "Points: " + yogiBear.getPoints() + " Time elapsed: " + Math.round(time * 10.0) / 10.0 + " s");
                                        if(picnicCollected == generator.getPicnic_num()){
                                            infoRefresh.stop();
                                            pause = true;
                                            new ResultFrame(engine, true);
                                            newFrameTimer.stop();
                                        }
                                        break;
                                    }
                                }else if(sp instanceof Ranger){
                                    if(yogiBear.isCaught((Ranger)sp)){
                                        yogiBear.setLifes(yogiBear.getLifes() - 1);
                                        caught = true;
                                        treeCollided = null;
                                        blockedDirection = null;
                                        break;
                                    }
                                }else if(sp instanceof Tree){
                                    if(yogiBear.collides(sp)){
                                        if(treeCollided == null){
                                            treeCollided = (Tree)sp;
                                            blockedDirection = yogiBear.getDirection();
                                        }
                                    }else{
                                        if(sp.equals(treeCollided)){
                                            treeCollided = null;
                                            blockedDirection = null;
                                        }
                                    }
                                }
                            }
                        }
                        if(sprite instanceof Ranger)((Ranger) sprite).move();
                        else if(sprite instanceof YogiBear) yogiBear.moved(blockedDirection);
                    }
                }
                if(caught){
                    if(yogiBear.getLifes() > 0){
                        yogiBear.setX(0);
                        yogiBear.setY(0);
                    }else{
                        infoRefresh.stop();
                        pause = true;
                        new ResultFrame(engine, false);
                        newFrameTimer.stop();
                    }
                }
                
                
                repaint();
            }
        }
    }
    
    /**
     * Gomblenyomás EventListenere.
     * Ha lenyomunk egy irányító gombot, akkor az irány belekerül egy tömbbe. Mindig abba az irányba halad YogiBear,
     * ami a tömb tetején van. Ha felengedjük a gombot, akkor az adott irány törlődik a veremből. Ha a verem üres,
     * akkor YogiBear Direction tpisuú paramétere null lesz, és nem megy semerre.
     */
    class ControlListener implements KeyListener{
        
        private ArrayList<Direction> keysPressed = new ArrayList<Direction>();
        private GameEngine engine;
        
        ControlListener(GameEngine engine){
            this.engine = engine;
        }
        
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                yogiBear.setDirection(Direction.LEFT);
                keysPressed.add(Direction.LEFT);
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                yogiBear.setDirection(Direction.RIGHT);
                keysPressed.add(Direction.RIGHT);
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                yogiBear.setDirection(Direction.UP);
                keysPressed.add(Direction.UP);;
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                yogiBear.setDirection(Direction.DOWN);
                keysPressed.add(Direction.DOWN);
            }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(!pause){
                    infoRefresh.stop();
                    new Menu(engine, true);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                keysPressed.removeAll(Collections.singleton(Direction.LEFT));
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                keysPressed.removeAll(Collections.singleton(Direction.RIGHT));
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                keysPressed.removeAll(Collections.singleton(Direction.UP));
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                keysPressed.removeAll(Collections.singleton(Direction.DOWN));
            }

            if(keysPressed.size() != 0){
                yogiBear.setDirection(keysPressed.get(keysPressed.size() - 1));
            }else{
                yogiBear.setDirection(null);
            }
        }
    }

    public static int getW_WIDTH() {
        return W_WIDTH;
    }

    public static int getW_HEIGHT() {
        return W_HEIGHT;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    public GameMode getGamemode() {
        return gamemode;
    }

    public GUI getGui() {
        return gui;
    }

    public Timer getInfoRefresh() {
        return infoRefresh;
    }

    public YogiBear getYogiBear() {
        return yogiBear;
    }
    
}
