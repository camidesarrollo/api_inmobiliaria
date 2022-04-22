package app.inmobiliaria.api.payload.request;

import app.inmobiliaria.api.entity.Role;

public class PrivilegiosRequest {

	  private Long privilegios_id;
	 

	 private Boolean ver;


	 private Boolean crear;
	  

	 private Boolean actualizar;
	  
	public Long getPrivilegios_id() {
		return privilegios_id;
	}

	public void setPrivilegios_id(Long privilegios_id) {
		this.privilegios_id = privilegios_id;
	}

	public Boolean getVer() {
		return ver;
	}

	public void setVer(Boolean leer) {
		this.ver = leer;
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


	  
	  
}
