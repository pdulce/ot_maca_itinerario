package giss.mad.itinerario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @Autowired
    private Application context;

    @Test
    public void contextLoads() {
        Assertions.assertThat(context).isNotNull();
        Application.main(new String[]{});
    }
}

