package Alex.PO2.Utils;

import Alex.PO2.Organisms.Animals.Antylopa;
import Alex.PO2.Organisms.Animals.Wilk;
import Alex.PO2.Organisms.Animals.Lis;
import Alex.PO2.Organisms.Animals.Zolw;
import Alex.PO2.Organisms.Animals.Owca;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Organisms.Plants.Mlecz;
import Alex.PO2.Organisms.Plants.Barszcz;
import Alex.PO2.Organisms.Plants.Guarana;
import Alex.PO2.Organisms.Plants.Trawa;
import Alex.PO2.Organisms.Plants.Wilcze_jagody;

import Alex.PO2.Swiat;
import org.w3c.dom.traversal.TreeWalker;

import java.awt.*;

public enum OrganizmyList {
    Zolw(1),
    Wilk(2),
    Wilcze_jagody(3),
    Trawa(4),
    Owca(5),
    Mlecz(6),
    Guarana(7),
    Barszcz(8),
    Antylopa(9),
    Lis(10),

    Z(1),
    W(2),
    J(3),
    T(4),
    O(5),
    M(6),
    G(7),
    B(8),
    A(9),
    L(10),

    MAX(10);


    private final int number;

    private OrganizmyList(int number) {
        this.number = number;
    }


    public int getNumber() {
        return number;
    }

    public static String getStringByValue(int num){
        for (OrganizmyList val: OrganizmyList.values()) {
            if (num == (val.getNumber())){
                return val.name();
            }
        }
        return null;
    }

    public static Color getColor(OrganizmyList val, Swiat _world){
        if (val == Zolw){
            return new Zolw(_world, 0, 0).getColor();
        }else if (val == Wilk){
            return new Wilk(_world, 0,0).getColor();
        }else if (val == Wilcze_jagody){
            return new Wilcze_jagody(_world, 0,0).getColor();
        }else if (val == Trawa){
            return new Trawa(_world, 0,0).getColor();
        }else if (val == Owca){
            return new Owca(_world, 0,0).getColor();
        }else if (val == Mlecz){
            return new Mlecz(_world, 0,0).getColor();
        }else if (val == Guarana){
            return new Guarana(_world, 0,0).getColor();
        }else if (val == Barszcz){
            return new Barszcz(_world, 0, 0).getColor();
        }else if (val == Antylopa){
            return new Antylopa(_world, 0, 0).getColor();
        }else if (val == Lis){
            return new Lis(_world, 0 ,0 ).getColor();
        }

        return Color.ORANGE;
    }

//    public static Organizm createOrganizm(OrganizmyList val, Swiat _world, int X, int Y){
//        if (val == Zolw){
//            return new Zolw(_world, X, Y);
//        }else if (val == Wilk){
//            return new Wilk(_world, X,Y);
//        }else if (val == Wilcze_jagody){
//            return new Wilcze_jagody(_world, X,Y);
//        }else if (val == Trawa){
//            return new Trawa(_world, X,Y);
//        }else if (val == Owca){
//            return new Owca(_world, X,Y);
//        }else if (val == Mlecz){
//            return new Mlecz(_world, X,Y);
//        }else if (val == Guarana){
//            return new Guarana(_world, X,Y);
//        }else if (val == Barszcz){
//            return new Barszcz(_world, X, Y);
//        }else if (val == Antylopa){
//            return new Antylopa(_world, X, Y);
//        }else if (val == Lis){
//            return new Lis(_world, X ,Y );
//        }
//        return null;
//    }

}
