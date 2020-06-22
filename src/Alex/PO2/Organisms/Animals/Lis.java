package Alex.PO2.Organisms.Animals;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Lis extends Zwierze {

    public Lis(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 3;
        this.inicjatywa = 7;

        this.symbol = 'L';
        this.title = "Lis";

        this.COLOR = Color.ORANGE;

    }

    @Override
    public void akcja() {
        int[] coordinates = getMoveCoord();

        int X = coordinates[0];
        int Y = coordinates[1];

        int tX = this.polozenie[0];
        int tY = this.polozenie[1];


        if (organizmy[Y][X] == null) {
            super.akcja(X, Y);
        } else {
            if (this.getSila() > organizmy[Y][X].getSila()) {
                organizmy[Y][X].kolizja(tX, tY);
            }

        }
    }

    protected Organizm createNewOrganizm(int x, int y){
        return new Lis(World, x, y);
    }
}
