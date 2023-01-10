package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.ActividadItinerario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadItinerarioRepository extends JpaRepository<ActividadItinerario, Integer> {


  List<ActividadItinerario>
  findAllByQualityItineraryIdAndDeletedIsNull(Integer qualityItineraryId);

  ActividadItinerario findByIdAndDeletedIsNull(Integer id);

}

