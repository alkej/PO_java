package Alex.PO2.GUI;

import Alex.PO2.Komentator;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;
import Alex.PO2.Utils.OrganizmyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Board extends JPanel implements ActionListener, KeyListener, MouseListener {

    private int width, height, width_x, height_y;
    private String message;
    private Swiat swiat;
    private Organizm[][] organisms;

    private JButton nextTour_button, saveGame_button, showLogs_button, newGame_button;
    private GUI menu;

    static private int CELL_SIZE = 30, BORDER_SIZE = 10;

    public Board(int width, int height, Swiat my_world, GUI menu, int screen_width){
        Komentator.clearKomentatorLogs();
        this.menu = menu;

        this.width = width;
        this.height = height;

        this.width_x = width * CELL_SIZE + 2*BORDER_SIZE;
        this.height_y = height * CELL_SIZE + 2*BORDER_SIZE;

        if (my_world == null){
            swiat = new Swiat(width, height);
        }else{
            swiat = my_world;
        }

        organisms = swiat.getOrganizmy();

        this.setLayout(null);


        nextTour_button = new JButton("Next Tour");
        nextTour_button.setBounds(screen_width-175, BORDER_SIZE, 150, 50);

        saveGame_button = new JButton("Save Game");
        saveGame_button.setBounds(screen_width-175, BORDER_SIZE + 50, 150, 50);

        showLogs_button = new JButton("Komentator");
        showLogs_button.setBounds(screen_width-175, BORDER_SIZE + 2*50, 150, 50);

        newGame_button = new JButton("Start new game");
        newGame_button.setBounds(screen_width-175, BORDER_SIZE + 3*50, 150, 50);


        this.add(saveGame_button);
        this.add(nextTour_button);
        this.add(showLogs_button);
        this.add(newGame_button);

        nextTour_button.addActionListener(this);
        saveGame_button.addActionListener(this);
        showLogs_button.addActionListener(this);
        newGame_button.addActionListener(this);


        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(true);
        this.addMouseListener(this);

        nextTour_button.setFocusable(false);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!swiat.getGameOver()){
            new AnimalsList(swiat, e.getX()/CELL_SIZE, e.getY()/CELL_SIZE);
            menu.getFrame().validate();
            menu.getFrame().repaint();
        }
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nextTour_button){
            boolean result = swiat.wykonajTure();
            if (!result){
                endTheGame();
            }
        }

        if (event.getSource() == saveGame_button){
            boolean result = swiat.SaveGame();
            if (result){
                String msg = "Game was saved";
                JOptionPane.showMessageDialog(null, msg,
                        "Saving", JOptionPane.INFORMATION_MESSAGE);
                Komentator.newRecord(msg);
            }else{
                String msg = "Error with game saving";
                JOptionPane.showMessageDialog(null, msg,
                        "Saving", JOptionPane.INFORMATION_MESSAGE);
                Komentator.newRecord(msg);
            }
        }

        if (event.getSource() == showLogs_button){
           Komentator.getKomentatorWindow();
        }

        if (event.getSource() == newGame_button){
            menu.startNewGame();
        }

        menu.getFrame().validate();
        menu.getFrame().repaint();

    }
    @Override
    public void keyPressed(KeyEvent e) {
        boolean result = true;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                swiat.setCzlowiekDirection_world(3);
                result = swiat.wykonajTure();
                break;
            case KeyEvent.VK_DOWN:
                swiat.setCzlowiekDirection_world(4);
                result = swiat.wykonajTure();
                break;
            case KeyEvent.VK_LEFT:
                swiat.setCzlowiekDirection_world(2);
                result = swiat.wykonajTure();
                break;
            case KeyEvent.VK_RIGHT:
                swiat.setCzlowiekDirection_world(1);
                result = swiat.wykonajTure();
                break;
            case KeyEvent.VK_ENTER:
                boolean special_result = swiat.tryToActivateSpecial();
                if (special_result){
                    String msg = "Specjalna umejętność Sashy została aktywowana!";
                    JOptionPane.showMessageDialog(null, msg,
                            "Umejetnosć", JOptionPane.INFORMATION_MESSAGE);
                    Komentator.newRecord(msg);
                }else{
                    String msg = "Nie udało się aktywować specjalną umejętność!";
                    JOptionPane.showMessageDialog(null, msg,
                            "Umejetnosć", JOptionPane.INFORMATION_MESSAGE);
                    Komentator.newRecord(msg);
                }
                break;
        }

        menu.getFrame().validate();
        menu.getFrame().repaint();

        if (!result){
            endTheGame();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);
        drawColors(g);
        drawTourLicznik(g);
        drawOrganizmy(g);

        //this.setFocusable(true);
        this.requestFocusInWindow();

        menu.getFrame().validate();
        menu.getFrame().repaint();

    }

    private void drawGrid(Graphics g){
        g.setColor(Color.RED);

        for (int i = BORDER_SIZE; i <= height_y-BORDER_SIZE; i+=CELL_SIZE) {
            g.drawLine(BORDER_SIZE, i,width_x - BORDER_SIZE, i);

        }

        for (int i = BORDER_SIZE; i <= width_x-BORDER_SIZE; i+=CELL_SIZE) {
            g.drawLine(i, BORDER_SIZE, i, height_y - BORDER_SIZE);

        }

    }
    private void drawOrganizmy(Graphics g){
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if (organisms[i][k] != null) {
                    g.setColor(organisms[i][k].getColor());
                    g.fillRect(BORDER_SIZE + k * CELL_SIZE,
                               BORDER_SIZE + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
    private void drawColors(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        for (int i = 1; i < OrganizmyList.MAX.getNumber() + 1; i++){
            g.setColor(OrganizmyList.getColor(OrganizmyList.valueOf(OrganizmyList.getStringByValue(i)), swiat));
            g.fillRect(BORDER_SIZE + (i-1)*(CELL_SIZE + 10), height_y,
                    CELL_SIZE, CELL_SIZE);

            String name = OrganizmyList.getStringByValue(i);

            if (name.equals("Wilcze_jagody")){
                name = "Jagody";
            }
            g.drawString(name, BORDER_SIZE + (i-1)*(CELL_SIZE + 10),
                    height_y + CELL_SIZE + 15);

            // for human
            if (i == OrganizmyList.MAX.getNumber()){
                g.setColor(Color.CYAN);
                g.fillRect(BORDER_SIZE + i*(CELL_SIZE + 10), height_y,
                        CELL_SIZE, CELL_SIZE);

                g.drawString("SASHA", BORDER_SIZE + i*(CELL_SIZE + 10),
                        height_y + CELL_SIZE + 15);
            }
        }

    }
    private void drawTourLicznik(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Zaczełą się tura Nr. " + swiat.getTourNumber(), BORDER_SIZE, height_y + CELL_SIZE*2 + 10);

    }

    private void endTheGame(){
        JOptionPane.showMessageDialog(null, "GAME OVER!",
                "Game over!", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
