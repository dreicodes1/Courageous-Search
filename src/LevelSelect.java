import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LevelSelect extends JPanel implements ActionListener{

    JLayeredPane boardLayeredPane = new JLayeredPane();
    
    JLayeredPane selectLevelPane = new JLayeredPane();
        JLabel selectLabel = new JLabel();
        JButton casualButton = new JButton("Casual (easy)");
        JButton knowledgableButton = new JButton("Knowledable (mid)");
        JButton nerdButton = new JButton("Nerd (hard)");


    private MainMenu mainMenu;
    LevelSelect(MainMenu mainMenu){

        this.mainMenu = mainMenu;

        boardLayeredPane.setBounds(0,0,700,375);
        boardLayeredPane.setLayout(null);

        selectLevelPane.setBounds(0,0,500,380);
        selectLevelPane.setBackground(new Color(0x309E71));
        selectLevelPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        selectLevelPane.setOpaque(true);
        
        selectLabel.setBackground(new Color(0x309E71));
        selectLabel.setForeground(new Color(0xFFFF00));
        selectLabel.setText("Select Difficulty");
        selectLabel.setHorizontalAlignment(JLabel.CENTER);
        selectLabel.setVerticalAlignment(JLabel.CENTER);
        selectLabel.setFont(new Font("ROCKWELL", Font.BOLD, 60));
        selectLabel.setPreferredSize(new Dimension(500, 80));

        casualButton.setBackground(new Color(0x309E71));
        casualButton.setForeground(new Color(0xFFFF00));
        casualButton.setFont(new Font("ROCKWELL", Font.BOLD, 50));
        casualButton.setPreferredSize(new Dimension(500, 80));
        casualButton.setBorder(null);
        casualButton.addActionListener(this);

        knowledgableButton.setBackground(new Color(0x309E71));
        knowledgableButton.setForeground(new Color(0xFFFF00));
        knowledgableButton.setFont(new Font("ROCKWELL", Font.BOLD, 50));
        knowledgableButton.setPreferredSize(new Dimension(500, 80));
        knowledgableButton.setBorder(null);
        knowledgableButton.addActionListener(this);

        nerdButton.setBackground(new Color(0x309E71));
        nerdButton.setForeground(new Color(0xFFFF00));
        nerdButton.setFont(new Font("ROCKWELL", Font.BOLD, 50));
        nerdButton.setPreferredSize(new Dimension(500, 80));
        nerdButton.setBorder(null);
        nerdButton.addActionListener(this);

        selectLevelPane.add(selectLabel);
        selectLevelPane.add(casualButton);
        selectLevelPane.add(knowledgableButton);
        selectLevelPane.add(nerdButton);
    
        this.setLayout(null);
        this.setBackground(new Color(0x309E71));
        this.setBounds(250, 150, 500, 380); 
        this.add(selectLevelPane);
        this.setBackground(Color.BLACK);
    
    }
    public void playAgain(int levelType){
        switch (levelType) {
            case 1:
                boardLayeredPane.removeAll();
                PlayLevel levelCasual = new PlayLevel(this, mainMenu, 1); 
                boardLayeredPane.add(levelCasual);
                this.add(boardLayeredPane);
            break;
            case 2:
                boardLayeredPane.removeAll();
                PlayLevel levelKnowledgable = new PlayLevel(this, mainMenu, 2); 
                boardLayeredPane.add(levelKnowledgable);
                this.add(boardLayeredPane);
            break;
            case 3:
                boardLayeredPane.removeAll();
                PlayLevel levelNerd  = new PlayLevel(this, mainMenu, 3); 
                boardLayeredPane.add(levelNerd);
                this.add(boardLayeredPane);
            break;
        }
    }


    public void actionPerformed(ActionEvent e) {
        this.remove(selectLevelPane);

        this.setBackground(Color.BLACK);
        this.setBounds(145, 150, 700, 375);
        this.setLayout(null);

        if(e.getSource() == casualButton){
            
            mainMenu.changeBg(1);
            selectLevelPane.setBackground(new Color(0x00FF80));
            this.setBackground(new Color(0x00FF80));
            PlayLevel levelCasual = new PlayLevel(this, mainMenu, 1); 
            boardLayeredPane.add(levelCasual);
            this.add(boardLayeredPane);

        } else if(e.getSource() == knowledgableButton){

            mainMenu.changeBg(2); //tanggal
            selectLevelPane.setBackground(new Color(0xF1FA98));
            this.setBackground(new Color(0xF1FA98));
            PlayLevel levelKnowledgable = new PlayLevel(this, mainMenu, 2);
            boardLayeredPane.add(levelKnowledgable);
            this.add(boardLayeredPane);

        } else if(e.getSource() == nerdButton){

            mainMenu.changeBg(3);
            selectLevelPane.setBackground(new Color(0xFF5656));
            this.setBackground(new Color(0xFF5656));
            PlayLevel levelNerd = new PlayLevel(this, mainMenu, 3); //ff5656
            boardLayeredPane.add(levelNerd);
            this.add(boardLayeredPane);

        }

        this.revalidate();
        this.repaint();
    }
}
