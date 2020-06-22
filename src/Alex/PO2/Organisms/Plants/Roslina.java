package Alex.PO2.Organisms.Plants;

import Alex.PO2.Komentator;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

public class Roslina extends Organizm {

    public Roslina(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 0;
        this.inicjatywa = 0;

    }

    @Override
    public void akcja() {
        int rn = (int) (Math.random() * 100);

        if (rn > 93){
            rozmnozSie();
        }
    }

    @Override
    public void akcja(int x, int y) {

    }

    @Override
    public void kolizja(int x, int y) {

        boolean AtDef = czyOdbilAtak(organizmy[y][x]);

        int tX = this.polozenie[0];
        int tY = this.polozenie[1];

        if (!AtDef){
            Komentator.newRecord(organizmy[y][x].getTitle()+ " zabił " + this.title);
            World.zabijOrganizm(tX, tY);
            organizmy[y][x].akcja(tX, tY);

        }else{
            Komentator.newRecord(this.title + " zabił " + organizmy[y][x].getTitle());
            World.zabijOrganizm(x, y);
        }



    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return null;
    }
}
