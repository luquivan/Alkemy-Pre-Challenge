package alkemy.challenge.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeliculaSerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String imagen;

	@Column
	private String titulo;

	@Column
	private Date fechaCreacion;

	@Column
	private int calificacion;

	@ManyToMany(mappedBy = "peliculasYseries", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Personaje> personajes;

	@ManyToMany(mappedBy = "peliculasYseries", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Genero> generos;
}
