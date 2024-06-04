package fr.sg.tondeuse;

import fr.sg.tondeuse.consts.ParamConst;
import fr.sg.tondeuse.services.PelouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootApplication
public class TondeuseApplication implements CommandLineRunner {

	@Value("${input.file}")
	private String inputFile;
	@Autowired
	private PelouseServiceImpl pelouseServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(TondeuseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Lire le fichier d'entrée depuis les ressources
		try {
			Path inputFilePath = new ClassPathResource(inputFile).getFile().toPath();
			pelouseServiceImpl.processFile(inputFilePath.toString());
		} catch (IOException e) {
			System.err.println(ParamConst.FILE_PATH_ERROR + e.getMessage());
		}
	}
}