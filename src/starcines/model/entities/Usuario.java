package starcines.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usu_nick")
	private String usuNick;

	@Column(name="usu_pass")
	private String usuPass;

	public Usuario() {
	}

	public String getUsuNick() {
		return this.usuNick;
	}

	public void setUsuNick(String usuNick) {
		this.usuNick = usuNick;
	}

	public String getUsuPass() {
		return this.usuPass;
	}

	public void setUsuPass(String usuPass) {
		this.usuPass = usuPass;
	}

}