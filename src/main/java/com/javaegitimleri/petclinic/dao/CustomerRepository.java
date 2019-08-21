package com.javaegitimleri.petclinic.dao;

import java.util.List;

import com.javaegitimleri.petclinic.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();
	Customer findById(int id);
	List<Customer> findByLastName(String lastName);
	void create(Customer customer);
	Customer update(Customer customer);
	void delete(int id);
}
