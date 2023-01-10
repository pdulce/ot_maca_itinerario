package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.EtapaPruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtapaPruebasRepository extends JpaRepository<EtapaPruebas, Integer> {

  List<EtapaPruebas> findAllByDeletedIsNull();

  EtapaPruebas findByNameAndDeletedIsNull(String name);

  EtapaPruebas findByIdAndDeletedIsNull(Integer id);

}

