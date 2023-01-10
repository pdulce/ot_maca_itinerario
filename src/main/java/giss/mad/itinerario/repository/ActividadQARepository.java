package giss.mad.itinerario.repository;

import giss.mad.itinerario.model.ActividadQA;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadQARepository extends JpaRepository<ActividadQA, Integer> {

  List<ActividadQA> findAllByDeletedIsNull(Sort sort);

  ActividadQA findByIdAndDeletedIsNull(Integer id);

}

