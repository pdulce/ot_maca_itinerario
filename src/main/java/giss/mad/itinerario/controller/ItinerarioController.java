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
  public EtapaPruebas getEtapaPruebas(final @PathVariable Integer id) {
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
    Collection<ActividadReduced> c = this.actividadQAService.getIdAndNameOfActivities();
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/QAactivities/getById/{id}")
  public ActividadQA getActividadQA(final @PathVariable Integer id) {
    ActividadQA c = this.actividadQAService.get(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @DeleteMapping("/QAactivities/delete/{id}")
  public void deleteActividadQA(final @PathVariable Integer id) {
    ActividadQA c = this.actividadQAService.remove(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/QAactivities/create")
  public ResponseEntity<Object> createActividadQA(
      final @RequestBody @NotEmpty ActividadQA atributoEje) {
    ActividadQA actividadQASaved = this.actividadQAService.save(atributoEje);
    if (this.actividadQAService.get(actividadQASaved.getId()) != null) {
      return ResponseEntity.ok().body("ActividadQA created sucesfully");
    } else {
      return ResponseEntity.unprocessableEntity().body("Failed to create ActividadQA specified");
    }
  }

  /**** Mappings para operaciones de visualizacion grafica ***/

  @GetMapping("/pesosByElementCat/{idTypeOfCatalogo}")
  public Collection<PesoGraph> getPesosByElementCatalogo(
      final @PathVariable Integer idTypeOfCatalogo) {
    return pesoService.getAllByElement(idTypeOfCatalogo, false);
  }

  @GetMapping("/pesosByDeliveryOfElement/{idTypeOfCatalogo}")
  public Collection<PesoGraph> getPesosByDeliveryOfElement(
      final @PathVariable Integer idTypeOfCatalogo) {
    return pesoService.getAllByElement(idTypeOfCatalogo, true);
  }


  @GetMapping("/threshold/getByElementCat/{idTypeOfCatalogo}")
  public Collection<UmbralGraph> getUmbralesByElementCatalogo(
      final @PathVariable Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByTypeOfElement(idTypeOfCatalogo, false);
  }

  @GetMapping("/threshold/getByDeliveryOfElement/{idTypeOfCatalogo}")
  public Collection<UmbralGraph> getUmbralesByDeliveryOfElement(
      final @PathVariable Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByTypeOfElement(idTypeOfCatalogo, true);
  }

  @GetMapping("/threshold/getByDeliveryOfElementBubles/{idTypeOfCatalogo}")
  public Collection<StageBuble> getUmbralesByStageDelivery(
      final @PathVariable Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByStage(idTypeOfCatalogo, true);
  }

  @GetMapping("/threshold/getByElementBubles/{idTypeOfCatalogo}")
  public Collection<StageBuble> getUmbralesByStageElement(
      final @PathVariable Integer idTypeOfCatalogo) {
    return umbralActividadService.getUmbralesByStage(idTypeOfCatalogo, false);
  }

  @GetMapping("/maxPesosOfActElemPromo/{idActivity}")
  public Integer getMaxSumOfPesosOfActElemPromo(
      final @PathVariable Integer idActivity) {
    int elementType = Constantes.NUMBER_1;
    Boolean isDelivery = false;
    return umbralActividadService.getMaximumOfWeigths(elementType, isDelivery, idActivity);
  }

  @GetMapping("/maxPesosOfActEntregaElemPromo/{idActivity}")
  public Integer getMaxSumOfPesosOfActEntregaElemPromo(final @PathVariable Integer idActivity) {
    int elementType = Constantes.NUMBER_1;
    Boolean isDelivery = true;
    return umbralActividadService.getMaximumOfWeigths(elementType, isDelivery, idActivity);
  }

  /*** Mapping operations for itinerary **/

  @GetMapping("/getAll")
  public Collection<ItinerarioCalidad> getAllItinerary() {
    Collection<ItinerarioCalidad> c = this.itinerarioCalidadService.getAll();
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getById/{idItinerario}")
  public ItinerarioCalidad getItinerarioById(final @PathVariable Integer idItinerario) {
    ItinerarioCalidad c = this.itinerarioCalidadService.getByIdItinerario(idItinerario);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getActivitiesIncludedById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesIncludedById(
      final @PathVariable Integer idItinerario) {
    Collection<ActividadQAPantalla> c = this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario, true);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getActivitiesExcludedById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesExcludedById(
      final @PathVariable Integer idItinerario) {
    Collection<ActividadQAPantalla> c = this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario, false);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getActivitiesById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesByItineraryId(
      final @PathVariable Integer idItinerario) {
    Collection<ActividadQAPantalla> c = this.itinerarioCalidadService.getActivitiesByItineraryId(
        idItinerario);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getOnlyIncludedById/{idItinerario}")
  public Collection<StageBuble> getOnlyIncludedById(final @PathVariable Integer idItinerario) {
    Collection<StageBuble> c = this.itinerarioCalidadService.getByIdItinerarioOnlyIncluded(
        idItinerario);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }


  @GetMapping("/getMoreRecentByIdElement/{idElementInstance}")
  public ItinerarioCalidad getItinerarioMasRecienteByIdElementOrEntrega(
      final @PathVariable Integer idElementInstance) {
    ItinerarioCalidad c = this.itinerarioCalidadService.getItinerarioMasRecienteByIdElementOrEntrega(
        idElementInstance, false);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @GetMapping("/getAllByIdElement/{idElementInstance}")
  public Collection<ItinerarioCalidad> getAllItinerariosByIdElement(
      final @PathVariable Integer idElementInstance) {
    Collection<ItinerarioCalidad> c = this.itinerarioCalidadService.getAllItinerariosByIdElementOrEntrega(
        idElementInstance, false);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @PostMapping("/calculate")
  public ItinerarioCalidad calculateItineraryWithDetailednfoElemen(
      final @RequestBody @NotEmpty ReplicaElementOEntrega elementCatalogue) {
    return actividadItinerarioService.calcularActividadItinerarioWithDetailedInfo(elementCatalogue);
  }

  @PostMapping("/calculateItinerary")
  public ItinerarioPantalla
  calculateItineraryElemen(final @RequestBody @NotEmpty ReplicaElementOEntrega elementCatalogue) {
    return actividadItinerarioService.calculateItinerary(elementCatalogue);
  }


}
