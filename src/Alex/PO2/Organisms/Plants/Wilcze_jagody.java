package Alex.PO2.Organisms.Plants;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Wilcze_jagody extends Roslina{
    public Wilcze_jagody(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 99;

        this.symbol = 'J';
        this.title = "Jagody";

        this.COLOR = Color.BLUE;
    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return true;
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return new Wilcze_jagody(World, x, y);
    }
}
