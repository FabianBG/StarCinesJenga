package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sala database table.
 * 
 */
@Entity
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sal_id")
	private Integer salId;

	@Column(name="sal_formato")
	private String salFormato;

	@Column(name="sal_nombre")
	private String salNombre;

	//bi-directional many-to-one association to Cartelera
	@OneToMany(mappedBy="sala")
	private List<Cartelera> carteleras;

	public Sala() {
	}

	public Integer getSalId() {
		return this.salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	public String getSalFormato() {
		return this.salFormato;
	}

	public void setSalFormato(String salFormato) {
		this.salFormato = salFormato;
	}

	public String getSalNombre() {
		return this.salNombre;
	}

	public void setSalNombre(String salNombre) {
		this.salNombre = salNombre;
	}

	public List<Cartelera> getCarteleras() {
		return this.carteleras;
	}

	public void setCarteleras(List<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}

	public Cartelera addCartelera(Cartelera cartelera) {
		getCarteleras().add(cartelera);
		cartelera.setSala(this);

		return cartelera;
	}

	public Cartelera removeCartelera(Cartelera cartelera) {
		getCarteleras().remove(cartelera);
		cartelera.setSala(null);

		return cartelera;
	}

}