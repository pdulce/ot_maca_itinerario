package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.Peso;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesoRepository extends JpaRepository<Peso, Integer> {

  List<Peso> findAllByDeletedIsNull();

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
      Integer elementTypeId, Integer forDelivery, Integer activityId,
      Integer axisAttributeId);

  Peso findByIdAndDeletedIsNull(Integer id);

  List<Peso> findAllByDeletedIsNullAndElementTypeIdAndForDelivery(Integer elementTypeId,
      Integer forDelivery, Sort sort);


}

