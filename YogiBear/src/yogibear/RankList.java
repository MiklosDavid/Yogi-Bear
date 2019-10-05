package yogibear;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankList extends JFrame{
    
    /**
     * Ezen a JFrame-n a ranglista jelenik meg.
     */
    
    public RankList(){
        
        Database db = new Database();        
        ArrayList<DataStructure> ranklist = db.query();
        db.close();
        
        JPanel panel = new JPanel(new GridLayout(13, 1));
        JPanel titleP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("Top 10 results");
        JButton ok = new JButton("OK");
        ok.addActionListener(new OkPressed());
        
        titleP.add(title);
        panel.add(titleP);
        
        JPanel header = new JPanel(new GridLayout(1, 3));
        header.add(new JLabel("Name "));
        header.add(new JLabel("Difficulty "));
        header.add(new JLabel("Points"));
        panel.add(header);
        
        for(int i = 0; i < 10; i++){
            if(i < ranklist.size()){
                JPanel container = new JPanel(new GridLayout(1, 3));
                container.add(new JLabel(ranklist.get(i).getName()));
                container.add(new JLabel(ranklist.get(i).getDifficulty()));
                container.add(new JLabel(ranklist.get(i).getPoints() + ""));
                panel.add(container);
            }else{
                panel.add(new JLabel());
            }
        }
        panel.add(ok);
        getContentPane().add(panel);
        
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }
    
    class OkPressed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
        
    }
    
    
}
