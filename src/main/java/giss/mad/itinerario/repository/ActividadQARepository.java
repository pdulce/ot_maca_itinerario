package giss.mad.marcocalidad.itinerariocalidad.repo;

import giss.mad.marcocalidad.itinerariocalidad.model.ActividadQA;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadQARepository extends JpaRepository<ActividadQA, Integer> {

    List<ActividadQA> findAllByDeletedIsNull(Sort sort);

    ActividadQA findByIdAndDeletedIsNull(Integer id);

}

