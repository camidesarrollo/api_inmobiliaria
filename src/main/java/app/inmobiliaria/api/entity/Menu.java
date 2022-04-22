package app.inmobiliaria.api.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "menu")
public class Menu {
  @Id
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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "menu_roles", 
             joinColumns = @JoinColumn(name = "menu_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  
  /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
  private List<SubMenu> sub_menu;*/
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vigencia_id")
  private Vigencia vigencia;
  
  @NotBlank
  @Size(max = 50)
  private String descripcion;
  
  @NotBlank
  @Size(max = 50)
  private String argumentos;
  
  private Long tipo_menu;

/*public Menu(@NotBlank @Size(max = 20) String menu_title,
		@NotBlank @Size(max = 50) String menu_path, @NotBlank @Size(max = 120) String menu_icon, String descripcion, String argumentos) {
	this.menu_title = menu_title;
	this.menu_path = menu_path;
	this.menu_icon = menu_icon;
	this.descripcion = descripcion;
	this.argumentos = argumentos;
}*/

  


public Long getMenu_id() {
	return menu_id;
}


public Menu() {
	super();
	// TODO Auto-generated constructor stub
}

public Menu(@NotBlank Long menu_id, @NotBlank @Size(max = 20) String menu_title, @NotBlank @Size(max = 50) String menu_path,
		@NotBlank @Size(max = 120) String menu_icon,
		@NotBlank @Size(max = 50) String descripcion, @NotBlank @Size(max = 50) String argumentos, Long tipo_menu) {
	this.menu_id = menu_id; 
	this.menu_title = menu_title;
	this.menu_path = menu_path;
	this.menu_icon = menu_icon;
	this.descripcion = descripcion;
	this.argumentos = argumentos;
	this.tipo_menu = tipo_menu;
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



 public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  
	/*public List<SubMenu> getSub_menu() {
	return sub_menu;
}



public void setSub_menu(List<SubMenu> sub_menu) {
	this.sub_menu = sub_menu;
}*/



	public Vigencia getVigencia() {
	return vigencia;
	}



	public void setVigencia(Vigencia vigencia) {
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


	@Override
	public String toString() {
	  return "Usuario [path=" + menu_path + ", icon=" + menu_icon + ", role=" + roles +"]";
	}
}
