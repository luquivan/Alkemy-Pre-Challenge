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

import alkemy.challenge.dto.PersonajeDTO;
import alkemy.challenge.services.PersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeRest {

	@Autowired
	PersonajeService personajeService;

	@GetMapping
	public ResponseEntity<List<PersonajeDTO>> listarPersonajes(
			@RequestParam(value = "name", required = false) Optional<String> nombre,
			@RequestParam(value = "age", required = false) Optional<Integer> edad,
			@RequestParam(value = "movies", required = false) Optional<Long> idPeliSerie) {
		List<PersonajeDTO> result = personajeService.buscarPersonaje(nombre, edad, idPeliSerie);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	public ResponseEntity<PersonajeDTO> crearPersonaje(@RequestBody PersonajeDTO personaje) {
		if (personajeService.crearPersonaje(personaje)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
		}
		return ResponseEntity.status(400).body(personaje);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDTO> modificarPersonaje(@PathVariable("id") Long id,
			@RequestBody PersonajeDTO personaje) {
		if (personajeService.modificarPersonaje(id, personaje)) {
			return ResponseEntity.ok(personaje);
		}
		return ResponseEntity.status(400).body(personaje);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersonaje(@PathVariable("id") Long id) {
		if (personajeService.eliminarPersonaje(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonajeDTO> detallePersonajes(@PathVariable("id") Long id) {
		PersonajeDTO personaje = personajeService.detallePersonaje(id);
		return ResponseEntity.ok(personaje);
	}

}
