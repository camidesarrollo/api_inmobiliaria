package app.inmobiliaria.api.payload.request;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.Vigencia;
 
public class MenuRequest {

	  private Long menu_id;

	  @NotBlank
	  @Size(max = 20)
	  private String menu_title;

	  @NotBlank
	  @Size(max = 50)
	  private String menu_path;

	  @NotBlank
	  @Size(max = 120)
	  private String menu_icon;

	  private Set<String> role;
	  
	  private Set<String> vigencia;
	  
	  @NotBlank
	  @Size(max = 50)
	  private String descripcion;
	  
	  @NotBlank
	  @Size(max = 50)
	  private String argumentos;
	  
	  private Long tipo_menu;

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

    public Set<String> getRole() {
	      return this.role;
  }
		    
  public void setRole(Set<String> role) {
    this.role = role;
  }

	public Set<String> getVigencia() {
		return vigencia;
	}

	public void setVigencia(Set<String> vigencia) {
		this.vigencia = vigencia;
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
	  
	  


}
