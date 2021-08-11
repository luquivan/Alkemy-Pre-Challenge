package alkemy.challenge.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class PersonajeDTO {

	private String imagen;

	private String nombre;

	private int edad;

	private double peso;

	private String historia;

	private List<PeliculaSerie> peliculasYseries;

	public Personaje buildEntity() {
		return Personaje.builder().imagen(this.imagen).nombre(this.nombre).edad(this.edad).peso(this.peso)
				.historia(this.historia).peliculasYseries(this.peliculasYseries).build();
	}

	public Personaje buildEntity(Long id) {
		return Personaje.builder().id(id).imagen(this.imagen).nombre(this.nombre).edad(this.edad).peso(this.peso)
				.historia(this.historia).peliculasYseries(this.peliculasYseries).build();
	}

	public static PersonajeDTO from(Personaje personajeEntity) {
		return PersonajeDTO.builder().imagen(personajeEntity.getImagen()).nombre(personajeEntity.getNombre())
				.edad(personajeEntity.getEdad()).peso(personajeEntity.getPeso()).historia(personajeEntity.getHistoria())
				.peliculasYseries(personajeEntity.getPeliculasYseries()).build();
	}
	
	public static PersonajeDTO searchFrom(Personaje personajeEntity) {
		return PersonajeDTO.builder().imagen(personajeEntity.getImagen()).nombre(personajeEntity.getNombre()).build();
	}
}
