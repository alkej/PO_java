package Alex.PO2.Organisms.Animals;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Zolw extends Zwierze {

    public Zolw(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 2;
        this.inicjatywa = 1;

        this.symbol = 'Z';
        this.title = "Zolw";

        this.COLOR = new Color(0,102,0);

    }

    @Override
    public void akcja() {
        int rn = (int) (Math.random() * 100);
        if (rn < 25){
            super.akcja();
        }

    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return atakujacy.getSila() < 5;
    }

    @Override
    public void kolizja(int x, int y) {
        boolean AtDef = czyOdbilAtak(organizmy[y][x]);

        if (!AtDef) {
            super.kolizja(x, y);
        } // jezeli nic nie robimy -> atakujacy organizm wraca na swoje mejsce
    }

    protected Organizm createNewOrganizm(int x, int y){
        return new Zolw(World, x, y);
    }
}
