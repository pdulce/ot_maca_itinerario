package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.ActividadItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadItinerarioRepository extends JpaRepository<ActividadItinerario, Integer> {

  ActividadItinerario findByIdAndDeletedIsNull(Integer id);

}

