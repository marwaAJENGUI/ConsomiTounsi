package tn.consomitounsi.www.entity;

import java.io.Serializable;

public class ProductSearch  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1953860745695194132L;
	String productName ;
	String categoryName ;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ProductSearch(String productName, String categoryName) {
		super();
		this.productName = productName;
		this.categoryName = categoryName;
	}
	public ProductSearch() {
		super();
	}
	@Override
	public String toString() {
		return "ProductSearch [productName=" + productName + ", categoryName=" + categoryName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		ProductSearch other = (ProductSearch) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
}
