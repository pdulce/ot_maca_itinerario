package giss.mad.itinerario.service;

import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.auxpesos.PesoGraph;
import giss.mad.itinerario.model.repository.EjeHeredableRepository;
import giss.mad.itinerario.model.repository.PesoRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public final class PesoService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private EjeHeredableRepository ejeHeredableRepository;

  public Collection<Peso> getAll() {
    return this.pesoRepository.findAllByDeletedIsNull();
  }

  public Collection<PesoGraph> getAllByElement(final Integer idElement, final Integer isDelivery) {
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

  public Peso get(final Integer idPeso) {
    return this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
  }

  @Transactional
  public Peso remove(final Integer idPeso) {
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
  public Peso save(final Peso peso) {
    return this.pesoRepository.save(peso);
  }

  @Transactional
  public Peso update(final Peso peso) {
    Peso updatedObject = null;
    if (this.pesoRepository.findById(peso.getId()).isPresent()) {
      updatedObject = this.pesoRepository.save(peso);
    }
    return updatedObject;
  }



}

