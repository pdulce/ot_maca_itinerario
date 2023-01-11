package giss.mad.itinerario;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ApplicationTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ApplicationTest.class);
    private static final String SPRING_BANNER = "spring.main.banner_mode";
    private static final String SERVER_PORT = "server.port";

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setContextVariables() {
        System.setProperty(SPRING_BANNER, "off");
        System.setProperty(SERVER_PORT, "8080");
    }

    @AfterAll
    public static void cleanContextVariables() {
        System.setProperty(SPRING_BANNER, "");
        System.setProperty(SERVER_PORT, "");

        logger.info(">>>>>>>>>>>>>>>>>>>>>>> ApplicationTest COMPLETADO");
    }

	@Test
	void contextLoads() {
        logger.info("TESTING SI ESTA LEVANTADO EL SERVIDOR...");
        String msg = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/QAactivities/getAll", String.class);
        logger.info("RECIBIDO: " + msg);
        Assertions.assertEquals(msg,
                "{\"errors\":[{\"code\":\"GISS-500\",\"message\":\"Internal Server Error\"}]}");
	}
}