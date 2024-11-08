package administracion.tpo;

import administracion.tpo.dao.*;
import administracion.tpo.modelo.*;
import administracion.tpo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class TpoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TpoApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		}

}
