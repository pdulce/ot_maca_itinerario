package giss.mad.itinerario.service;

import giss.mad.itinerario.model.EjeHeredable;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.auxpesos.PesoEjeIniciador;
import giss.mad.itinerario.model.auxpesos.PesoGraph;
import giss.mad.itinerario.model.auxpesos.Weight;
import giss.mad.itinerario.model.repository.EjeHeredableRepository;
import giss.mad.itinerario.model.repository.PesoRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PesoService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private EjeHeredableRepository ejeHeredableRepository;

  private Map<Integer, List<Integer>> pesosHeredables = Map.of(1, List.of(5, 6, 7, 12, 15, 16, 20));
  private Map<Integer, List<Integer>> pesosHeredablesForDeliveries = Map.of(1,
      List.of(7, 15, 16, 20));//writables

  public Collection<Peso> getAll() {
    return this.pesoRepository.findAllByDeletedIsNull();
  }

  public Collection<PesoGraph> getAllByElement(Integer idElement, Boolean isDelivery) {
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

  public Peso get(Integer idPeso) {
    return this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
  }

  @Transactional
  public Peso remove(Integer idPeso) {
    Peso actividadQA = this.pesoRepository.findByIdAndDeletedIsNull(idPeso);
    if (this.pesoRepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      this.pesoRepository.delete(actividadQA);
    }
    return actividadQA;
  }

  @Transactional
  public Peso save(Peso peso) {
    return this.pesoRepository.save(peso);
  }

  @Transactional
  public Peso update(Peso peso) {
    if (this.pesoRepository.findById(peso.getId()).isPresent()) {
      return this.pesoRepository.save(peso);
    }
    return null;
  }


  public List<Integer> getAxisList() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<>(headers);

    String jsonString = restTemplate.exchange("/catalogo/ejes/getAll", HttpMethod.GET, entity,
        String.class).getBody();

    JSONArray jsonArr = new JSONArray(jsonString);
    List<Integer> axisIds = new ArrayList<>();
    for (int i = 0; i < jsonArr.length(); i++) {
      Integer id = (Integer) jsonArr.getJSONObject(i).get("id");
      axisIds.add(id);
    }
    return axisIds;
  }

  @Transactional
  public void initializeDB() {
    this.ejeHeredableRepository.deleteAll();
    this.pesoRepository.deleteAll();
    //anyadimos los ejes heredables, tanto en la jerarquÃ­a de elementos de catÃ¡logo, como entre entregas y sus elem. de catÃ¡logo asociados

    for (Integer idElementType : this.pesosHeredablesForDeliveries.keySet()) {
      List<Integer> ejesHeredablesForDeliveryList = this.pesosHeredablesForDeliveries.get(
          idElementType);
      for (Integer axisId : ejesHeredablesForDeliveryList) {
        EjeHeredable ejeHeredable = new EjeHeredable();
        ejeHeredable.setElementTypeId(idElementType);
        ejeHeredable.setAxisId(axisId);
        ejeHeredable.setForDelivery(true);
        ejeHeredable.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ejeHeredable.setWritable(true);
        this.ejeHeredableRepository.save(ejeHeredable);
      }

      List<Integer> atributos = getAxisList();
      for (Integer atrrId : atributos) {
        EjeHeredable ejeHeredable = new EjeHeredable();
        ejeHeredable.setElementTypeId(idElementType);
        ejeHeredable.setAxisId(atrrId);
        ejeHeredable.setForDelivery(true);
        ejeHeredable.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ejeHeredable.setWritable(false);
        this.ejeHeredableRepository.save(ejeHeredable);
      }
    }

    for (Integer idElementType : this.pesosHeredables.keySet()) {
      List<Integer> ejesHeredablesList = this.pesosHeredables.get(idElementType);
      for (Integer axisId : ejesHeredablesList) {
        EjeHeredable ejeHeredable = new EjeHeredable();
        ejeHeredable.setElementTypeId(idElementType);
        ejeHeredable.setAxisId(axisId);
        ejeHeredable.setForDelivery(false);
        ejeHeredable.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ejeHeredable.setWritable(true);
        this.ejeHeredableRepository.save(ejeHeredable);
      }
    }

    Map<Map<Integer, Boolean>, Map<Integer, List<Weight>>> elementsOfCatalogo = new PesoEjeIniciador(
        restTemplate).getElementsOfCatalogo();
    int id = 1;
    for (Map<Integer, Boolean> idElement_withIsDelivery : elementsOfCatalogo.keySet()) {
      Integer idOfElementType = idElement_withIsDelivery.keySet().iterator().next();
      Boolean isDelivery = idElement_withIsDelivery.get(idOfElementType);
      Map<Integer, List<Weight>> activitiesMap = elementsOfCatalogo.get(idElement_withIsDelivery);
      for (Integer idOfActivitiy : activitiesMap.keySet()) {
        List<Weight> listaEjesConPespsPorValorDominio = activitiesMap.get(idOfActivitiy);
        for (Weight weightObject : listaEjesConPespsPorValorDominio) {
          Integer idOAxis = weightObject.getIdOfAxis();
          Integer idOfValueDomain = weightObject.getIdOfvalueDomain();
          Integer weight = weightObject.getWeightValue();
          /*** creamos el peso ***/
          Peso peso = new Peso();
          peso.setId(id++);
          peso.setForDelivery(isDelivery);
          peso.setElementTypeId(idOfElementType);
          peso.setActivityId(idOfActivitiy);
          peso.setAxisAttributeId(idOAxis);
          peso.setDomainValueId(idOfValueDomain);
          peso.setWeightValue(weight);
          peso.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
          this.save(peso);
          /** fin grabacion peso ***/
        }//for de pesos-ejes-valordominio
      }//mapa actividades del elemento catalogo
    }//for elementos de catalogo
  }
}

