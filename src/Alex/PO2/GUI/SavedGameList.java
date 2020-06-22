package Alex.PO2.GUI;

import Alex.PO2.Utils.DealsWithFiles;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;

public class SavedGameList extends JPanel {

    private JList<String> gamesList;

    
    public SavedGameList(GUI menu){

        String localDir = System.getProperty("user.dir");

        DefaultListModel<String> model = new DefaultListModel<>();

        ArrayList<String> games_list_data = new DealsWithFiles().getData(
                (localDir + "/saves/games.txt"));

        for (int i = 0; i < games_list_data.size(); i++) {
            model.addElement(games_list_data.get(i));
        }

        gamesList = new JList<>(model);
        gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        gamesList.addListSelectionListener((ListSelectionEvent event) -> {
            String path = localDir + "/saves/" + gamesList.getSelectedValue() + ".txt";
            menu.startSavedGame(path);

        });

        gamesList.setFixedCellHeight(25);
        gamesList.setFixedCellWidth(200);
        gamesList.setBackground(new Color(238, 238, 238));
        gamesList.setLayoutOrientation(JList.VERTICAL_WRAP);
        add(gamesList);

        setVisible(true);

    }


}

