package app.inmobiliaria.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;
  

  @NotBlank
  @Size(max = 20)
  private String nombres;
  
  @NotBlank
  @Size(max = 20)
  private String ap_paterno;
  
  @NotBlank
  @Size(max = 20)
  private String ap_materno;


  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  @NotBlank
  @Size(max = 120)
  private int rut;
  
  @NotBlank
  @Size(max = 10)
  private String dv;
  
  @Size(max = 50)
  private String telefono;
 
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vigencia_id")
  private Vigencia vigencia;
  
@ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();



public User() {
}


public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, @Size(max = 50) String telefono, int rut, String dv, String ap_paterno, String ap_materno, String nombres) {
	this.username = username;
	this.email = email;
	this.password = password;
	this.telefono = telefono;
	this.rut = rut;
	this.dv = dv;
	this.ap_materno = ap_materno;
	this.ap_paterno = ap_paterno;
	this.nombres = nombres;
}



  public String getAp_paterno() {
	return ap_paterno;
}


public void setAp_paterno(String ap_paterno) {
	this.ap_paterno = ap_paterno;
}


public String getAp_materno() {
	return ap_materno;
}


public void setAp_materno(String ap_materno) {
	this.ap_materno = ap_materno;
}




public String getDv() {
	return dv;
}


public void setDv(String dv) {
	this.dv = dv;
}


public int getRut() {
	return rut;
}


public void setRut(int rut) {
	this.rut = rut;
}


public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public Vigencia getVigencia() {
	return vigencia;
}


public void setVigencia(Vigencia vigencia) {
	this.vigencia = vigencia;
}


public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  public String getTelefono() {
	return telefono;
  }

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	@Override
	public String toString() {
	  return "Usuario [path=" + id + ", username=" + username + ",password="+ password + ", role=" + roles +"]";
	}
}
