package Alex.PO2.GUI;

import Alex.PO2.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener{

    private int width, height;

    private JTextField width_field, height_field;
    private JLabel width_label, height_label;
    private JButton start_game, load_game;
    private JFrame frame;
    private JPanel panel_menu, game_board, saved_games;

    private Container contentPane;

    public GUI(){
    }

    public static void main(String[] args){
        GUI start = new GUI();
        start.go();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == start_game){
            if (!width_field.getText().equals("") && !height_field.getText().equals("")){
                width = Integer.parseInt(width_field.getText());
                height = Integer.parseInt(height_field.getText());

                if (width != 0 && height != 0){
                    frame.remove(panel_menu);

                    int[] size = getScreenSize(width, height);

                    game_board = new Board(width, height, null, this, size[0]);
                    frame.setSize(size[0], size[1]);
                    frame.add(game_board);
                }

            }
        } else if (event.getSource() == load_game) {
            frame.remove(panel_menu);
            saved_games = new SavedGameList(this);
            frame.add(saved_games);
            frame.setSize(900, 350);
        }

        frame.validate();
        frame.repaint();
    }

    public void startSavedGame(String path){
        frame.remove(panel_menu);
        frame.remove(saved_games);

        Swiat savedSwiat = new Swiat(path);
        width = savedSwiat.getSize()[0];
        height = savedSwiat.getSize()[1];

        int[] size = getScreenSize(width, height);

        game_board = new Board(width, height, savedSwiat, this, size[0]);
        frame.setSize(size[0], size[1]);

        frame.add(game_board);


        frame.validate();
        frame.repaint();
    }

    public void startNewGame(){
        game_board.setVisible(false);
        frame.setSize(250, 110);
        frame.add(panel_menu);

        frame.validate();
        frame.repaint();
    }

    private void go(){

        frame = new JFrame("Keisel Aleksei 178947");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel_menu = new JPanel();

        SpringLayout layout = new SpringLayout();

        contentPane = frame.getContentPane();
        panel_menu.setLayout(layout);


        width_label = new JLabel("Width");
        width_field = new JTextField(10);

        height_label = new JLabel("Height");
        height_field = new JTextField(10);


        panel_menu.add(width_label);
        panel_menu.add(width_field);


        //Adjust constraints for the label so it's at (8,5).
        layout.putConstraint(SpringLayout.WEST, width_label,
                8,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, width_label,
                5,
                SpringLayout.NORTH, contentPane);

        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, width_field,
                5,
                SpringLayout.EAST, width_label);
        layout.putConstraint(SpringLayout.NORTH, width_field,
                5,
                SpringLayout.NORTH, contentPane);



        panel_menu.add(height_label);
        panel_menu.add(height_field);

        layout.putConstraint(SpringLayout.WEST, height_label,
                5,
                SpringLayout.WEST, contentPane);

        layout.putConstraint(SpringLayout.NORTH, height_label,
                25,
                SpringLayout.NORTH, contentPane);


        layout.putConstraint(SpringLayout.WEST, height_field,
                5,
                SpringLayout.EAST, height_label);
        layout.putConstraint(SpringLayout.NORTH, height_field,
                25,
                SpringLayout.NORTH, contentPane);


        start_game = new JButton("Start!");
        load_game = new JButton("Load");

        panel_menu.add(start_game);
        panel_menu.add(load_game);

        layout.putConstraint(SpringLayout.WEST, start_game,
                45,
                SpringLayout.WEST, contentPane);

        layout.putConstraint(SpringLayout.NORTH, start_game,
                50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, load_game,
                65,
                SpringLayout.WEST, start_game);

        layout.putConstraint(SpringLayout.NORTH, load_game,
                50,
                SpringLayout.NORTH, contentPane);

        contentPane.add(panel_menu);

        start_game.addActionListener(this);
        load_game.addActionListener(this);

        frame.setSize(250, 110);
        frame.setVisible(true);

        frame.validate();
        frame.repaint();

    }


    private int[] getScreenSize(int width, int height){
        int[] size = new int[2];

        size[0] = width*50 >= 500 ? width*50 : 500;
        size[1] = height*45 >= 450 ? height*45 : 450;

        return size;
    }



}
