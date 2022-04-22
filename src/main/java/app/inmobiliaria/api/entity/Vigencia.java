package app.inmobiliaria.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "vigencia")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Vigencia {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;

	  @Column(length = 20)
	  private String name;
	public Vigencia() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
	  return "Vigencia [id=" + id + ", name=" + name +"]";
	}


	  
	  
}
