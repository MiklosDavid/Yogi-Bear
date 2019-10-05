package yogibear;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A játék menüje.
 * @author birom
 */
public class Menu extends JFrame{
    
    private GameEngine engine;
    private JComboBox gameMode;
    
    public Menu(GameEngine engine){
        new Menu(engine, false);
    }
    
    /**
     * A menü konstruktora. Két féle menü létezik, az egyik akkor jön létre, ha a játék még nem indult el, a másik
     * ha pause-oljuk.
     * @param engine Megkapja a GameEngine-t.
     * @param isPaused Megadja, hogy pause-olás miatt lépünk-e be a menübe.
     */
    public Menu(GameEngine engine, boolean isPaused){
        this.engine = engine;
        if(isPaused) paused();
        else notPaused();
        
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }
    
    /**
     * Ez a menü a játékindítás előtt jön létre. Állíthatunk benne nehézséget.
     */
    private void notPaused(){
        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        JPanel menuP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gameModeP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        GameMode[] modes = {GameMode.EASY, GameMode.MEDIUM, GameMode.HARD};
        JLabel menuL = new JLabel("MENU");
        JLabel gameModeL = new JLabel("Difficulty");
        gameMode = new JComboBox(modes);
        gameMode.setSelectedItem(engine.getGamemode());
        JButton startGame = new JButton("START GAME");
        JButton rankList = new JButton("CHECK RANK LIST");
        JButton exitGame = new JButton("EXIT GAME");
        
        startGame.addActionListener(new StartGameE());
        rankList.addActionListener(new RankListE());
        exitGame.addActionListener(new ExitGameE());
        
        menuP.add(menuL);
        gameModeP.add(gameModeL);
        gameModeP.add(gameMode);
        
        panel.add(menuP);
        panel.add(gameModeP);
        panel.add(startGame);
        panel.add(rankList);
        panel.add(exitGame);
        
        getContentPane().add(panel);
    }
    
    /**
     * Ez a menü pause-olásnál jön elő. Nehézséget nem állíthatunk benne, illetve a másik menühöz képet van egy Játék folytatása
     * opció.
     */
    private void paused(){
        engine.setPause(true);
        
        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        JPanel menuP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel menuL = new JLabel("MENU");
        JButton resumeGame = new JButton("RESUME GAME");
        JButton newGame = new JButton("START NEW GAME");
        JButton rankList = new JButton("CHECK RANK LIST");
        JButton exitGame = new JButton("EXIT GAME");
        
        resumeGame.addActionListener(new ResumeGameE());
        newGame.addActionListener(new NewGameE());
        rankList.addActionListener(new RankListE());
        exitGame.addActionListener(new ExitGameE());
        
        menuP.add(menuL);
        
        panel.add(menuP);
        panel.add(resumeGame);
        panel.add(newGame);
        panel.add(rankList);
        panel.add(exitGame);
        
        getContentPane().add(panel);
    }
    
    class StartGameE implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            engine.start((GameMode)gameMode.getSelectedItem());
            dispose();
        }
        
    }
    
    class ResumeGameE implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            engine.setPause(false);
            engine.getInfoRefresh().start();
            dispose();
        }
        
    }
    
    class NewGameE implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            engine.getGui().reset();
            dispose();
        }
        
    }
    
    class RankListE implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new RankList();
        }
        
    }
    
    class ExitGameE implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }
    
}
