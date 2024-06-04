package fr.sg.tondeuse.repository;

import fr.sg.tondeuse.entities.Tondeuse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TondeuseRepository extends JpaRepository<Tondeuse, Long> {
}
