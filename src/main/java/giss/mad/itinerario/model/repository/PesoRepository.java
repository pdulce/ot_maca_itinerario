package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.Peso;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesoRepository extends JpaRepository<Peso, Integer> {

  List<Peso> findAllByDeletedIsNull();

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
      Integer elementTypeId, Boolean forDelivery, Integer activityId,
      Integer axisAttributeId);

  Peso findByIdAndDeletedIsNull(Integer id);

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDelivery(Integer elementTypeId,
      Boolean forDelivery, Sort sort);


}

