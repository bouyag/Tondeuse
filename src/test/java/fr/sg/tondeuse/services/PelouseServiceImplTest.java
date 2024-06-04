package fr.sg.tondeuse.services;

import fr.sg.tondeuse.entities.DimensionsPelouse;
import fr.sg.tondeuse.entities.Tondeuse;
import fr.sg.tondeuse.repository.DimensionsPelouseRepository;
import fr.sg.tondeuse.repository.TondeuseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PelouseServiceImplTest {

    @Mock
    private TondeuseRepository tondeuseRepository;

    @Mock
    private DimensionsPelouseRepository dimensionsPelouseRepository;

    @InjectMocks
    private PelouseServiceImpl pelouseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessFile() {
        List<String> lines = Arrays.asList(
                "5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );
        PelouseService spyServicePelouse = spy(pelouseService);
        doReturn(lines).when(spyServicePelouse).readInputFile(anyString());

        spyServicePelouse.processFile("fake_path");

        verify(dimensionsPelouseRepository, times(1)).save(any(DimensionsPelouse.class));
        verify(tondeuseRepository, times(2)).save(any(Tondeuse.class));
    }

    @Test
    public void testReadInputFile() {
        String inputFilePath = "src/test/resources/input.txt";
        List<String> expectedLines = Arrays.asList(
                "5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );

        List<String> lines = pelouseService.readInputFile(inputFilePath);
        assertEquals(expectedLines, lines);
    }
}