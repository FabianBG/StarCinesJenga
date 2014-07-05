package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cartelera database table.
 * 
 */
@Entity
@NamedQuery(name="Cartelera.findAll", query="SELECT c FROM Cartelera c")
public class Cartelera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARTELERA_CARID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARTELERA_CARID_GENERATOR")
	@Column(name="car_id")
	private Integer carId;

	@Temporal(TemporalType.DATE)
	@Column(name="car_desde")
	private Date carDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="car_hasta")
	private Date carHasta;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="car_pel_id")
	private Pelicula pelicula;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="car_sal_id")
	private Sala sala;

	//bi-directional many-to-one association to Horario
	@OneToMany(mappedBy="cartelera")
	private List<Horario> horarios;

	public Cartelera() {
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Date getCarDesde() {
		return this.carDesde;
	}

	public void setCarDesde(Date carDesde) {
		this.carDesde = carDesde;
	}

	public Date getCarHasta() {
		return this.carHasta;
	}

	public void setCarHasta(Date carHasta) {
		this.carHasta = carHasta;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Horario addHorario(Horario horario) {
		getHorarios().add(horario);
		horario.setCartelera(this);

		return horario;
	}

	public Horario removeHorario(Horario horario) {
		getHorarios().remove(horario);
		horario.setCartelera(null);

		return horario;
	}

}