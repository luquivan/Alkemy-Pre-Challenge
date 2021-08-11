package alkemy.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import alkemy.challenge.dto.PeliculaSerieDTO;
import alkemy.challenge.model.PeliculaSerie;
import alkemy.challenge.repository.PeliculaSerieRepository;

@Service
public class PeliculaSerieService {

	@Autowired
	PeliculaSerieRepository peliculaSerieRepository;

	public List<PeliculaSerieDTO> buscarPeliculaSerie(Optional<String> nombre, Optional<String> orden,
			Optional<Long> idGenero) {
		List<PeliculaSerie> allPeliculaSerie = peliculaSerieFilteredBy(nombre, idGenero, orden);
		List<PeliculaSerieDTO> result = new ArrayList<>();
		for (PeliculaSerie peliculaSerie : allPeliculaSerie) {
			result.add(PeliculaSerieDTO.searchFrom(peliculaSerie));
		}
		return null;
	}

	private List<PeliculaSerie> peliculaSerieFilteredBy(Optional<String> nombre, Optional<Long> idGenero,
			Optional<String> orden) {
		List<PeliculaSerie> allPeliculaSerie = new ArrayList<>();
		Sort sort = setSortType(orden);
		if (nombre.isPresent()) {
			String titulo = nombre.get();
			allPeliculaSerie = peliculaSerieRepository.findByTitulo(titulo, sort);
		} else {
			allPeliculaSerie = peliculaSerieRepository.findAll(sort);
		}

		if (idGenero.isPresent()) {
			int i = 0;
			Boolean flag = false;
			Long trueGenero = idGenero.orElseThrow();
			for (PeliculaSerie peliculaSerie : allPeliculaSerie) {
				while (peliculaSerie.getGeneros().size() > i && !flag) {
					if (peliculaSerie.getGeneros().get(i).getId() == trueGenero) {
						flag = true;
					}
					i++;
				}
				if (!flag) {
					allPeliculaSerie.remove(peliculaSerie);
				}
			}
		}
		return allPeliculaSerie;
	}

	private Sort setSortType(Optional<String> orden) {
		Sort sort;
		if (orden.isPresent()) {
			String ordenar = orden.get();
			if (ordenar.equals("ASC")) {
				sort = Sort.by(Sort.Direction.ASC, "fechaCreacion");
			} else {
				sort = Sort.by(Sort.Direction.DESC, "fechaCreacion");
			}
		} else {
			sort = Sort.by(Sort.DEFAULT_DIRECTION);
		}
		return sort;
	}

	public Boolean crearPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO) {
		try {
			PeliculaSerie peliculaSerie = peliculaSerieDTO.buildEntity();
			peliculaSerieRepository.saveAndFlush(peliculaSerie);
			return true;
		} catch (Exception e) {
			System.out.println("No se pudo crear la pelicula");
			return false;
		}
	}

	public Boolean modificarPeliculaSerie(Long id, PeliculaSerieDTO peliculaSerieDTO) {
		try {
			PeliculaSerie peliculaSerie = peliculaSerieDTO.buildEntity(id);
			peliculaSerieRepository.saveAndFlush(peliculaSerie);
			return true;
		} catch (Exception e) {
			System.out.println("No se pudo modificar la pelicula");
			return false;
		}
	}

	public Boolean eliminarPeliculaSerie(Long id) {
		try {
			peliculaSerieRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println("La pelicula no existe en la base de datos");
		}
		return false;
	}

	public PeliculaSerieDTO detallePeliculaSerie(Long id) {
		PeliculaSerie peliculaSerie = peliculaSerieRepository.getById(id);
		return PeliculaSerieDTO.from(peliculaSerie);
	}

}
