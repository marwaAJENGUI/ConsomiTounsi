package tn.consomitounsi.www.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class UserProductCategoryViews implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6637050210318111190L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private ProductCategory productCategory;
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
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public int getViews() {
		return views;
	}
	public void setViews() {
		this.views = this.views+1;
	}
	public UserProductCategoryViews() {}
	public UserProductCategoryViews(Long id, User user, ProductCategory productCategory, int views) {
		super();
		this.id = id;
		this.user = user;
		this.productCategory = productCategory;
		this.views = views;
	}
	@Override
	public String toString() {
		return "UserProductCategoryViews [Id=" + id + ", user=" + user + ", productCategory=" + productCategory
				+ ", views=" + views + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
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
		UserProductCategoryViews other = (UserProductCategoryViews) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
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
