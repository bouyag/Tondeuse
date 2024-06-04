package fr.sg.tondeuse.repository;

import fr.sg.tondeuse.entities.DimensionsPelouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionsPelouseRepository extends JpaRepository<DimensionsPelouse, Long> {
}
