package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.EjeHeredable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjeHeredableRepository extends JpaRepository<EjeHeredable, Integer> {

  EjeHeredable findByIdAndDeletedIsNull(Integer id);

  EjeHeredable findByElementTypeIdAndAxisIdAndForDeliveryAndDeletedIsNull(
      Integer elementTypeId, Integer axisId, Integer forDelivery);

}

