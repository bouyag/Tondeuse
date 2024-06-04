package fr.sg.tondeuse.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public class MoteurTondeuse {
    private int x;
    private int y;
    private char orientation;
    private final int maxX;
    private final int maxY;

    public void executeInstruction(char instruction) {
        log.info("-------------- Debut : executeInstruction --------------");

        switch (instruction) {
            case 'G':
                tournerGauche();
                break;
            case 'D':
                tournerDroite();
                break;
            case 'A':
                avancer();
                break;
        }
        log.info("-------------- Fin : executeInstruction --------------");

    }

    private void tournerGauche() {
        log.info("-------------- Debut : tournerGauche --------------");

        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'N';
                break;
        }
        log.info("-------------- Fin : tournerGauche --------------");

    }

    private void tournerDroite() {
        log.info("-------------- Debut : tournerDroite --------------");

        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;
        }
        log.info("-------------- Fin : tournerDroite --------------");

    }

    private void avancer() {
        log.info("-------------- Debut : avancer --------------");

        switch (orientation) {
            case 'N':
                if (y < maxY) {
                    y++;
                }
                break;
            case 'E':
                if (x < maxX) {
                    x++;
                }
                break;
            case 'S':
                if (y > 0) {
                    y--;
                }
                break;
            case 'W':
                if (x > 0) {
                    x--;
                }
                break;
        }
        log.info("-------------- Fin : avancer --------------");

    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}