package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pelicula database table.
 * 
 */
@Entity
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pel_id")
	private Integer pelId;

	@Column(name="pel_actores")
	private String pelActores;

	@Column(name="pel_clasificacion")
	private String pelClasificacion;

	@Column(name="pel_director")
	private String pelDirector;

	@Column(name="pel_duracion")
	private String pelDuracion;

	@Temporal(TemporalType.DATE)
	@Column(name="pel_fecha")
	private Date pelFecha;

	@Column(name="pel_idioma")
	private String pelIdioma;

	@Column(name="pel_imagen")
	private byte[] pelImagen;

	@Column(name="pel_nombre")
	private String pelNombre;

	@Column(name="pel_sinopsis")
	private String pelSinopsis;

	@Column(name="pel_sub_idioma")
	private String pelSubIdioma;

	@Column(name="pel_trailer")
	private String pelTrailer;

	//bi-directional many-to-one association to Cartelera
	@OneToMany(mappedBy="pelicula")
	private List<Cartelera> carteleras;

	//bi-directional many-to-many association to Genero
	@ManyToMany
	@JoinTable(
		name="genero_x_pelicula"
		, joinColumns={
			@JoinColumn(name="pel_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="gen_id")
			}
		)
	private List<Genero> generos;

	public Pelicula() {
	}

	public Integer getPelId() {
		return this.pelId;
	}

	public void setPelId(Integer pelId) {
		this.pelId = pelId;
	}

	public String getPelActores() {
		return this.pelActores;
	}

	public void setPelActores(String pelActores) {
		this.pelActores = pelActores;
	}

	public String getPelClasificacion() {
		return this.pelClasificacion;
	}

	public void setPelClasificacion(String pelClasificacion) {
		this.pelClasificacion = pelClasificacion;
	}

	public String getPelDirector() {
		return this.pelDirector;
	}

	public void setPelDirector(String pelDirector) {
		this.pelDirector = pelDirector;
	}

	public String getPelDuracion() {
		return this.pelDuracion;
	}

	public void setPelDuracion(String pelDuracion) {
		this.pelDuracion = pelDuracion;
	}

	public Date getPelFecha() {
		return this.pelFecha;
	}

	public void setPelFecha(Date pelFecha) {
		this.pelFecha = pelFecha;
	}

	public String getPelIdioma() {
		return this.pelIdioma;
	}

	public void setPelIdioma(String pelIdioma) {
		this.pelIdioma = pelIdioma;
	}

	public byte[] getPelImagen() {
		return this.pelImagen;
	}

	public void setPelImagen(byte[] pelImagen) {
		this.pelImagen = pelImagen;
	}

	public String getPelNombre() {
		return this.pelNombre;
	}

	public void setPelNombre(String pelNombre) {
		this.pelNombre = pelNombre;
	}

	public String getPelSinopsis() {
		return this.pelSinopsis;
	}

	public void setPelSinopsis(String pelSinopsis) {
		this.pelSinopsis = pelSinopsis;
	}

	public String getPelSubIdioma() {
		return this.pelSubIdioma;
	}

	public void setPelSubIdioma(String pelSubIdioma) {
		this.pelSubIdioma = pelSubIdioma;
	}

	public String getPelTrailer() {
		return this.pelTrailer;
	}

	public void setPelTrailer(String pelTrailer) {
		this.pelTrailer = pelTrailer;
	}

	public List<Cartelera> getCarteleras() {
		return this.carteleras;
	}

	public void setCarteleras(List<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}

	public Cartelera addCartelera(Cartelera cartelera) {
		getCarteleras().add(cartelera);
		cartelera.setPelicula(this);

		return cartelera;
	}

	public Cartelera removeCartelera(Cartelera cartelera) {
		getCarteleras().remove(cartelera);
		cartelera.setPelicula(null);

		return cartelera;
	}

	public List<Genero> getGeneros() {
		return this.generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

}