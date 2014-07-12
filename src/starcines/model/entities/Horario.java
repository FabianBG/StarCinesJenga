package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hor_id")
	private Integer horId;

	@Column(name="hor_hora")
	private Time horHora;

	//bi-directional many-to-one association to Cartelera
	@OneToMany(mappedBy="horario")
	private List<Cartelera> carteleras;

	public Horario() {
	}

	public Integer getHorId() {
		return this.horId;
	}

	public void setHorId(Integer horId) {
		this.horId = horId;
	}

	public Time getHorHora() {
		return this.horHora;
	}

	public void setHorHora(Time horHora) {
		this.horHora = horHora;
	}

	public List<Cartelera> getCarteleras() {
		return this.carteleras;
	}

	public void setCarteleras(List<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}

	public Cartelera addCartelera(Cartelera cartelera) {
		getCarteleras().add(cartelera);
		cartelera.setHorario(this);

		return cartelera;
	}

	public Cartelera removeCartelera(Cartelera cartelera) {
		getCarteleras().remove(cartelera);
		cartelera.setHorario(null);

		return cartelera;
	}

}