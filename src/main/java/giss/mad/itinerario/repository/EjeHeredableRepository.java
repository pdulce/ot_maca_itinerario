package giss.mad.marcocalidad.itinerariocalidad.repo;

import giss.mad.marcocalidad.itinerariocalidad.model.EjeHeredable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjeHeredableRepository extends JpaRepository<EjeHeredable, Integer> {

    EjeHeredable findByIdAndDeletedIsNull(Integer id);

    EjeHeredable findByElementTypeIdAndAxisIdAndForDeliveryAndDeletedIsNull(Integer elementTypeId, Integer axisId, Boolean forDelivery);

}

