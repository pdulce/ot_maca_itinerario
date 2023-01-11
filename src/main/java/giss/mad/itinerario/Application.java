package giss.mad.itinerario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        //setProperty("SPRING_PROFILES_ACTIVE", "local");
        SpringApplication.run(Application.class, args);
    }

}
