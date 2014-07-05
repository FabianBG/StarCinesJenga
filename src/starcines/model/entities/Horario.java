package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HORARIO_HORID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HORARIO_HORID_GENERATOR")
	@Column(name="hor_id")
	private Integer horId;

	@Column(name="hor_hora")
	private Time horHora;

	//bi-directional many-to-one association to Cartelera
	@ManyToOne
	@JoinColumn(name="hor_car_id")
	private Cartelera cartelera;

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

	public Cartelera getCartelera() {
		return this.cartelera;
	}

	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}

}