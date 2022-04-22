package app.inmobiliaria.api.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String ap_materno;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String ap_paterno;
    

	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    private Set<String> vigencia;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @Size(max = 50)
    private String telefono;
    
    @Size(max = 120)
    private int rut;
    
    @Size(max = 10)
    private String dv;
    
    private String nombres;
 
    public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
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
 
    public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    
    public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Override
	public String toString() {
	  return "Usuario [username=" + username + ", email=" + email + ", role=" + role +"]";
	}
}
