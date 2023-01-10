package giss.mad.itinerario.service;

import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.auxpesos.PesoGraph;
import giss.mad.itinerario.model.repository.EjeHeredableRepository;
import giss.mad.itinerario.model.repository.PesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

@Service
public class PesoService {

  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private EjeHeredableRepository ejeHeredableRepository;

  public final Collection<Peso> getAll() {
    return this.pesoRepository.findAllByDeletedIsNull();
  }

  public final Collection<PesoGraph> getAllByElement(final Integer idElement, final Integer isDelivery) {
    Collection<Peso> c = this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDelivery(
        idElement, isDelivery, Sort.by(Sort.Order.asc("id")));
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    Collection<PesoGraph> pesos = new ArrayList<>();
    for (Peso peso : c) {
      PesoGraph pesoGraph = new PesoGraph();
      pesoGraph.setActivityId(peso.getActivityId());
      pesoGraph.setAxisAttributeId(peso.getAxisAttributeId());
      pesoGraph.setWeightValue(peso.getWeightValue());
      pesos.add(pesoGraph);
    }
    return pesos;
  }

  public final Peso get(final Integer idPeso) {
    return this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
  }

  @Transactional
  public final Peso remove(final Integer idPeso) {
    Peso removedObject = null;
    Peso pesoBBDD = this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
    if (pesoBBDD != null) {
      Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
      pesoBBDD.setUpdateDate(timeStamp);
      pesoBBDD.setDeleted(1);
      removedObject = this.pesoRepository.saveAndFlush(pesoBBDD);
    }
    return removedObject;
  }

  @Transactional
  public final Peso save(final Peso peso) {
    return this.pesoRepository.save(peso);
  }

  @Transactional
  public final Peso update(final Peso peso) {
    Peso updatedObject = null;
    if (this.pesoRepository.findById(peso.getId()).isPresent()) {
      updatedObject = this.pesoRepository.save(peso);
    }
    return updatedObject;
  }



}

