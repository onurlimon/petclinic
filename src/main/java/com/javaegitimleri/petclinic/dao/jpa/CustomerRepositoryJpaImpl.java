package com.javaegitimleri.petclinic.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.CustomerRepository;
import com.javaegitimleri.petclinic.model.Customer;

@Repository
public abstract class CustomerRepositoryJpaImpl implements CustomerRepository{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Customer> findAll() {
		return entityManager.createQuery("from Owner",Customer.class).getResultList();
	}

	@Override
	public Customer findById(int id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return entityManager.createQuery("from Customer where lastName = :lastName",Customer.class).setParameter("lastName", lastName).getResultList();
	}

	@Override
	public void create(Customer customer) {
		entityManager.persist(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return entityManager.merge(customer);
	}

	@Override
	public void delete(int id) {
		 entityManager.remove(entityManager.getReference(Customer.class, id));
		
	}
	
	
	
}
