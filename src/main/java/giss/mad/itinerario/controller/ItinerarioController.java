package giss.mad.itinerario.controller;

import giss.mad.itinerario.model.ActividadItinerario;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.volatilentities.PesoGraph;
import giss.mad.itinerario.model.volatilentities.ActividadReduced;
import giss.mad.itinerario.model.volatilentities.ReplicaElementOEntrega;
import giss.mad.itinerario.model.volatilentities.ActividadQAPantalla;
import giss.mad.itinerario.model.volatilentities.ItinerarioPantalla;
import giss.mad.itinerario.model.volatilentities.StageBuble;
import giss.mad.itinerario.model.volatilentities.UmbralGraph;
import giss.mad.itinerario.repository.ActividadItinerarioRepository;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EjeHeredableRepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.ItinerarioCalidadRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import giss.mad.itinerario.service.ActividadItinerarioService;
import giss.mad.itinerario.service.Constantes;
import giss.mad.itinerario.service.EtapaPruebasService;
import giss.mad.itinerario.service.ItinerarioCalidadService;
import giss.mad.itinerario.service.PesoService;
import giss.mad.itinerario.service.UmbralActividadService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import giss.mad.itinerario.service.ActividadQAService;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
@RequestMapping("/itinerario")
public final class ItinerarioController {


  @Autowired
  private ActividadItinerarioRepository actividadItinerarioRepository;

  @Autowired
  private ActividadQARepository actividadQARepository;

  @Autowired
  private EjeHeredableRepository ejeHeredableRepository;

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  @Autowired
  private ItinerarioCalidadRepository itinerarioCalidadRepository;

  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private UmbralActividadRepository umbralActividadRepository;

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

  @PostConstruct
  public void initRepositories() {
    this.actividadItinerarioService.setActividadItinerarioRepository(this.actividadItinerarioRepository);
    this.actividadItinerarioService.setEtapaPruebasRepository(this.etapaPruebasRepository);
    this.actividadItinerarioService.setItinerarioCalidadRepo(this.itinerarioCalidadRepository);
    this.actividadItinerarioService.setPesoRepository(this.pesoRepository);
    this.actividadItinerarioService.setUmbralActividadRepository(this.umbralActividadRepository);
    this.actividadItinerarioService.setEjeHeredableRepository(this.ejeHeredableRepository);
    this.actividadItinerarioService.setActividadQARepositoryRepository(this.actividadQARepository);

    this.actividadQAService.setUmbralActividadRepository(this.umbralActividadRepository);
    this.actividadQAService.setPesoRepository(this.pesoRepository);
    this.actividadQAService.setActividadQARepository(this.actividadQARepository);
    this.actividadQAService.setEtapaPruebasRepository(this.etapaPruebasRepository);

    this.etapaPruebasService.setEtapaPruebasRepository(this.etapaPruebasRepository);
    this.etapaPruebasService.setActividadQARepository(this.actividadQARepository);

    this.itinerarioCalidadService.setItinerarioCalidadRepository(this.itinerarioCalidadRepository);
    this.itinerarioCalidadService.setActividadQARepository(this.actividadQARepository);
    this.itinerarioCalidadService.setEtapaPruebasRepository(this.etapaPruebasRepository);

    this.pesoService.setPesoRepository(this.pesoRepository);
    this.pesoService.setEjeHeredableRepository(this.ejeHeredableRepository);

    this.umbralActividadService.setUmbralActividadRepository(this.umbralActividadRepository);
    this.umbralActividadService.setActividadRepository(this.actividadQARepository);
    this.umbralActividadService.setEtapaPruebasRepository(this.etapaPruebasRepository);
    this.umbralActividadService.setPesoRepository(this.pesoRepository);
  }

  @GetMapping("/QAStages/getAll")
  public Collection<EtapaPruebas> getEtapasPruebas() {
    return this.etapaPruebasService.getAll();
  }

  @GetMapping("/QAStages/getById/{id}")
  public EtapaPruebas getEtapaPruebas(final @PathVariable @NotNull @NotEmpty Integer id) {
    EtapaPruebas c = this.etapaPruebasService.get(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return c;
  }

  @PostMapping("/QAStages/create")
  public EtapaPruebas createStageQA(final @RequestBody @NotNull @NotEmpty EtapaPruebas etapaPruebas) {
    EtapaPruebas c = this.etapaPruebasService.insertar(etapaPruebas);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  @PutMapping("/QAStages/update")
  public EtapaPruebas updateStageQA(final @RequestBody @NotNull @NotEmpty EtapaPruebas etapaPruebas) {
    EtapaPruebas c = this.etapaPruebasService.actualizar(etapaPruebas);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  @DeleteMapping("/QAStages/delete/{idEtapa}")
  public EtapaPruebas deleteStageQA(final @PathVariable @NotNull @NotEmpty Integer idEtapa) {
    EtapaPruebas c = this.etapaPruebasService.borradoLogico(idEtapa);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }


  @GetMapping("/QAactivities/getAll")
  public Collection<ActividadQA> getActividadesQA() {
    return this.actividadQAService.getAll();
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

  @PostMapping("/QAactivities/create")
  public ActividadQA createActividadQA(final @RequestBody @NotNull @NotEmpty ActividadQA actividadQA) {
    ActividadQA c = this.actividadQAService.insertar(actividadQA);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  @PutMapping("/QAactivities/update")
  public ActividadQA updateActividadQA(final @RequestBody @NotNull @NotEmpty ActividadQA actividadQA) {
    ActividadQA c = this.actividadQAService.actualizar(actividadQA);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  @PutMapping("/QAactivities/updateWeights")
  public ActividadQA updatePesosActividadQA(final @RequestBody @NotNull @NotEmpty ActividadQA actividadQA) {
    ActividadQA c = this.actividadQAService.actualizarPesos(actividadQA);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  @PutMapping("/QAactivities/updateThresholds")
  public ActividadQA updateUmbralesActividadQA(final @RequestBody @NotNull @NotEmpty ActividadQA actividadQA) {
    ActividadQA c = this.actividadQAService.actualizarUmbrales(actividadQA);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }


  @DeleteMapping("/QAactivities/delete/{id}")
  public ActividadQA deleteActividadQA(final @PathVariable @NotNull @NotEmpty Integer id) {
    ActividadQA c = this.actividadQAService.borradoLogico(id);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return c;
  }

  /**** Mappings para operaciones de visualizacion grafica ***/

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
    return this.itinerarioCalidadService.getActivitiesByItineraryId(idItinerario, Constantes.NUMBER_1);
  }

  @GetMapping("/getActivitiesExcludedById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesExcludedById(
      final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getActivitiesByItineraryId(idItinerario, Constantes.NUMBER_0);
  }

  @GetMapping("/getActivitiesById/{idItinerario}")
  public Collection<ActividadQAPantalla> getActivitiesByItineraryId(
      final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getActivitiesByItineraryId(idItinerario);
  }

  @GetMapping("/getOnlyIncludedById/{idItinerario}")
  public Collection<StageBuble> getOnlyIncludedById(final @PathVariable @NotNull @NotEmpty Integer idItinerario) {
    return this.itinerarioCalidadService.getByIdItinerarioOnlyIncluded(idItinerario);
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
    return this.itinerarioCalidadService.getAllItinerariosByIdElementOrEntrega(idElementInstance, Constantes.NUMBER_0);
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
      throw new ResponseStatusException(HttpStatus.ACCEPTED);
    }
    return actividadItinerarioService.calculateItinerary(elementCatalogue);
  }

  @DeleteMapping("/borrarItinerarioById/{idOfItinerary}")
  public void deleteItinerary(final @PathVariable @NotEmpty @NotNull Integer idOfItinerary) {
    Collection<ActividadItinerario> actQAList = this.actividadItinerarioService.
            getItineraryActivitiesByParent(idOfItinerary);
    for (ActividadItinerario actItinerario: actQAList) {
      this.actividadItinerarioService.remove(actItinerario.getId());
    }
    itinerarioCalidadService.delete(idOfItinerary);
  }



}
