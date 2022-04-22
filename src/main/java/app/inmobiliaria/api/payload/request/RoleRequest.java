package app.inmobiliaria.api.payload.request;

import java.util.Set;

import app.inmobiliaria.api.entity.Privilegios;



public class RoleRequest {
	
	  private Long id;

	  private String name;
	  
	  private Set<String> vigencia;
	  
	  private PrivilegiosRequest privilegios; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getVigencia() {
		return vigencia;
	}

	public void setVigencia(Set<String> vigencia) {
		this.vigencia = vigencia;
		
	}

	public PrivilegiosRequest getPrivilegio() {
		return privilegios;
	}

	public void setPrivilegio(PrivilegiosRequest privilegio) {
		this.privilegios = privilegio;
	}
	
	@Override
	public String toString() {
	  return "Usuario [id=" + id + ", name=" + name + ",vigencia="+ vigencia + ", privilegios=" + privilegios +"]";
	}

	  
}
