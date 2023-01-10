package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.ItinerarioCalidad;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItinerarioCalidadRepository extends JpaRepository<ItinerarioCalidad, Integer> {

  List<ItinerarioCalidad> findAllByCatalogueIdAndDelivery(Integer catalogueId,
      Integer delivery, Sort sort);
  ItinerarioCalidad findByIdAndDeletedIsNull(Integer id);

}

