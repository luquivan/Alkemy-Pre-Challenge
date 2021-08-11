package alkemy.challenge.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import alkemy.challenge.model.PeliculaSerie;

public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {

	List<PeliculaSerie> findByTitulo(String nombre, Sort sort);
}
