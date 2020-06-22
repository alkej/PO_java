package Alex.PO2.Organisms.Animals;

import Alex.PO2.Komentator;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.util.Arrays;

public class Zwierze extends Organizm {

    public Zwierze(Swiat my_world, int x, int y){
        super(my_world, x, y);
    }


    @Override
    public void akcja() {

        int[] coordinates = getMoveCoord();

        int X = coordinates[0];
        int Y = coordinates[1];

        akcja(X, Y);
    }

    public void akcja(int X, int Y){

        int tX = this.polozenie[0];
        int tY = this.polozenie[1];

        if (organizmy[Y][X] == null) {
            this.setNewPolozenie(X, Y);

            organizmy[Y][X] = organizmy[tY][tX];
            organizmy[tY][tX] = null;

        }
        else if (organizmy[tY][tX] != null) {
            organizmy[Y][X].kolizja(tX, tY);
        }

    }

    @Override
    public void kolizja(int x, int y) {

        boolean AtDef = czyOdbilAtak(organizmy[y][x]);

        int tX = this.polozenie[0]; //defence organism
        int tY = this.polozenie[1];

        boolean SameOrganism = checkOrganizm(organizmy[y][x], organizmy[tY][tX]);

        if (!SameOrganism) {
            if (!AtDef) {
                Komentator.newRecord(organizmy[y][x].getTitle() + " zabił " + this.title);
                World.zabijOrganizm(tX, tY);
                organizmy[y][x].akcja(tX, tY);


            }
            else{
                Komentator.newRecord(this.getTitle() + " obronił się od  " + organizmy[y][x].getTitle());
                World.zabijOrganizm(x, y);
            }
        }
        else {
            rozmnozSie();
        }
    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return this.sila > atakujacy.getSila();
    }


    @Override
    protected Organizm createNewOrganizm(int x, int y){
        return null;
    }

}



