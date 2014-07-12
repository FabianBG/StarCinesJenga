package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gen_id")
	private Integer genId;

	@Column(name="gen_tipo")
	private String genTipo;

	//bi-directional many-to-many association to Pelicula
	@ManyToMany(mappedBy="generos")
	private List<Pelicula> peliculas;

	public Genero() {
	}

	public Integer getGenId() {
		return this.genId;
	}

	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public String getGenTipo() {
		return this.genTipo;
	}

	public void setGenTipo(String genTipo) {
		this.genTipo = genTipo;
	}

	public List<Pelicula> getPeliculas() {
		return this.peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

}