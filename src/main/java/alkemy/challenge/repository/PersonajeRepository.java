package alkemy.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import alkemy.challenge.model.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
	
	List<Personaje> findByNombre(String nombre);

}
