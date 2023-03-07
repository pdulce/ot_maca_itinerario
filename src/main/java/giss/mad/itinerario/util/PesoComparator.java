package giss.mad.itinerario.util;

import giss.mad.itinerario.model.Peso;

import java.util.Comparator;

public class PesoComparator implements Comparator<Peso> {


    @Override
    public int compare(final Peso o1, final Peso o2) {
        int result = o1.getElementTypeId().compareTo(o2.getElementTypeId());
        if (result == 0) {
            result = o1.getAxisAttributeId().compareTo(o2.getAxisAttributeId());
            if (result == 0) {
                result = o1.getDomainValueId().compareTo(o2.getDomainValueId());
            }
        }
        return result;
    }

}
