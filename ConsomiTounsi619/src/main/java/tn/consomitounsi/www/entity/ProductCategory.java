package tn.consomitounsi.www.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id ;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6994794800536918692L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(unique=true)
	private String name;
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List <Product> products;
	@OneToMany(mappedBy="productCategory")
	@JsonIgnore
	private List <UserProductCategoryViews> productCategoryUsersViews;
	
	public Long getId () {
		return id ;
	}
	public void  setId (Long id ) {
		this.id  = id ;
	}
	public String getName() {
		return name;
	}
	public void  setName(String name) {
		this.name = name;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void  setProducts(List<Product> products) {
		this.products = products;
	}
	public ProductCategory(Long id , String name, List<Product> products) {
		super();
		this.id  =id ;
		this.name = name;
		this.products = products;
	}
	public List<UserProductCategoryViews> getProductCategoryUsersViews() {
		return productCategoryUsersViews;
	}
	public void  setProductCategoryUsersViews(List<UserProductCategoryViews> productCategoryUsersViews) {
		this.productCategoryUsersViews = productCategoryUsersViews;
	}
	public ProductCategory() {
		super();
	}
	public ProductCategory(Long id , String name, List<Product> products,
			List<UserProductCategoryViews> productCategoryUsersViews) {
		super();
		this.id  = id ;
		this.name = name;
		this.products = products;
		this.productCategoryUsersViews = productCategoryUsersViews;
	}
	@Override
	public String toString() {
		return "ProductCategory [id =" + id  + ", name=" + name + ", products=" + products
				+ ", productCategoryUsersViews=" + productCategoryUsersViews + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id  == null) ? 0 : id .hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productCategoryUsersViews == null) ? 0 : productCategoryUsersViews.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (id  == null) {
			if (other.id  != null)
				return false;
		} else if (!id .equals(other.id ))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productCategoryUsersViews == null) {
			if (other.productCategoryUsersViews != null)
				return false;
		} else if (!productCategoryUsersViews.equals(other.productCategoryUsersViews))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	
}
