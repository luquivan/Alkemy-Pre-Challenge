package alkemy.challenge.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.dto.PeliculaSerieDTO;
import alkemy.challenge.services.PeliculaSerieService;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieRest {
	@Autowired
	PeliculaSerieService peliculaSerieService;

	@GetMapping
	public ResponseEntity<List<PeliculaSerieDTO>> listarPeliculasYSeries(
			@RequestParam(value = "name", required = false) Optional<String> nombre,
			@RequestParam(value = "order", required = false) Optional<String> orden,
			@RequestParam(value = "genre", required = false) Optional<Long> idGenero) {
		List<PeliculaSerieDTO> result = peliculaSerieService.buscarPeliculaSerie(nombre, orden, idGenero);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	public ResponseEntity<PeliculaSerieDTO> crearPeliculaSerie(@RequestBody PeliculaSerieDTO peliculaSerie) {
		if (peliculaSerieService.crearPeliculaSerie(peliculaSerie)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerie);
		}
		return ResponseEntity.status(400).body(peliculaSerie);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PeliculaSerieDTO> modificarPeliculaSerie(@PathVariable("id") Long id,
			@RequestBody PeliculaSerieDTO peliculaSerie) {
		if (peliculaSerieService.modificarPeliculaSerie(id, peliculaSerie)) {
			return ResponseEntity.ok(peliculaSerie);
		}
		return ResponseEntity.status(400).body(peliculaSerie);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPelicula(@PathVariable("id") Long id) {
		if (peliculaSerieService.eliminarPeliculaSerie(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PeliculaSerieDTO> detallePeliculaSerie(@PathVariable("id") Long id) {
		PeliculaSerieDTO peliculaSerie = peliculaSerieService.detallePeliculaSerie(id);
		return ResponseEntity.ok(peliculaSerie);
	}
}
