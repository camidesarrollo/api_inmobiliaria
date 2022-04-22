package app.inmobiliaria.api.payload.response;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.Vigencia;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UserInfoDataTableResponse {
	private Long id;
	private String username;
	private String ap_paterno;
	private String ap_materno;
	private String email;
	private String password;
	private int rut;
	private String dv;
	private String telefono;
	private Vigencia vigencia;
	private Set<Role> roles;
	private String rutFormato;
	private String nombres;
	
	public UserInfoDataTableResponse(Long id, String username, String ap_paterno, String ap_materno, String email,
			String password, int rut, String dv, String telefono, Vigencia vigencia, Set<Role> roles, String rutFormato, String nombres) {
		this.id = id;
		this.username = username;
		this.ap_paterno = ap_paterno;
		this.ap_materno = ap_materno;
		this.email = email;
		this.password = password;
		this.rut = rut;
		this.dv = dv;
		this.telefono = telefono;
		this.vigencia = vigencia;
		this.roles = roles;
		this.rutFormato = rutFormato;
		this.nombres = nombres;
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
	public int getRut() {
		return rut;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Vigencia getVigencia() {
		return vigencia;
	}
	public void setVigencia(Vigencia vigencia) {
		this.vigencia = vigencia;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getRutFormato() {
		return rutFormato;
	}
	public void setRutFormato(String rutFormato) {
		this.rutFormato = rutFormato;
	}
	
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	@Override
	public String toString() {
	  return "Usuario [path=" + id + ", username=" + username + ", role=" + roles + ", vigencia=" + vigencia +"]";
	}
}
