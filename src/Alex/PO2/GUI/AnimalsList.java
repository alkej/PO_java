package Alex.PO2.GUI;

import Alex.PO2.Komentator;
import Alex.PO2.Swiat;
import Alex.PO2.Utils.OrganizmyList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class AnimalsList extends JFrame {

    private JList<String> orgList;
    private Swiat world;
    private JFrame frame;

    private int x, y;

    public AnimalsList(Swiat world, int x, int y){

        this.world = world;
        this.x = x;
        this.y = y;

        DefaultListModel<String> model = new DefaultListModel<>();
        int licznik = 0;
        for (OrganizmyList val: OrganizmyList.values()) {
            licznik++;
            int max = OrganizmyList.MAX.getNumber();
            model.addElement(val.name());
            if (licznik >= max)
                break;
        }
        orgList = new JList<>(model);

        orgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        orgList.addListSelectionListener((ListSelectionEvent event) -> {

            int orgNum = OrganizmyList.valueOf(orgList.getSelectedValue()).getNumber();

            int world_x = world.getSize()[0];
            int world_y = world.getSize()[1];

            if (this.x < world_x && this.y < world_y){
                this.world.initOrganizmy(this.x, this.y, orgNum);
                Komentator.newRecord(orgList.getSelectedValue() + " był/a dodany/a! Witajcie!");
            }else{
                JOptionPane.showMessageDialog(null, "Outside the game border!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }


            SwingUtilities.getWindowAncestor(orgList).setVisible(false);
        });

        add(orgList);
        setTitle("List of organisms");
        orgList.setSize(400,400);
        setSize(400,400);
        setVisible(true);

    }
}

