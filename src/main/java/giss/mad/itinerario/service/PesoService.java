package giss.mad.itinerario.service;

import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.auxpesos.PesoGraph;
import giss.mad.itinerario.model.repository.EjeHeredableRepository;
import giss.mad.itinerario.model.repository.PesoRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PesoService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private EjeHeredableRepository ejeHeredableRepository;

  private final Map<Integer, List<Integer>> pesosHeredables = Map.of(Constantes.NUMBER_1,
      List.of(5, 6, 7, 12, 15, 16, 20));
  private final Map<Integer, List<Integer>> pesosHeredablesForDeliveries = Map.of(Constantes.NUMBER_1,
      List.of(7, 15, 16, 20));

  public Collection<Peso> getAll() {
    return this.pesoRepository.findAllByDeletedIsNull();
  }

  public Collection<PesoGraph> getAllByElement(final Integer idElement, Boolean isDelivery) {
    Collection<Peso> c = this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDelivery(
        idElement, isDelivery, Sort.by(Sort.Order.asc("id")));
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    Collection<PesoGraph> pesos = new ArrayList<>();
    for (Peso peso : c) {
      PesoGraph pesoGraph = new PesoGraph();
      pesoGraph.setActivity_id(peso.getActivityId());
      pesoGraph.setAxis_attribute_id(peso.getAxisAttributeId());
      pesoGraph.setWeight_value(peso.getWeightValue());
      pesos.add(pesoGraph);
    }
    return pesos;
  }

  public Peso get(final Integer idPeso) {
    return this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
  }

  @Transactional
  public Peso remove(final Integer idPeso) {
    Peso actividadQA = this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
    if (this.pesoRepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      this.pesoRepository.delete(actividadQA);
    }
    return actividadQA;
  }

  @Transactional
  public Peso save(final Peso peso) {
    return this.pesoRepository.save(peso);
  }

  @Transactional
  public Peso update(final Peso peso) {
    if (this.pesoRepository.findById(peso.getId()).isPresent()) {
      return this.pesoRepository.save(peso);
    }
    return null;
  }



}

