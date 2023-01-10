package giss.mad.itinerario.service;


import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port:8080",
                "jpa.database-platform:org.hibernate.dialect.Oracle12cDialect",
                "spring.datasource.driverClassName:oracle.jdbc.OracleDriver",
                "spring.datasource.url:jdbc:oracle:thin:@ldap://oraoid1.portal.ss:389/ds32,cn=OracleContext,dc=portal,dc=ss",
                "spring.datasource.username:MACA_ITINERARIO", "spring.datasource.password:maca_itinerario"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UmbralesServiceTest {

    private static Logger logger = (Logger) LoggerFactory.getLogger(UmbralesServiceTest.class);

    @InjectMocks
    private UmbralActividadService umbralActividadService;

    @Autowired
    private ActividadQARepository actividadQARepository;


    @BeforeAll
    public static void setContextVariables() {
    }

    @AfterAll
    public static void cleanContextVariables() {
    }

	@Test
	public void buscarPesoDeActividadesConcretas() {
        ActividadQA actividadQA_1 = actividadQARepository.findById(1).get();
        ActividadQA actividadQA_10 = actividadQARepository.findById(10).get();
        ActividadQA actividadQA_15 = actividadQARepository.findById(15).get();

        Iterable<UmbralActividad> umbrales = umbralActividadService.getUmbralesByIdActividad(1, 0,
                actividadQA_1.getId());
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_1.getId());
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_10.getId());
        org.assertj.core.api.Assertions.assertThat(umbrales).extracting(UmbralActividad::getActivityId).
                contains(actividadQA_15.getId());
	}
}