package fr.sg.tondeuse.services;

import fr.sg.tondeuse.entities.DimensionsPelouse;
import fr.sg.tondeuse.entities.Tondeuse;
import fr.sg.tondeuse.repository.DimensionsPelouseRepository;
import fr.sg.tondeuse.repository.TondeuseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static fr.sg.tondeuse.consts.ParamConst.FILE_READ_ERROR;

@Service
@Slf4j
public class PelouseServiceImpl implements PelouseService{
    @Autowired
    private TondeuseRepository tondeuseRepository;
    @Autowired
    private DimensionsPelouseRepository dimensionsPelouseRepository;
    @Transactional
    public void processFile(String inputFilePath) {
        log.info("-------------- Debut : processFile --------------");
        List<String> lines = readInputFile(inputFilePath);

        // Lire les dimensions de la pelouse
        String[] dimensions = lines.get(0).split(" ");
        int maxX = Integer.parseInt(dimensions[0]);
        int maxY = Integer.parseInt(dimensions[1]);

        DimensionsPelouse dimensionsPelouse = new DimensionsPelouse();
        dimensionsPelouse.setMaxX(maxX);
        dimensionsPelouse.setMaxY(maxY);
        dimensionsPelouseRepository.save(dimensionsPelouse);

        // Lire les positions initiales et instructions des tondeuses
        for (int i = 1; i < lines.size(); i += 2) {
            String[] positionInitiale = lines.get(i).split(" ");
            int x = Integer.parseInt(positionInitiale[0]);
            int y = Integer.parseInt(positionInitiale[1]);
            char orientation = positionInitiale[2].charAt(0);

            MoteurTondeuse moteurTondeuse = new MoteurTondeuse(x, y, orientation, maxX, maxY);
            String instructions = lines.get(i + 1);
            for (char instruction : instructions.toCharArray()) {
                moteurTondeuse.executeInstruction(instruction);
            }

            Tondeuse tondeuse = new Tondeuse();
            tondeuse.setX(moteurTondeuse.getX());
            tondeuse.setY(moteurTondeuse.getY());
            tondeuse.setOrientation(moteurTondeuse.getOrientation());
            tondeuseRepository.save(tondeuse);
        }
        log.info("-------------- Fin : processFile --------------");
    }

    public List<String> readInputFile(String inputFilePath) {
        log.info("-------------- readInputFile --------------");
        try {
            return Files.readAllLines(Paths.get(inputFilePath));
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }
}