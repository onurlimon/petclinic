package com.javaegitimleri.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Customer extends BaseEntity {
	
	@NotEmpty
	@Column(name="first_name")
	private String first_name;
	
	@NotEmpty
	@Column(name="last_name")
	private String last_name;
	
	@NotEmpty
	@Column(name="customer_id")
	private String customer_id;

	public Customer(@NotEmpty String first_name, @NotEmpty String last_name, @NotEmpty String customer_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	

	
}
