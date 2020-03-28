package tn.consomitounsi.www.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class UserProductViews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3090977258317652334L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Product product;
	private int views=0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getViews() {
		return views;
	}
	public void setViews() {
		this.views = this.views+1;
	}
	public UserProductViews() {}	
	public UserProductViews(Long id, User user, Product product, int views) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.views = views;
	}
	@Override
	public String toString() {
		return "UserProductViews [Id=" + id + ", user=" + user + ", product=" + product + ", views=" + views + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + views;
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
		UserProductViews other = (UserProductViews) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (views != other.views)
			return false;
		return true;
	}
	
}
