package giss.mad.marcocalidad.itinerariocalidad.repo;

import giss.mad.marcocalidad.itinerariocalidad.model.ActividadItinerario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadItinerarioRepository extends JpaRepository<ActividadItinerario, Integer> {


    List<ActividadItinerario> findAllByQualityItineraryIdAndDeletedIsNull(Integer qualityItineraryId);

    ActividadItinerario findByIdAndDeletedIsNull(Integer id);

}

