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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "subMenu")
public class SubMenu {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sub_menu_id;
   
	@NotBlank
    @Size(max = 20)
   private String sub_menu_title;
	
	@NotBlank
    @Size(max = 20)
   private String sub_menu_path;
	
	@NotBlank
    @Size(max = 20)
   private String sub_menu_icon;

	  /*@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "menu_id")
	  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    private Menu menu;*/
	  
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false, updatable = false)
    private Menu menu;
    
    @NotBlank
    @Size(max = 120)
    private String sub_menu_descripcion;
    
    @NotBlank
    @Size(max = 120)
    private String sub_menu_argumentos;
    
	public SubMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubMenu(String title, String path, String icon, String sub_menu_descripcion, String sub_menu_argumentos) {
		super();
		this.sub_menu_title = title;
		this.sub_menu_path = path;
		this.sub_menu_icon = icon;
		this.sub_menu_descripcion = sub_menu_descripcion;
		this.sub_menu_argumentos = sub_menu_argumentos;
		
	}

	public Long getId() {
		return sub_menu_id;
	}

	/*public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}*/

	public void setId(Long id) {
		this.sub_menu_id = id;
	}

	public String getTitle() {
		return sub_menu_title;
	}

	public void setTitle(String title) {
		this.sub_menu_title = title;
	}

	public String getPath() {
		return sub_menu_path;
	}

	public void setPath(String path) {
		this.sub_menu_path = path;
	}

	public String getIcon() {
		return sub_menu_icon;
	}

	public void setIcon(String icon) {
		this.sub_menu_icon = icon;
	}

	public String getSub_menu_descripcion() {
		return sub_menu_descripcion;
	}

	public void setSub_menu_descripcion(String sub_menu_descripcion) {
		this.sub_menu_descripcion = sub_menu_descripcion;
	}

	public String getSub_menu_argumentos() {
		return sub_menu_argumentos;
	}

	public void setSub_menu_argumentos(String sub_menu_argumentos) {
		this.sub_menu_argumentos = sub_menu_argumentos;
	}
	
	

}
