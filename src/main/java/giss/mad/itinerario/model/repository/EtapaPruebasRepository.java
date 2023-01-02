package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.EtapaPruebas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapaPruebasRepository extends JpaRepository<EtapaPruebas, Integer> {

  List<EtapaPruebas> findAllByDeletedIsNull();

  EtapaPruebas findByNameAndDeletedIsNull(final String name);

  EtapaPruebas findByIdAndDeletedIsNull(final Integer id);

}

