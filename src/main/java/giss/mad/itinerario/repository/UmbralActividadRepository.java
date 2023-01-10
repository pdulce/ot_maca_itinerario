package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.UmbralActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmbralActividadRepository extends JpaRepository<UmbralActividad, Integer> {

  List<UmbralActividad> findAllByDeletedIsNull();

  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
      Integer elemenTypeId, Integer forDelivery, Integer activityId);

  List<UmbralActividad> findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(
      Integer elemenTypeId, Integer forDelivery);

  UmbralActividad findByIdAndDeletedIsNull(Integer id);

}

