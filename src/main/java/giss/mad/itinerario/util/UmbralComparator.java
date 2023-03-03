package giss.mad.itinerario.util;

import giss.mad.itinerario.model.UmbralActividad;

import java.util.Comparator;

public class UmbralComparator implements Comparator<UmbralActividad> {


    @Override
    public int compare(final UmbralActividad o1, final UmbralActividad o2) {
        if (o1.getElemenTypeId() < o2.getElemenTypeId()) {
            return 1;
        } else if (o1.getElemenTypeId() > o2.getElemenTypeId()) {
            return -1;
        } else {
            return 0;
        }
    }

}
