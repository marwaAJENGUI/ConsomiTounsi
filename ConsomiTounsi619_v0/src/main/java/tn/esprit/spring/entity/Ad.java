package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Ad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2329896158303253039L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Canal;
	@Temporal(TemporalType.DATE)
	private Date beginningDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private int targetViews;
	private int views;
	@ManyToOne
	private  Product product;
	@ManyToOne
	private AdCategory category;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCanal() {
		return Canal;
	}
	public void setCanal(String canal) {
		Canal = canal;
	}
	public Date getBeginningDate() {
		return beginningDate;
	}
	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTargetViews() {
		return targetViews;
	}
	public void setTargetViews(int targetViews) {
		this.targetViews = targetViews;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public AdCategory getCategory() {
		return category;
	}
	public void setCategory(AdCategory category) {
		this.category = category;
	}
	
	public Ad() {
		super();
	}
	public Ad(Long id, String canal, Date beginningDate, Date endDate, int targetViews, int views, Product product,
			AdCategory category) {
		super();
		Id = id;
		Canal = canal;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.targetViews = targetViews;
		this.views = views;
		this.product = product;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Ad [Id=" + Id + ", Canal=" + Canal + ", beginningDate=" + beginningDate + ", endDate=" + endDate
				+ ", targetViews=" + targetViews + ", views=" + views + ", product=" + product + ", category="
				+ category + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Canal == null) ? 0 : Canal.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((beginningDate == null) ? 0 : beginningDate.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + targetViews;
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
		Ad other = (Ad) obj;
		if (Canal == null) {
			if (other.Canal != null)
				return false;
		} else if (!Canal.equals(other.Canal))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (beginningDate == null) {
			if (other.beginningDate != null)
				return false;
		} else if (!beginningDate.equals(other.beginningDate))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (targetViews != other.targetViews)
			return false;
		if (views != other.views)
			return false;
		return true;
	}
	
}