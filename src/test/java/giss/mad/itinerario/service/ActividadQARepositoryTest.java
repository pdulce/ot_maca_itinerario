package giss.mad.itinerario.service;


import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.repository.ActividadQARepository;
import org.junit.jupiter.api.AfterAll;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ActividadQARepositoryTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ActividadQARepositoryTest.class);

    @Autowired
    private ActividadQARepository actividadQARepository;

    @BeforeAll
    public static void setContextVariables() {
    }

    @AfterAll
    public static void cleanContextVariables() {
    }

	@Test
	public void buscarActividades() {

        Iterable<ActividadQA> actividades = actividadQARepository.findAll();
        org.assertj.core.api.Assertions.assertThat(actividades).extracting(ActividadQA::getName).
                contains("Revisión Requisitos");
	}
}