package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the snacks database table.
 * 
 */
@Entity
@Table(name="snacks")
@NamedQuery(name="Snack.findAll", query="SELECT s FROM Snack s")
public class Snack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SNACKS_SNAID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SNACKS_SNAID_GENERATOR")
	@Column(name="sna_id")
	private Integer snaId;

	@Column(name="sna_descripcion")
	private String snaDescripcion;

	@Column(name="sna_nombre")
	private String snaNombre;

	@Column(name="sna_precio")
	private BigDecimal snaPrecio;

	public Snack() {
	}

	public Integer getSnaId() {
		return this.snaId;
	}

	public void setSnaId(Integer snaId) {
		this.snaId = snaId;
	}

	public String getSnaDescripcion() {
		return this.snaDescripcion;
	}

	public void setSnaDescripcion(String snaDescripcion) {
		this.snaDescripcion = snaDescripcion;
	}

	public String getSnaNombre() {
		return this.snaNombre;
	}

	public void setSnaNombre(String snaNombre) {
		this.snaNombre = snaNombre;
	}

	public BigDecimal getSnaPrecio() {
		return this.snaPrecio;
	}

	public void setSnaPrecio(BigDecimal snaPrecio) {
		this.snaPrecio = snaPrecio;
	}

}