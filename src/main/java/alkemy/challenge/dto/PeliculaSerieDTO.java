package alkemy.challenge.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import alkemy.challenge.model.Genero;
import alkemy.challenge.model.PeliculaSerie;
import alkemy.challenge.model.Personaje;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class PeliculaSerieDTO {

	private String imagen;

	private String titulo;

	private Date fechaCreacion;

	private int calificacion;

	private List<Personaje> personajes;

	private List<Genero> generos;

	public PeliculaSerie buildEntity() {
		return PeliculaSerie.builder().imagen(this.imagen).titulo(this.titulo).fechaCreacion(this.fechaCreacion)
				.calificacion(this.calificacion).personajes(this.personajes).generos(this.generos).build();
	}

	public PeliculaSerie buildEntity(Long id) {
		return PeliculaSerie.builder().id(id).imagen(this.imagen).titulo(this.titulo).fechaCreacion(this.fechaCreacion)
				.calificacion(this.calificacion).personajes(this.personajes).generos(this.generos).build();
	}

	public static PeliculaSerieDTO from(PeliculaSerie peliculaSerieEntity) {
		return PeliculaSerieDTO.builder().imagen(peliculaSerieEntity.getImagen())
				.titulo(peliculaSerieEntity.getTitulo()).fechaCreacion(peliculaSerieEntity.getFechaCreacion())
				.calificacion(peliculaSerieEntity.getCalificacion()).personajes(peliculaSerieEntity.getPersonajes())
				.generos(peliculaSerieEntity.getGeneros()).build();
	}

	public static PeliculaSerieDTO searchFrom(PeliculaSerie peliculaSerieEntity) {
		return PeliculaSerieDTO.builder().imagen(peliculaSerieEntity.getImagen())
				.titulo(peliculaSerieEntity.getTitulo()).fechaCreacion(peliculaSerieEntity.getFechaCreacion()).build();
	}
}
