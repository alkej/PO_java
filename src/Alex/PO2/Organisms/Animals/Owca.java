package Alex.PO2.Organisms.Animals;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 4;
        this.inicjatywa = 4;

        this.symbol = 'O';
        this.title = "Owca";

        this.COLOR = new Color(153, 102, 0);

    }

    protected Organizm createNewOrganizm(int x, int y){
        return new Owca(World, x, y);
    }
}
