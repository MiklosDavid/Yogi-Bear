package yogibear;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A játék végeredményét közli.
 * @author birom
 */

public class ResultFrame extends JFrame{
    
    private JTextField name;
    private GameEngine engine;
    
    /**
     * A ResultFrame konstruktora.
     * @param engine A GameEngine-t megkapja paraméterként.
     * @param isWin Megkapja paraméterként, hogy nyert vagy veszített a játékos.
     */
    public ResultFrame(GameEngine engine, boolean isWin){
        
        this.engine = engine;
        
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel2 = new JPanel(new GridLayout(4, 1));
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        name = new JTextField("", 20);
        JButton ok = new JButton("OK");
        ok.addActionListener(new OkPressed());
        panel2.add(panel3);
        if(isWin)panel3.add(new JLabel("YOU WON!"));
        else panel3.add(new JLabel("YOU LOST!"));
        panel1.add(new JLabel("Your name: "));
        panel1.add(name);
        panel4.add(ok);
        panel5.add(new JLabel("Result: " + engine.getYogiBear().getPoints()));
        
        panel2.add(panel1);
        panel2.add(panel4);
        getContentPane().add(panel2);
        
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Ellenőrzi, hogy a játékos megadta-e a nevét. Ha igen, akkor elmenti a ranglistába. Ha nem, akkor újra kéri a nevet.
     */
    class OkPressed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String n = name.getText();
            if(n.length() < 1){
                JOptionPane.showMessageDialog(null, "The name field is empty.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }else{
                Database db = new Database();
                DataStructure data = new DataStructure(n, engine.getGamemode().toString(), engine.getYogiBear().getPoints());
                db.insert(data);
                db.close();
                engine.getGui().reset();
                dispose();
            }
        }
        
    }
    
}
