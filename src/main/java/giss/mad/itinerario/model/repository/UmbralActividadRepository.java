package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.UmbralActividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmbralActividadRepository extends JpaRepository<UmbralActividad, Integer> {

  List<UmbralActividad> findAllByDeletedIsNull();


  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
      final Integer elemenTypeId, final Boolean forDelivery, final Integer activityId);

  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(
      final Integer elemenTypeId, final Boolean forDelivery);

  UmbralActividad findByIdAndDeletedIsNull(final Integer id);

}

