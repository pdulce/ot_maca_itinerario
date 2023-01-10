package giss.mad.itinerario.controller;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.auxactiv.ActividadReduced;
import giss.mad.itinerario.model.auxactiv.ReplicaElementOEntrega;
import giss.mad.itinerario.model.auxitinerario.ActividadQAPantalla;
import giss.mad.itinerario.model.auxitinerario.ItinerarioPantalla;
import giss.mad.itinerario.model.auxpesos.PesoGraph;
import giss.mad.itinerario.model.auxumbrales.StageBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralGraph;
import giss.mad.itinerario.service.ActividadItinerarioService;
import giss.mad.itinerario.service.Constantes;
import giss.mad.itinerario.service.EtapaPruebasService;
import giss.mad.itinerario.service.ItinerarioCalidadService;
import giss.mad.itinerario.service.PesoService;
import giss.mad.itinerario.service.UmbralActividadService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import giss.mad.itinerario.service.ActividadQAService;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
@RequestMapping("/itinerario")
public final class ItinerarioController {

  @Autowired
  private ActividadQAService actividadQAService;
  @Autowired
  private EtapaPruebasService etapaPruebasService;

  @Autowired
  private PesoService pesoService;

  @Autowired
  private UmbralActividadService umbralActividadService;

  @Autowired
  private ActividadItinerarioService actividadItinerarioService;

  @Autowired
  private ItinerarioCalidadService itinerarioCalidadService;

  @GetMapping("/QAStages/getAll")
  public Collection<EtapaPruebas> getEtapasPruebas() {
    Collection<EtapaPruebas> c = this.etapaPruebasService.getAll();
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/QAStages/getById/{id}")
  public EtapaPruebas getEtapaPruebas(final @PathVariable @NotNull @NotEmpty Integer id) {
    EtapaPruebas c = this.etapaPruebasService.get(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/QAactivities/getAll")
  public Collection<ActividadQA> getActividadesQA() {
    Collection<ActividadQA> c = this.actividadQAService.getAll();
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/QAactivities/getreduced")
  public Collection<ActividadReduced> getIdAndNameOfActivities() {
    return this.actividadQAService.getIdAndNameOfActivities();
  }

  @GetMapping("/QAactivities/getById/{id}")
  public ActividadQA getActividadQA(final @PathVariable @NotNull @NotEmpty Integer id) {
    ActividadQA c = this.actividadQAService.get(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @DeleteMapping("/QAactivities/delete/{id}")
  public void deleteActividadQA(final @PathVariable @NotEmpty @NotNull Integer id) {
    ActividadQA c = this.actividadQAService.remove(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/QAactivities/create")
  public ResponseEntity<Object> createActividadQA(final @RequestBody @NotEmpty @NotNull ActividadQA atributoEje) {
    ResponseEntity<Object> retorno = ResponseEntity.ok().body("ActividadQA created sucesfully");
    if (atributoEje.getName() == null || "".contentEquals(atributoEje.getName())) {
      retorno = ResponseEntity.unprocessableEntity().body("Forbidden invocation params in received object");
    } else {
      ActividadQA actividadQASaved = this.actividadQAService.save(atributoEje);
      if (this.actividadQAService.get(actividadQASaved.getId()) == null) {
        retorno = ResponseEntity.unprocessableEntity().body("Failed to create ActividadQA specified");
      }
    }
    return retorno;
  }

  /**** Mappings para operaciones de visualizacion grafica ***/

  @GetMapping("/pesosByElementCat/{idTypeOfCatalogo}")
  public Collection<PesoGraph> getPesosByElementCatalogo(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return pesoService.getAllByElement(idTypeOfCatalogo, Constantes.NUMBER_0);
  }

  @GetMapping("/pesosByDeliveryOfElement/{idTypeOfCatalogo}")
  public Collection<PesoGraph> getPesosByDeliveryOfElement(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return pesoService.getAllByElement(idTypeOfCatalogo, Constantes.NUMBER_1);
  }


  @GetMapping("/threshold/getByElementCat/{idTypeOfCatalogo}")
  public Collection<UmbralGraph> getUmbralesByElementCatalogo(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByTypeOfElement(idTypeOfCatalogo, Constantes.NUMBER_0);
  }

  @GetMapping("/threshold/getByDeliveryOfElement/{idTypeOfCatalogo}")
  public Collection<UmbralGraph> getUmbralesByDeliveryOfElement(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByTypeOfElement(idTypeOfCatalogo, Constantes.NUMBER_1);
  }

  @GetMapping("/threshold/getByDeliveryOfElementBubles/{idTypeOfCatalogo}")
  public Collection<StageBuble> getUmbralesByStageDelivery(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByStage(idTypeOfCatalogo, Constantes.NUMBER_1);
  }

  @GetMapping("/threshold/getByElementBubles/{idTypeOfCatalogo}")
  public Collection<StageBuble> getUmbralesByStageElement(
      final @PathVariable @NotNull @NotEmpty Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByStage(idTypeOfCatalogo, Constantes.NUMBER_0);
  }

  @GetMapping("/maxPesosOfActElemPromo/{idActivity}")
  public Integer getMaxSumOfPesosOfActElemPromo(
      final @PathVariable @NotNull @NotEmpty Integer idActivity) {
    int elementType = Constantes.NUMBER_1;
    Integer isDelivery = Constantes.NUMBER_0;
    return umbralActividadService.getMaximumOfWeigths(elementType, isDelivery, idActivity);
  }

  @GetMapping("/maxPesosOfActEntregaElemPromo/{idActivity}")
  public Integer getMaxSumOfPesosOfActEntregaElemPromo(final @PathVariable @NotNull @NotEmpty Integer idActivity) {
    int elementType = Constantes.NUMBER_1;
    Integer isDelivery = Constantes.NUMBER_1;
    return umbralActividadService.getMaximumOfWeigths(elementType, isDelivery, idActivity);
  }

  /*** Mapping operations for itinerary **/

  @GetMapping("/getAll")
  public Collection<ItinerarioCalidad> getAllItinerary() {
    return this.itinerarioCalidadService.getAll();
  }

  @GetMapping("/getById/{idItinerario}")
  public ItinerarioCalidad getItinerarioById(final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    ItinerarioCalidad c = this.itinerarioCalidadService.getByIdItinerario(idItinerario);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getActivitiesIncludedById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesIncludedById(
      final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario, Constantes.NUMBER_1);
  }

  @GetMapping("/getActivitiesExcludedById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesExcludedById(
      final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario, Constantes.NUMBER_0);
  }

  @GetMapping("/getActivitiesById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesByItineraryId(
      final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario);
  }

  @GetMapping("/getOnlyIncludedById/{idItinerario}")
  public Collection<StageBuble> getOnlyIncludedById(final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getByIdItinerarioOnlyIncluded(
        idItinerario);
  }

  @GetMapping("/getMoreRecentByIdElement/{idElementInstance}")
  public ItinerarioCalidad getItinerarioMasRecienteByIdElementOrEntrega(
      final @PathVariable @NotNull @NotEmpty Integer idElementInstance) {
    ItinerarioCalidad c = this.itinerarioCalidadService.getItinerarioMasRecienteByIdElementOrEntrega(
        idElementInstance, Constantes.NUMBER_0);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getAllByIdElement/{idElementInstance}")
  public Collection<ItinerarioCalidad> getAllItinerariosByIdElement(
      final @PathVariable @NotNull @NotEmpty Integer idElementInstance) {
    Collection<ItinerarioCalidad> c = this.itinerarioCalidadService.getAllItinerariosByIdElementOrEntrega(
        idElementInstance, Constantes.NUMBER_0);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @PostMapping("/calculate")
  public ItinerarioCalidad calculateItineraryWithDetailednfoElemen(
      final @RequestBody @NotNull @NotEmpty ReplicaElementOEntrega elementCatalogue) {
    return actividadItinerarioService.calcularActividadItinerarioWithDetailedInfo(elementCatalogue);
  }

  @PostMapping("/calculateItinerary")
  public ItinerarioPantalla
  calculateItineraryElemen(final @RequestBody @NotNull @NotEmpty ReplicaElementOEntrega elementCatalogue) {
    if (elementCatalogue.getAttributeValuesCollection() == null
            || elementCatalogue.getAttributeValuesCollection().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return actividadItinerarioService.calculateItinerary(elementCatalogue);
  }


}
