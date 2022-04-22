package app.inmobiliaria.api.payload.response;

import java.util.List;

import javax.validation.constraints.Size;

import app.inmobiliaria.api.entity.Privilegios;
import app.inmobiliaria.api.entity.Vigencia;
import app.inmobiliaria.api.entity.User;

public class RoleResponse {
	  private Long id;
	  private String name;
	  private Vigencia vigencia;
	  private Privilegios privilegios; 
	  List <User> usuarios;
	  
	  
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
	public Vigencia getVigencia() {
		return vigencia;
	}
	public void setVigencia(Vigencia vigencia) {
		this.vigencia = vigencia;
	}
	public Privilegios getPrivilegios() {
		return privilegios;
	}
	public void setPrivilegios(Privilegios privilegios) {
		this.privilegios = privilegios;
	}
	public List<User> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}
	  
	  
}
