package giss.mad.itinerario.repository;


import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
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
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port:8080",
                "jpa.database-platform:org.hibernate.dialect.Oracle12cDialect",
                "spring.datasource.driverClassName:oracle.jdbc.OracleDriver",
                "spring.datasource.url:jdbc:oracle:thin:@ldap://oraoid1.portal.ss:389/ds32,cn=OracleContext,dc=portal,dc=ss",
                "spring.datasource.username:MACA_ITINERARIO", "spring.datasource.password:maca_itinerario"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ActividadQARepositoryTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ActividadQARepositoryTest.class);
    private static final String SPRING_BANNER = "spring.main.banner_mode";
    private static final String SERVER_PORT = "server.port";

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