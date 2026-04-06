import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class PlayLevel extends JPanel implements ActionListener{
    
    ArrayList<Integer> shufNum = new ArrayList<>();
    ArrayList<Integer> pairedCards = new ArrayList<>();

    JLayeredPane boardLayeredPane = new JLayeredPane();
        JPanel cardLayer = new JPanel();
        JPanel backLayer = new JPanel();

    JPanel rightGui = new JPanel();
        JButton retryButton = new JButton();
            JLabel retryIcon = new JLabel();
        JPanel heartPanel = new JPanel();
            JLabel[] heart = new JLabel[5];
        JLabel livesText = new JLabel();

    JPanel leftGui = new JPanel();
        JButton homeButton = new JButton();
            JLabel homeIcon = new JLabel();
        JLabel errorText = new JLabel();

    JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel();
        JButton buttonA = new JButton();
        JButton buttonB = new JButton();
        JButton buttonC = new JButton();
        JButton buttonD = new JButton();

    int heartCount;
    int errorCount = 0;

    JLabel tile;
    JButton[] backTile;
    int t = 0;
    int clickIndex = -1;

    boolean wait = false;
    int click1 = -1;
    int click2 = 0;
    int question = 0;

    int buttonNum, rows, cols, boardWidth, boardHeight, cardWidth, cardHeight, pairNum, positionX, chanceCardNum, mode;

    class QuestionSet{

            String question;
            String choiceA;
            String choiceB;
            String choiceC;
            String choiceD;
            String ans;  

            QuestionSet(String question, String choiceA, String choiceB, String choiceC, String choiceD, String ans){
                this.question = question;
                this.choiceA = choiceA;
                this.choiceB = choiceB;
                this.choiceC = choiceC;
                this.choiceD = choiceD;
                this.ans = ans;
            }
        }
    
    Random rand = new Random();
    int questionNum;
    boolean answering;

    QuestionSet[] questionSet = new QuestionSet[20];
    ArrayList<Integer> doneQuestion = new ArrayList<>();

    private LevelSelect levelSelect;
    private MainMenu mainMenu;
    int levelType;
    Border border = BorderFactory.createLineBorder(Color.BLACK,3);
    Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE,3);
    Border heartBorder = BorderFactory.createLineBorder(Color.RED,1);

    
    PlayLevel(LevelSelect levelSelect, MainMenu mainMenu, int levelType){ 

        this.levelSelect = levelSelect;
        this.mainMenu = mainMenu;
        this.levelType = levelType;

        questionSet[0] = new QuestionSet("What is the capital of Australia?", "A) Sydney", "B) Melbourne", "C) Canberra", "D) Brisbane", "C) Canberra");
        questionSet[1] = new QuestionSet("Which planet is known as the Red Planet?", "A) Venus", "B) Mars", "C) Jupiter", "D) Saturn", "B) Mars");
        questionSet[2] = new QuestionSet("Who painted the Mona Lisa?", "A) Vincent van Gogh", "B) Leonardo da Vinci", "C) Pablo Picasso", "D) Michelangelo", "B) Leonardo da Vinci");
        questionSet[3] = new QuestionSet("What is the largest mammal in the world?", "A) Elephant", "B) Blue Whale", "C) Giraffe", "D) Orca", "B) Blue Whale");
        questionSet[4] = new QuestionSet("Which gas do plants absorb from the atmosphere?", "A) Oxygen", "B) Nitrogen", "C) Carbon Dioxide", "D) Hydrogen", "C) Carbon Dioxide");
        questionSet[5] = new QuestionSet("What is the hardest natural substance on Earth?", "A) Gold", "B) Diamond", "C) Quartz", "D) Iron", "B) Diamond");
        questionSet[6] = new QuestionSet("Who wrote Romeo and Juliet?", "A) William Shakespeare", "B) Charles Dickens", "C) Mark Twain", "D) Jane Austen", "A) William Shakespeare");
        questionSet[7] = new QuestionSet("How many continents are there on Earth?", "A) 5", "B) 6", "C) 7", "D) 8", "C) 7");
        questionSet[8] = new QuestionSet("What is the smallest prime number?", "A) 0", "B) 1", "C) 2", "D) 3", "C) 2");
        questionSet[9] = new QuestionSet("Which ocean is the largest?", "A) Atlantic Ocean", "B) Pacific Ocean", "C) Indian Ocean", "D) Arctic Ocean", "B) Pacific Ocean");
        questionSet[10] = new QuestionSet("What is the currency of Japan?", "A) Yen", "B) Yuan", "C) Won", "D) Ringgit", "A) Yen");
        questionSet[11] = new QuestionSet("Which blood type is known as the universal donor?", "A) A+", "B) O-", "C) AB+", "D) B-", "B) O-");
        questionSet[12] = new QuestionSet("What is the chemical symbol for gold?", "A) Go", "B) Au", "C) Ag", "D) Gd", "B) Au");
        questionSet[13] = new QuestionSet("Which country gifted the Statue of Liberty to the USA?", "A) England", "B) France", "C) Spain", "D) Germany", "B) France");
        questionSet[14] = new QuestionSet("In computing, what does 'CPU' stand for?", "A) Central Processing Unit", "B) Computer Power Utility", "C) Core Program Unit", "D) Central Programming Unit", "A) Central Processing Unit");
        questionSet[15] = new QuestionSet("Which is the longest river in the world?", "A) Amazon", "B) Nile", "C) Yangtze", "D) Mississippi", "A) Amazon");
        questionSet[16] = new QuestionSet("What language has the most native speakers worldwide?", "A) English", "B) Mandarin Chinese", "C) Hindi", "D) Spanish", "B) Mandarin Chinese");
        questionSet[17] = new QuestionSet("What is the square root of 144?", "A) 10", "B) 11", "C) 12", "D) 13", "C) 12");
        questionSet[18] = new QuestionSet("Which organ in the human body produces insulin?", "A) Liver", "B) Pancreas", "C) Kidney", "D) Heart", "B) Pancreas");
        questionSet[19] = new QuestionSet("Which element has the chemical symbol 'O'?", "A) Oxygen", "B) Osmium", "C) Ozone", "D) Oxide", "A) Oxygen");
        
        switch (levelType){ 
            case 1:
                buttonNum = 9;
                rows = 3;
                cols = 3;
                boardWidth = 375;
                boardHeight = 375;
                cardWidth = 125;
                cardHeight = 125;
                pairNum = 4;
                positionX = 165;
                heartCount = 3;
                mode = 1;

                chanceCardNum = 1;
            break;

            case 2:
                buttonNum = 25;
                rows = 5;
                cols = 5;
                boardWidth = 375;
                boardHeight = 375;
                cardWidth = 75;
                cardHeight = 75;
                pairNum = 11;
                positionX = 165;
                heartCount = 5;
                mode = 2;

                chanceCardNum = 3;
            break;

            case 3:
                buttonNum = 35;
                rows = 5;
                cols = 7;
                boardWidth = 525;
                boardHeight = 375;
                cardWidth = 75;
                cardHeight = 75;
                pairNum = 15;
                positionX = 88;
                heartCount = 5;
                mode = 3;

                chanceCardNum = 5;
            break;
        }
        //color
        switch(mode){
            case 1:
                 boardLayeredPane.setBackground(new Color(0x00FF80)); //0x00FF80
                 cardLayer.setBackground(new Color(0x00FF80));
                 heartPanel.setBackground(new Color(0x00FF80));
                 livesText.setForeground(new Color(0x00FF80));
            break;
            case 2:
                 boardLayeredPane.setBackground(new Color(0xF1FA98));
                 cardLayer.setBackground(new Color(0xF1FA98));
                 heartPanel.setBackground(new Color(0xF1FA98));
                 livesText.setForeground(new Color(0xF1FA98));
            break;
            case 3:
                 boardLayeredPane.setBackground(new Color(0xFF5656));
                 cardLayer.setBackground(new Color(0xFF5656));
                 heartPanel.setBackground(new Color(0xFF5656));
                 livesText.setForeground(new Color(0xFF5656));
            break;

        }

        backTile = new JButton[buttonNum]; //9 , 25 , 35

        cardLayer.setLayout(new GridLayout(rows,cols,5,5)); //3,3 - 5,5 - 5,7
        cardLayer.setBounds(positionX,0,boardWidth,boardHeight);  // 375,375 - 375,375 - 525,375

        backLayer.setLayout(new GridLayout(rows,cols,5,5));
        backLayer.setBounds(positionX,0,boardWidth,boardHeight);    
        backLayer.setOpaque(false);    

        boardLayeredPane.setBounds(0,0,700,375);
        boardLayeredPane.setOpaque(true);
        boardLayeredPane.add(cardLayer, JLayeredPane.DEFAULT_LAYER);
        boardLayeredPane.add(backLayer, JLayeredPane.PALETTE_LAYER);

        for(int i = 1 ; i <= pairNum ; i++){ //4, 12, 17 
            shufNum.add(i);
            shufNum.add(i);
        }
        switch(levelType){
           case 1: shufNum.add(0); break;
           case 2: for(int i = 0 ; i < 3 ; i++) {shufNum.add(0);} break;
           case 3: for(int i = 0 ; i < 5 ; i++) {shufNum.add(0);} break;
        }
        Collections.shuffle(shufNum);

        for(int cardNum : shufNum){

            Image cardImage = new ImageIcon(getClass().getResource("/nCards/card"+ cardNum +".png")).getImage();
            Image scaledCardImage = cardImage.getScaledInstance(cardWidth - 5,cardHeight - 5, Image.SCALE_SMOOTH); 

            tile = new JLabel(new ImageIcon(scaledCardImage));
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));

            Image cardBackImage = new ImageIcon(getClass().getResource("/nCards/back1.png")).getImage();
            Image scaledCardBackImage = cardBackImage.getScaledInstance(cardWidth - 5,cardHeight - 5, Image.SCALE_SMOOTH);

            backTile[t] = new JButton(new ImageIcon(scaledCardBackImage));
            backTile[t].setPreferredSize(new Dimension(cardWidth,cardHeight));
            backTile[t].addActionListener(this);

            cardLayer.add(tile);
            backLayer.add(backTile[t]);
            t++;
        } 
        //Right side GUI panel
        Image retryImage = new ImageIcon(getClass().getResource("/design/restart.png")).getImage();
        Image scaledRetryImage = retryImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        retryIcon = new JLabel(new ImageIcon(scaledRetryImage));
        retryIcon.setBounds(0, 0, 50, 50);
        retryButton.add(retryIcon);
        retryButton.setBackground(new Color(0x252121));
        retryButton.setBorder(border);
        retryButton.setBounds(20, 10, 50, 50);
        retryButton.setLayout(null);
        retryButton.addActionListener(this);
       
        for(int i = 0 ; i < heartCount ; i++){
            
            Image heartImage = new ImageIcon(getClass().getResource("/design/lives.png")).getImage();
            Image scaledHeartImage = heartImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);

            heart[i] = new JLabel(new ImageIcon(scaledHeartImage));
            heart[i].setPreferredSize(new Dimension(50,50));
            heart[i].setBorder(heartBorder);
            heart[i].setOpaque(true);
            heart[i].setBackground(new Color(0x252121));

            heartPanel.add(heart[i]);
        }

        heartPanel.setBounds(20,75,50,250);
        heartPanel.setBackground(new Color(0x64707C));
        heartPanel.setLayout(new  GridLayout(5,1 ,0,0));

        livesText.setBounds(15,325,80,30);
        livesText.setForeground(Color.BLACK);
        livesText.setText("Lives: " + heartCount);

        rightGui.setBounds(0, 0, 90, 375);
        rightGui.setBackground(new Color(0x64707C));
        rightGui.setBorder(border);
        rightGui.setLayout(null);
        rightGui.add(retryButton);
        rightGui.add(heartPanel);
        rightGui.add(livesText);

        //Left side GUI panel
        Image homeImage = new ImageIcon(getClass().getResource("/design/home.png")).getImage();
        Image scaledHomeImage = homeImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        homeIcon = new JLabel(new ImageIcon(scaledHomeImage));
        homeIcon.setBounds(0, 0, 50, 50);
        homeButton.add(homeIcon);
        homeButton.setBounds(20, 10, 50, 50);
        homeButton.setLayout(null);
        homeButton.setBackground(new Color(0x252121));
        homeButton.setBorder(border);
        homeButton.addActionListener(this);

        errorText.setBounds(15,325,80,30);
        errorText.setForeground(Color.BLACK);
        errorText.setText("Errors: " + errorCount);

        leftGui.setBounds(610, 0, 90, 375);
        leftGui.setLayout(null);
        leftGui.setBackground(new Color(0x64707C));
        leftGui.setBorder(border);
        leftGui.add(homeButton);
        leftGui.add(errorText);

        //Question
        questionLabel.setBounds(50, 40, 500, 150);
        questionLabel.setBackground(new Color(0x252121));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setOpaque(true);
        questionLabel.setBorder(whiteBorder);
        questionLabel.setVerticalAlignment(JLabel.CENTER);
        questionLabel.setHorizontalAlignment(JLabel.CENTER); 

        buttonA.setBounds(65, 200, 220, 50);
        buttonA.setOpaque(true);
        buttonA.setBorder(whiteBorder);
        buttonA.setBackground(new Color(0x252121));
        buttonA.setForeground(Color.WHITE);
        buttonA.addActionListener(this);

        buttonB.setBounds(65, 260, 220, 50);
        buttonB.setOpaque(true);
        buttonB.setBorder(whiteBorder);
        buttonB.setBackground(new Color(0x252121));
        buttonB.setForeground(Color.WHITE);
        buttonB.addActionListener(this);

        buttonC.setBounds(315, 200, 220, 50);
        buttonC.setOpaque(true);
        buttonC.setBorder(whiteBorder);
        buttonC.setBackground(new Color(0x252121));
        buttonC.setForeground(Color.WHITE);
        buttonC.addActionListener(this);

        buttonD.setBounds(315, 260, 220, 50);
        buttonD.setOpaque(true);
        buttonD.setBorder(whiteBorder);
        buttonD.setBackground(new Color(0x252121));
        buttonD.setForeground(Color.WHITE);
        buttonD.addActionListener(this);

        questionPanel.setBounds(50, 25, 600, 325);
        questionPanel.setBackground(new Color(0x64707C));
        questionPanel.setBorder(border);
        questionPanel.setLayout(null);
        questionPanel.add(questionLabel);
        questionPanel.add(buttonA);
        questionPanel.add(buttonB);
        questionPanel.add(buttonC);
        questionPanel.add(buttonD);
        questionPanel.setVisible(false);

        boardLayeredPane.add(questionPanel, JLayeredPane.MODAL_LAYER);
        boardLayeredPane.add(rightGui, JLayeredPane.DEFAULT_LAYER);
        boardLayeredPane.add(leftGui, JLayeredPane.DEFAULT_LAYER);

        this.add(boardLayeredPane);
        this.setLayout(null);
        this.setBounds(0,0,700, 375);
    }

    //Variable reset
    private void resetVars(boolean error){
        wait = false;
        click1 = -1;
        click2 = 0;
        question = 0;

        if(error){
            errorCount++;
            errorText.setText("Errors: " + errorCount);
        }
    }

    //Peeking
    private void peekCards(int click1){
        ArrayList<Integer> chanceRand = new ArrayList<>();
        ArrayList<Integer> available = new ArrayList<>();
        
        for (int i = 0; i < buttonNum; i++) {
            if (shufNum.get(i) != 0 && !pairedCards.contains(i)) {
                available.add(i);
            }
        }

        if (available.isEmpty()) return;

        int revealCount = Math.min(5, available.size());
        Collections.shuffle(available);

        for (JButton peekDisable : backTile) {
            peekDisable.setEnabled(false);
        }

        for (int i = 0; i < revealCount; i++) {
            int j = available.get(i);
            backTile[j].setVisible(false);
            backTile[j].setEnabled(false);
            chanceRand.add(j);
        }

        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for (int i : chanceRand) {
                    if (shufNum.get(i) != 0 && !pairedCards.contains(i)) {
                        backTile[i].setVisible(true);
                        backTile[i].setEnabled(true);
                    }
                }
                if(shufNum.get(click1) != 0){
                    backTile[click1].setVisible(true);
                    backTile[click1].setEnabled(true);
                }
                for (int i = 0; i < buttonNum; i++) {
                    if (!pairedCards.contains(i)) {
                        backTile[i].setEnabled(true);
                    }
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    //Question
    private void askQuestion(){
        do {
            questionNum = rand.nextInt(questionSet.length);
        } while (doneQuestion.contains(questionNum));

        doneQuestion.add(questionNum);

        questionLabel.setText(questionSet[questionNum].question);
        buttonA.setText(questionSet[questionNum].choiceA);
        buttonB.setText(questionSet[questionNum].choiceB);
        buttonC.setText(questionSet[questionNum].choiceC);
        buttonD.setText(questionSet[questionNum].choiceD);
        
        answering = true;

        questionPanel.setVisible(true);

        for (int i = 0; i < buttonNum; i++) {
            backTile[i].setEnabled(false);
        }
    }
    //ACTIONS
    public void actionPerformed(ActionEvent e) { 
        //----------------Home----------------\\
        if(e.getSource() == homeButton){ 
            mainMenu.resetBg();    
            mainMenu.callMainMenu();
            wait = false;
            return;
        }

        //----------------Retry----------------\\
        if(e.getSource() == retryButton){     
            levelSelect.playAgain(levelType);
            wait = false;
            return;
        }

        //----------------Question Answer----------------\\

        JButton click = (JButton) e.getSource();

        if (answering && (click != buttonA && click != buttonB && click != buttonC && click != buttonD)) {
            return;
        }

        if(click == buttonA || click == buttonB || click == buttonC || click == buttonD){
            String ansChosen = ((JButton)click).getText();
            String ansCorrect = questionSet[questionNum].ans;

            JButton ansChosenBut = click;
           
            if(ansChosen.equals(ansCorrect)){     
                questionLabel.setBackground(Color.GREEN);
                ansChosenBut.setBackground(Color.GREEN);
                questionLabel.setText("Correct");
            }else{
                questionLabel.setBackground(Color.RED);
                ansChosenBut.setBackground(Color.RED);
                questionLabel.setText("Wrong");
                if(buttonA.getText().equals(ansCorrect)){
                    buttonA.setBackground(Color.GREEN);
                }else if (buttonB.getText().equals(ansCorrect)){
                    buttonB.setBackground(Color.GREEN);
                }else if (buttonC.getText().equals(ansCorrect)){
                    buttonC.setBackground(Color.GREEN);
                }else if (buttonD.getText().equals(ansCorrect)){
                    buttonD.setBackground(Color.GREEN);
                }
                if (heartCount > 0) {
                    heartCount--;
                    if (heart[heartCount] != null) {
                        heart[heartCount].setIcon(null);
                        livesText.setText("Lives: " + heartCount);
                        errorCount++;
                        errorText.setText("Errors: " + errorCount);
                    }
                }
                if(heartCount == 0){
                    //you lost
                    for (JButton peekDisable : backTile) {
                        peekDisable.setEnabled(false);
                    }
                    Ending gameOver = new Ending(levelSelect, mainMenu, levelType, "You Lost");
                    boardLayeredPane.add(gameOver,JLayeredPane.MODAL_LAYER);
                    this.revalidate();
                    this.repaint();
                    System.out.println("Game Over");
                }
            }
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
            Timer timer = new Timer(1500, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        questionLabel.setBackground(Color.BLACK);
                        click.setBackground(Color.BLACK);
                        buttonA.setBackground(Color.BLACK);
                        buttonB.setBackground(Color.BLACK);
                        buttonC.setBackground(Color.BLACK);
                        buttonD.setBackground(Color.BLACK);
                        buttonA.setEnabled(true);
                        buttonB.setEnabled(true);
                        buttonC.setEnabled(true);
                        buttonD.setEnabled(true);
                        questionPanel.setVisible(false);
                        
                        answering = false;

                        for (int i = 0; i < buttonNum; i++) {
                            if (!pairedCards.contains(i)) {
                                backTile[i].setEnabled(true);
                            }
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
                resetVars(false);

            this.revalidate();
            this.repaint();
            return;
        }

        //----------------Card Pairing----------------\\
        if(wait){
            return;
        }

        for(int i = 0 ; i < buttonNum ; i++){
            if(click == backTile[i]){
                clickIndex = i;
                break;
            }
        }

        backTile[clickIndex].setVisible(false);
        backTile[clickIndex].setEnabled(false);

        if(click1 == -1){
            click1 = clickIndex;
            
             //click1 chancecard
            if(shufNum.get(click1) == 0){
                pairedCards.add(click1);
                peekCards(click1);
                resetVars(false);
            }

            //Ending
                if(pairedCards.size() == shufNum.size() && heartCount != 0){
                    Ending ending = new Ending(levelSelect, mainMenu, levelType, "You Won");
                    boardLayeredPane.add(ending,JLayeredPane.MODAL_LAYER);
                    this.revalidate();
                    this.repaint();
                    System.out.print("Game finish");
                }

        }else{
            click2 = clickIndex;

             //click2 chancecard
            if(shufNum.get(click2) == 0){
                pairedCards.add(click2);
                peekCards(click1);
                resetVars(false);
                return;
            }

            wait = true;

            if(shufNum.get(click1).equals(shufNum.get(click2))){
                pairedCards.add(click1);
                pairedCards.add(click2);
                resetVars(false);
                askQuestion();

                //Ending
                if(pairedCards.size() == shufNum.size() && heartCount != 0){
                    Ending ending = new Ending(levelSelect, mainMenu, levelType, "You Won!");
                    boardLayeredPane.add(ending,JLayeredPane.MODAL_LAYER);
                    this.revalidate();
                    this.repaint();
                    System.out.print("Game finish");
                }
            }else{
                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        backTile[click1].setVisible(true);
                        backTile[click1].setEnabled(true);
                        backTile[click2].setVisible(true);
                        backTile[click2].setEnabled(true);

                        resetVars(true);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

        this.revalidate();
        this.repaint();
    } 
}
//need to fix:
//Pause and menu 
//design
//
//pinagiisipan:  
//Timer mode
