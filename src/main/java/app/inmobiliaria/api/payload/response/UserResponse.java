package app.inmobiliaria.api.payload.response;

import java.util.List;

import app.inmobiliaria.api.entity.Role;

public class UserResponse {
	private Long id;
	private String username;
	private String email;
	private List<Role> roles;
	private String token;

	

	public UserResponse(Long id, String username, String email, List<Role> roles, String token) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.token = token;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
