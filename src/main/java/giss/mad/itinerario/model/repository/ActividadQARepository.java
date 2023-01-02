package giss.mad.itinerario.model.repository;

import giss.mad.itinerario.model.ActividadQA;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadQARepository extends JpaRepository<ActividadQA, Integer> {

  List<ActividadQA> findAllByDeletedIsNull(Sort sort);

  ActividadQA findByIdAndDeletedIsNull(Integer id);

}

