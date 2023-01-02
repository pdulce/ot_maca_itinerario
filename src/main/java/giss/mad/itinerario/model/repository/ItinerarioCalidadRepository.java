package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.ItinerarioCalidad;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItinerarioCalidadRepository extends JpaRepository<ItinerarioCalidad, Integer> {

  List<ItinerarioCalidad> findAllByCatalogueIdAndDelivery(final Integer catalogueId,
      final Boolean delivery, final Sort sort);

  List<ItinerarioCalidad> findAllByDeletedIsNull();

  ItinerarioCalidad findByIdAndDeletedIsNull(final Integer id);

}

