import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Ending extends JPanel implements ActionListener{
    JPanel buttonPanel = new JPanel();
    JPanel textPanel = new JPanel();

    JLabel homeIcon = new JLabel();
    JLabel retryIcon = new JLabel();

    JLabel endText = new JLabel();
    JButton retryBut = new JButton();
    JButton toMenuBut = new JButton();

    private LevelSelect levelSelect;
    private MainMenu mainMenu;
    int levelType;
    
    Border border = BorderFactory.createLineBorder(Color.BLACK,3);
    Ending(LevelSelect levelSelect, MainMenu mainMenu, int levelType, String text){
        this.levelSelect = levelSelect;
        this.mainMenu = mainMenu;
        this.levelType = levelType;

        endText.setBounds(125, 37, 600, 75);
        endText.setText(text);
        //endText.setVerticalAlignment(JLabel.CENTER);
        //endText.setHorizontalAlignment(JLabel.CENTER);
        endText.setFont(new Font("Rockwell", Font.BOLD, 50));
        endText.setForeground(new Color(0x252121));

        textPanel.setBounds(0, 0,500,150);
        textPanel.setBackground(new Color(0x64707C));
        textPanel.setBorder(border);
        textPanel.setLayout(null);
        textPanel.add(endText);

        Image retryImage = new ImageIcon(getClass().getResource("/design/restart.png")).getImage();
        Image scaledRetryImage = retryImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        retryIcon = new JLabel(new ImageIcon(scaledRetryImage));
        retryIcon.setBounds(0, 0, 100, 100);
        retryBut.add(retryIcon);
        retryBut.setBackground(new Color(0x252121));
        retryBut.setBorder(border);
        retryBut.setPreferredSize(new Dimension(100,100));
        retryBut.addActionListener(this);

        Image homeImage = new ImageIcon(getClass().getResource("/design/home.png")).getImage();
        Image scaledHomeImage = homeImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        homeIcon = new JLabel(new ImageIcon(scaledHomeImage));
        homeIcon.setBounds(0,0,100,100);
        toMenuBut.add(homeIcon);
        toMenuBut.setBackground(new Color(0x252121));
        toMenuBut.setBorder(border);
        toMenuBut.setPreferredSize(new Dimension(100,100)); 
        toMenuBut.addActionListener(this);

        buttonPanel.setBounds(0, 150,500,150);
        buttonPanel.setBackground(new Color(0x64707C));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(border);
        buttonPanel.add(retryBut);
        buttonPanel.add(toMenuBut);
        
        this.add(textPanel);
        this.add(buttonPanel);
        this.setBorder(border);
        this.setBounds(100, 35, 500, 300);
        this.setLayout(null);
        this.setBackground(new Color(0x64707C));
        this.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()  == retryBut){
            levelSelect.playAgain(levelType);
        }
        if(e.getSource() == toMenuBut){
            mainMenu.resetBg();
            mainMenu.callMainMenu();
        }
    }
}
