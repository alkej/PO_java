package Alex.PO2.Organisms.Plants;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Mlecz extends Roslina{
    public Mlecz(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.symbol = 'M';
        this.title = "Mlecz";

        this.COLOR = new Color(10, 55,102);
    }

    @Override
    public void akcja() {
        int attempts = 3;

        for (int i = 0; i < attempts; i++) {
            super.akcja();
        }
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return new Mlecz(World, x, y);
    }
}
