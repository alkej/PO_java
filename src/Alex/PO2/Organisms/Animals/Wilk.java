package Alex.PO2.Organisms.Animals;

import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Swiat;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 9;
        this.inicjatywa = 5;

        this.symbol = 'W';
        this.title = "Wilk";

        this.COLOR = Color.LIGHT_GRAY;


    }

    protected Organizm createNewOrganizm(int x, int y){
        return new Wilk(World, x, y);
    }
}
