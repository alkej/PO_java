package Alex.PO2.Utils;

import Alex.PO2.Organisms.Organizm;

import java.util.Comparator;

public class OrganizmComparator implements Comparator<Organizm> {
    @Override
    public int compare(Organizm o1, Organizm o2) {

        if (o1.getInicjatywa() == o2.getInicjatywa()) {
            return o2.getTourLife()-o1.getTourLife();
        }

        return o2.getInicjatywa() - o1.getInicjatywa();

    }
}
