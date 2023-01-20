package giss.mad.itinerario.service;


import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UmbralesTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(UmbralesTest.class);
    @Autowired
    private UmbralActividadRepository umbralActividadRepository;
    @Autowired
    private ActividadQARepository actividadQARepository;

    @BeforeAll
    public static void setContextVariables() {
    }

    @AfterAll
    public static void cleanContextVariables() {
    }

	@Test
	public void buscarUmbrales() {
        Optional<ActividadQA> actividadQA_1 = actividadQARepository.findById(1);
        Optional<ActividadQA> actividadQA_10 = actividadQARepository.findById(10);
        Optional<ActividadQA> actividadQA_15 = actividadQARepository.findById(15);

        Iterable<UmbralActividad> umbrales = umbralActividadRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_1.get().getId());
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_10.get().getId());
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_15.get().getId());

        if (umbralActividadRepository.findById(451).isPresent()) {
            UmbralActividad umbral = umbralActividadRepository.findById(451).get();
            Assertions.assertEquals(umbral.getId(), 451);
            Assertions.assertEquals(umbral.getThreshold(), "Cobertura del 75 % de funcionalidades críticas y " +
                    "del 50 % en el resto");
        }
	}
}