import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QuestionPanel extends JPanel implements ActionListener{

    JButton back = new JButton();


    QuestionPanel(){
        
        back.setBounds(0, 0, 50, 50);
        back.setBackground(Color.WHITE);
        
        this.setBounds(50, 25, 600, 325);
        this.setBackground(Color.BLACK);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
    }
}
