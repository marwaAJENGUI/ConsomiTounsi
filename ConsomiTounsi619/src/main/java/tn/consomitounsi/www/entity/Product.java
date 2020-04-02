package tn.consomitounsi.www.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950217207447904668L;
	@Id
	private Long barCode;
	@NotNull (message="product name is null")
	@Column(unique=true)
	private String name;
	@NotNull
	@Positive(message="The price should be positive number ")
	private float price;
	@ManyToOne
	private ProductCategory category;
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List <Ad> ads;
	@OneToMany(mappedBy="product",cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List <UserProductViews> productUsersViews;
	

	public Long getBarCode() {
		return barCode;
	}
	public void setBarCode(Long barCode) {
		if (!isValidBarCode(barCode)) {
			throw new IllegalArgumentException("Invalid Barcode, barcode should be a number with '13' digits and starts with '619'");
		}
		this.barCode = barCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}	
	public List<UserProductViews> getProductUsersViews() {
		return productUsersViews;
	}
	public void setProductUsersViews(List<UserProductViews> productUsersViews) {
		this.productUsersViews = productUsersViews;
	}
	
	public Product() {
	}
	
	public Product(Long barCode, @NotNull(message = "product name is null") String name,
			@NotNull @Positive(message = "The price should be positive number ") float price, ProductCategory category,
			List<Ad> ads, List<UserProductViews> productUsersViews) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.price = price;
		this.category = category;
		this.ads = ads;
		this.productUsersViews = productUsersViews;
	}
	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", ads=" + ads + ", productUsersViews=" + productUsersViews ;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ads == null) ? 0 : ads.hashCode());
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((productUsersViews == null) ? 0 : productUsersViews.hashCode());
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
		Product other = (Product) obj;
		if (ads == null) {
			if (other.ads != null)
				return false;
		} else if (!ads.equals(other.ads))
			return false;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (productUsersViews == null) {
			if (other.productUsersViews != null)
				return false;
		} else if (!productUsersViews.equals(other.productUsersViews))
			return false;
		return true;
	}
	boolean isValidBarCode(Long code ) {
		if((code.toString().indexOf("619")!=0)||(code.toString().length()!=13)) return false;
		return true;
	}	
}
