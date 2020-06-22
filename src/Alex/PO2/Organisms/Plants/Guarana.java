package Alex.PO2.Organisms.Plants;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Guarana extends Roslina{
    public Guarana(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.symbol = 'G';
        this.title = "Guarana";

        this.COLOR = Color.RED;
    }

    @Override
    public void kolizja(int x, int y) {
        int sila = organizmy[y][x].getSila();
        organizmy[y][x].setSila(sila*3);
        super.kolizja(x, y);
    }

    @Override
    protected Organizm createNewOrganizm(int x, int y) {
        return new Guarana(World, x, y);
    }
}
