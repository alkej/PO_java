package Alex.PO2.GUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KomentatorLogsList extends JFrame {
    
    private JFrame frame;

    public KomentatorLogsList(String path) {

        JTextArea text_area = new JTextArea(100, 100);

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            text_area.read(reader, "Reading file!");
        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + path);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Buffer error: " + path);
            ex.printStackTrace();
        }

        JScrollPane scroll = new JScrollPane(text_area);

        add(scroll);

        setTitle("Komentator - Bob");
        setSize(400,400);
        setVisible(true);
    }

}