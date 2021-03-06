package tn.consomitounsi.www.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -6236517548335858347L;
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		@NotNull
		@Column(unique=true)
	    @Size(max=50)
		private String userName;
		@NotNull
	    @Size(max=50)
		private String password;
		private boolean active;
		private String roles;
		private String firstName;
	    private String lastName;
	    @Email
	    private String email;
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
		@JsonIgnore
		private List <UserProductViews> UserProductsViews;
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
		@JsonIgnore
		private List <UserProductCategoryViews> userProductCategoriesViews;
	  
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName =  firstName;

		}



		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		public List<UserProductCategoryViews> getUserProductCategoriesViews() {
			return userProductCategoriesViews;
		}

		public void setUserProductCategoriesViews(List<UserProductCategoryViews> userProductCategoriesViews) {
			this.userProductCategoriesViews = userProductCategoriesViews;
		}
		
		public List<UserProductViews> getUserProductsViews() {
			return UserProductsViews;
		}
		public void setUserProductsViews(List<UserProductViews> userProductsViews) {
			UserProductsViews = userProductsViews;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}	
		
		public User(){}

		public User(Long id, @NotNull String userName, String password, boolean active, String roles, String firstName,
				String lastName, @Email String email, List<UserProductViews> userProductsViews,
				List<UserProductCategoryViews> userProductCategoriesViews) {
			super();
			this.id = id;
			this.userName = userName;
			this.password = password;
			this.active = active;
			this.roles = roles;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active
					+ ", roles=" + roles + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", UserProductsViews=" + UserProductsViews + ", userProductCategoriesViews="
					+ userProductCategoriesViews + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((UserProductsViews == null) ? 0 : UserProductsViews.hashCode());
			result = prime * result + (active ? 1231 : 1237);
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((roles == null) ? 0 : roles.hashCode());
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			result = prime * result
					+ ((userProductCategoriesViews == null) ? 0 : userProductCategoriesViews.hashCode());
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
			User other = (User) obj;
			if (UserProductsViews == null) {
				if (other.UserProductsViews != null)
					return false;
			} else if (!UserProductsViews.equals(other.UserProductsViews))
				return false;
			if (active != other.active)
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (roles == null) {
				if (other.roles != null)
					return false;
			} else if (!roles.equals(other.roles))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (userProductCategoriesViews == null) {
				if (other.userProductCategoriesViews != null)
					return false;
			} else if (!userProductCategoriesViews.equals(other.userProductCategoriesViews))
				return false;
			return true;
		}

}
