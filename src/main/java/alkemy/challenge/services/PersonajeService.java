package alkemy.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.dto.PersonajeDTO;
import alkemy.challenge.model.Personaje;
import alkemy.challenge.repository.PersonajeRepository;

@Service
public class PersonajeService {

	@Autowired
	PersonajeRepository personajeRepository;

	public List<PersonajeDTO> buscarPersonaje(Optional<String> nombre, Optional<Integer> edad,
			Optional<Long> idPeliSerie) {
		List<Personaje> allPersonajes = personajesFilteredBy(nombre, edad, idPeliSerie);
		List<PersonajeDTO> result = new ArrayList<>();
		for (Personaje personaje : allPersonajes) {
			result.add(PersonajeDTO.searchFrom(personaje));
		}
		return result;
	}

	private List<Personaje> personajesFilteredBy(Optional<String> nombre, Optional<Integer> edad,
			Optional<Long> idPeliSerie) {
		List<Personaje> allPersonajes = new ArrayList<>();
		if (nombre.isPresent()) {
			String trueName = nombre.get();
			allPersonajes = personajeRepository.findByNombre(trueName);
		} else {
			allPersonajes = personajeRepository.findAll();
		}

		if (edad.isPresent()) {
			int trueAge = edad.orElseThrow();
			for (Personaje personaje : allPersonajes) {
				if (personaje.getEdad() != trueAge) {
					allPersonajes.remove(personaje);
				}
			}
		}

		if (idPeliSerie.isPresent()) {
			int i = 0;
			Boolean flag = false;
			Long trueMovie = idPeliSerie.orElseThrow();
			for (Personaje personaje : allPersonajes) {
				while (personaje.getPeliculasYseries().size() > i && !flag) {
					if (personaje.getPeliculasYseries().get(i).getId() == trueMovie) {
						flag = true;
					}
					i++;
				}
				if (!flag) {
					allPersonajes.remove(personaje);
				}
			}
		}
		return allPersonajes;
	}

	public Boolean crearPersonaje(PersonajeDTO personajeDTO) {
		try {
			Personaje personaje = personajeDTO.buildEntity();
			personajeRepository.saveAndFlush(personaje);
			return true;
		} catch (Exception e) {
			System.out.println("No se pudo crear el personaje");
			return false;
		}
	}

	public Boolean modificarPersonaje(Long id, PersonajeDTO personajeDTO) {
		try {
			Personaje personaje = personajeDTO.buildEntity(id);
			personajeRepository.saveAndFlush(personaje);
			return true;
		} catch (Exception e) {
			System.out.println("No se pudo modificar el personaje");
			return false;
		}

	}

	public Boolean eliminarPersonaje(Long id) {
		try {
			personajeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println("El personaje no existe en la base de datos");
		}
		return false;
	}

	public PersonajeDTO detallePersonaje(Long id) {
		Personaje personaje = personajeRepository.getById(id);
		return PersonajeDTO.from(personaje);
	}
}
