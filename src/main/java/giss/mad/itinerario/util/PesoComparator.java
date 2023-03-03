package giss.mad.itinerario.util;

import giss.mad.itinerario.model.Peso;

import java.util.Comparator;

public class PesoComparator implements Comparator<Peso> {


    @Override
    public int compare(final Peso o1, final Peso o2) {
        if (o1.getElementTypeId() < o2.getElementTypeId()) {
            return 1;
        } else if (o1.getElementTypeId() > o2.getElementTypeId()) {
            return -1;
        } else {
            return 0;
        }
    }

}
