import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenu implements ActionListener{ 
    JFrame frame = new JFrame();
    JLayeredPane  menuLayeredPane = new JLayeredPane();

    JPanel backgroundMenuPanel = new JPanel();
    JPanel choiceMenuPanel = new JPanel();
    JPanel titleMenuPanel = new JPanel();
        
    JButton startButton = new JButton("Start");
    JButton exitButton = new JButton("Exit");

    JLabel titleTextLabel = new JLabel();

    JLabel mainMenuFrameLabel;
        Image mainMenuTVFrame = new ImageIcon(getClass().getResource("/design/menuFrame2.png")).getImage();
        Image scaledMainMenuTVFrame = mainMenuTVFrame.getScaledInstance(990, 665, Image.SCALE_SMOOTH);

    ImageIcon logoIcon = new ImageIcon("/cards/back.png");
                                                                          
    MainMenu(){

        try {
            File file = new File("sound1.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); 
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    
        mainMenuFrameLabel = new JLabel(new ImageIcon(scaledMainMenuTVFrame));

        startButton.setBackground(new Color(0x309E71));
        startButton.setForeground(new Color(0xFFFF00));
        startButton.setFont(new Font("Rockwell", Font.BOLD, 60));
        startButton.setPreferredSize(new Dimension(500, 80));
        startButton.setBorder(null);
        startButton.addActionListener(this);

        exitButton.setBackground(new Color(0x309E71));
        exitButton.setForeground(new Color(0xFFFF00));
        exitButton.setFont(new Font("Rockwell", Font.BOLD, 60));
        exitButton.setPreferredSize(new Dimension(500, 80));
        exitButton.setBorder(null);
        exitButton.addActionListener(this);

        titleTextLabel.setText("Courageous Search");
        titleTextLabel.setFont(new Font("Rockwell", Font.BOLD, 60));
        titleTextLabel.setForeground(new Color(0xFFFF00)); 

        backgroundMenuPanel.setLayout(new BorderLayout());
        backgroundMenuPanel.add(mainMenuFrameLabel,BorderLayout.CENTER);
        backgroundMenuPanel.setBackground(new Color(0x309E71));
        backgroundMenuPanel.setBounds(-5,-18, 1000,700);

        choiceMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        choiceMenuPanel.setBounds(250, 280, 500, 250);
        choiceMenuPanel.setBackground(new Color(0x309E71));
        choiceMenuPanel.add(startButton);
        choiceMenuPanel.add(exitButton);

        titleMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titleMenuPanel.add(titleTextLabel);
        titleMenuPanel.setBounds(200, 160, 600, 120);
        titleMenuPanel.setBackground(new Color(0x309E71));

        menuLayeredPane.setLayout(null);
        menuLayeredPane.add(backgroundMenuPanel, JLayeredPane.DEFAULT_LAYER);
        menuLayeredPane.add(choiceMenuPanel, JLayeredPane.PALETTE_LAYER);
        menuLayeredPane.add(titleMenuPanel, JLayeredPane.PALETTE_LAYER);

        frame.setIconImage(logoIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setTitle("Courageous Search Game");
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x309E71));
        frame.setLayout(new BorderLayout(0,0));
        frame.add(menuLayeredPane, BorderLayout.CENTER);
        frame.setVisible(true);
        
    }
    public void resetBg(){
        backgroundMenuPanel.setBackground(new Color(0x309E71));
        frame.getContentPane().setBackground(new Color(0x309E71));
    }
    public void changeBg(int mode){
        switch(mode){
            case 1:
                backgroundMenuPanel.setBackground(new Color(0x00FF80)); //0x9AF5C3
                frame.getContentPane().setBackground(new Color(0x00FF80)); //0x00FF80
            break;

            case 2:
                backgroundMenuPanel.setBackground(new Color(0xF1FA98));
                frame.getContentPane().setBackground(new Color(0xF1FA98));
            break;

            case 3:
                backgroundMenuPanel.setBackground(new Color(0xFF5656));
                frame.getContentPane().setBackground(new Color(0xFF5656));
            break;
        }
        frame.revalidate();
        frame.repaint();
    }
    public void callMainMenu(){
        menuLayeredPane.removeAll();
        menuLayeredPane.add(backgroundMenuPanel, JLayeredPane.DEFAULT_LAYER);
        menuLayeredPane.add(choiceMenuPanel, JLayeredPane.PALETTE_LAYER);
        menuLayeredPane.add(titleMenuPanel, JLayeredPane.PALETTE_LAYER);

        frame.revalidate();
        frame.repaint();
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton){
            menuLayeredPane.remove(choiceMenuPanel);
            menuLayeredPane.remove(titleMenuPanel);

            LevelSelect levelSelect = new LevelSelect(this);

            menuLayeredPane.add(levelSelect, JLayeredPane.PALETTE_LAYER);

            frame.revalidate();
            frame.repaint();
        }
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
