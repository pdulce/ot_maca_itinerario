package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.UmbralActividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmbralActividadRepository extends JpaRepository<UmbralActividad, Integer> {

  List<UmbralActividad> findAllByDeletedIsNull();

  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
      Integer elemenTypeId, Integer forDelivery, Integer activityId);

  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(
      Integer elemenTypeId, Integer forDelivery);

  UmbralActividad findByIdAndDeletedIsNull(Integer id);

}

