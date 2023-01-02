package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.Peso;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesoRepository extends JpaRepository<Peso, Integer> {

  List<Peso> findAllByDeletedIsNull();

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
      final Integer elementTypeId, final Boolean forDelivery, final Integer activityId,
      final Integer axisAttributeId);

  Peso findByIdAndDeletedIsNull(Integer id);

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDelivery(final Integer elementTypeId,
      final Boolean forDelivery, final Sort sort);


}

