package tn.consomitounsi.www.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
public class AdCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6286868225796205171L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(unique=true)
	private String name;
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List<Ad>ads;
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
	public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}
	public AdCategory() {
		super();
	}
	public AdCategory(Long id, String name, List<Ad> ads) {
		super();
		this.id = id;
		this.name = name;
		this.ads = ads;
	}
	@Override
	public String toString() {
		return "AdCategory [Id=" + id + ", name=" + name + ", ads=" + ads + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ads == null) ? 0 : ads.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdCategory other = (AdCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ads == null) {
			if (other.ads != null)
				return false;
		} else if (!ads.equals(other.ads))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
