package fr.sg.tondeuse.services;

import java.util.List;

public interface PelouseService {
    public void processFile(String inputFilePath);
    public List<String> readInputFile(String inputFilePath);
}
