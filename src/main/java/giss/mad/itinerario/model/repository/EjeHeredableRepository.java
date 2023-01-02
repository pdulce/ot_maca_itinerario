package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.EjeHeredable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjeHeredableRepository extends JpaRepository<EjeHeredable, Integer> {

  EjeHeredable findByIdAndDeletedIsNull(final Integer id);

  EjeHeredable findByElementTypeIdAndAxisIdAndForDeliveryAndDeletedIsNull(
      final Integer elementTypeId, final Integer axisId, Boolean forDelivery);

}

