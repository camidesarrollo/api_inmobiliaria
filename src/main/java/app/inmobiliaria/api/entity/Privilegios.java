package app.inmobiliaria.api.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "privilegios")
public class Privilegios {
	
	  @Id
	  private Long privilegios_id;
	 
	  @NotBlank
	  @Size(max = 20)
	 private Boolean ver;

	  @NotBlank
	  @Size(max = 20)
	 private Boolean crear;
	  
	  @NotBlank
	  @Size(max = 20)
	 private Boolean actualizar;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "role_id")
	  private Role role;

	  
	public Privilegios() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Privilegios(Long privilegios_id, @NotBlank @Size(max = 20) Boolean ver, @NotBlank @Size(max = 20) Boolean crear,
			@NotBlank @Size(max = 20) Boolean actualizar, Role role) {
		this.privilegios_id = privilegios_id;
		this.ver = ver;
		this.crear = crear;
		this.actualizar = actualizar;
		this.role = role;
	}


	public Boolean getVer() {
		return ver;
	}


	public void setVer(Boolean ver) {
		this.ver = ver;
	}


	public Boolean getCrear() {
		return crear;
	}


	public void setCrear(Boolean crear) {
		this.crear = crear;
	}


	public Boolean getActualizar() {
		return actualizar;
	}


	public void setActualizar(Boolean actualizar) {
		this.actualizar = actualizar;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public Long getPrivilegios_id() {
		return privilegios_id;
	}


	public void setPrivilegios_id(Long privilegios_id) {
		this.privilegios_id = privilegios_id;
	}
	
	
	
	
	  

}
