package Alex.PO2.Organisms.Plants;

import Alex.PO2.Komentator;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Barszcz extends Roslina{
    public Barszcz(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 10;

        this.symbol = 'B';
        this.title = "Barszcz";

        this.COLOR = new Color(102, 0, 153);
    }

    @Override
    public void akcja() {
        int tX = this.polozenie[0];
        int tY = this.polozenie[1];

        int w_w = World.getSize()[0];
        int w_h = World.getSize()[1];

        int krok = 1;

        if (tX > 0) {
            if (organizmy[tY][tX - krok] != null) {
                Komentator.newRecord("Barszcz zabił " + organizmy[tY][tX - krok].getTitle());
                World.zabijOrganizm(tX - krok, tY);
            }

        }
        if (tX < w_w - 1) {
            if (organizmy[tY][tX + krok] != null) {
                Komentator.newRecord("Barszcz zabił " + organizmy[tY][tX + krok].getTitle());
                World.zabijOrganizm(tX + krok, tY);
            }

        }
        if (tY > 0) {
            if (organizmy[tY - krok][tX] != null) {
                Komentator.newRecord("Barszcz zabił " + organizmy[tY - krok][tX].getTitle());
                World.zabijOrganizm(tX, tY-krok);

            }
        }
        if (tY < w_h - 1) {
            if (organizmy[tY + krok][tX] != null) {
                Komentator.newRecord("Barszcz zabił " + organizmy[tY + krok][tX].getTitle());
                World.zabijOrganizm(tX, tY+krok);

            }
        }

    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return true;
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return new Barszcz(World, x, y);
    }
}
