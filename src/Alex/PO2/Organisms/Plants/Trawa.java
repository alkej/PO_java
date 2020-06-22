package Alex.PO2.Organisms.Plants;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Trawa extends Roslina{
    public Trawa(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.symbol = 'T';
        this.title = "Trawa";

        this.COLOR = Color.GREEN;
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return new Trawa(World, x, y);
    }
}
