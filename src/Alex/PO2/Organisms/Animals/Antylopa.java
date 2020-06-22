package Alex.PO2.Organisms.Animals;

import Alex.PO2.Komentator;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 4;
        this.inicjatywa = 4;

        this.step = 2;

        this.symbol = 'A';
        this.title = "Antylopa";

        this.COLOR = Color.YELLOW;

    }

    protected Organizm createNewOrganizm(int x, int y){
        return new Antylopa(World,x,y);
    }


    @Override
    public void kolizja(int x, int y) {

        int rn = (int) (Math.random() * 100);
        if (rn >= 50) {
            Komentator.newRecord(this.title + " uczekała!!!");
            int[] mozliwosci = findFreePlace();

            int direction = getRandomDir(mozliwosci);
            if (direction != -1) {
                int[] coordinates = getMoveCoord(direction);

                int X = coordinates[0];
                int Y = coordinates[1];

                super.akcja(X, Y);

            } else {
                super.kolizja(x, y);
            }

        } else {
            super.kolizja(x, y);
        }
    }

}

