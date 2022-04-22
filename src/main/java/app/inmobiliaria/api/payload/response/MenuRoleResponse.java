package app.inmobiliaria.api.payload.response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import app.inmobiliaria.api.entity.Menu;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.Vigencia;

public class MenuRoleResponse {

	  private Long menu_id;


	  private String menu_title;


	  private String menu_path;

	  private String menu_path_response;
	  

	  private String menu_icon;

	  private String descripcion;
	  
	  private String argumentos;
	  
	  private Long tipo_menu;
	  
	  private List<Role> roles;
	  
	  private List<MenuRoleResponse> submenu;
	  
	  private MenuRoleResponse padre;
	  
	  private String tipo;
	

	public Long getMenu_id() {
		return menu_id;
	}


	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}


	public String getMenu_title() {
		return menu_title;
	}


	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}


	public String getMenu_path() {
		return menu_path;
	}


	public void setMenu_path(String menu_path) {
		this.menu_path = menu_path;
	}


	public String getMenu_icon() {
		return menu_icon;
	}


	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getArgumentos() {
		return argumentos;
	}


	public void setArgumentos(String argumentos) {
		this.argumentos = argumentos;
	}


	public Long getTipo_menu() {
		return tipo_menu;
	}


	public void setTipo_menu(Long tipo_menu) {
		this.tipo_menu = tipo_menu;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public List<MenuRoleResponse> getSubmenu() {
		return submenu;
	}


	public void setSubmenu(List<MenuRoleResponse> submenu) {
		this.submenu = submenu;
	}
	
	
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	  private Vigencia vigencia;
	  

	public Vigencia getVigencia() {
		return vigencia;
	}


	public void setVigencia(Vigencia vigencia) {
		this.vigencia = vigencia;
	}

	

	public String getMenu_path_response() {
		return menu_path_response;
	}


	public void setMenu_path_response(String menu_path_response) {
		this.menu_path_response = menu_path_response;
	}

	

	public MenuRoleResponse getPadre() {
		return padre;
	}


	public void setPadre(MenuRoleResponse padre) {
		this.padre = padre;
	}


	@Override
	public String toString() {
	  return "MenuRole [menu_id=" + menu_id + "";
	}
}
