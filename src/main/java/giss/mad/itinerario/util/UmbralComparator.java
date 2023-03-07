package giss.mad.itinerario.util;

import giss.mad.itinerario.model.UmbralActividad;

import java.util.Comparator;

public class UmbralComparator implements Comparator<UmbralActividad> {


    @Override
    public int compare(final UmbralActividad o1, final UmbralActividad o2) {
        int result = o1.getElemenTypeId().compareTo(o2.getElemenTypeId());
        if (result == 0) {
            result = o1.getForDelivery().compareTo(o2.getForDelivery());
            if (result == 0) {
                result = o1.getLowerLimit().compareTo(o2.getLowerLimit());
            }
        }
        return result;
    }

}
