package ec.edu.uce.pokedex.repository;

import ec.edu.uce.pokedex.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
