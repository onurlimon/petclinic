package com.javaegitimleri.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.dao.PetRepository;
import com.javaegitimleri.petclinic.dao.jpa.VetRepository;
import com.javaegitimleri.petclinic.exception.OwnerNotFoundException;
import com.javaegitimleri.petclinic.exception.VetNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Vet;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

	private OwnerRepository burak;

	private PetRepository petRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	private VetRepository vetRepository;
	
	@Autowired
	public void setVetRepository(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.burak = ownerRepository;
	}

	@Autowired
	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	//@Secured(value = { "ROLE_USER", "ROLE_EDITOR" })
	public List<Owner> findOwners() {
		return burak.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Owner> findOwners(String lastName) {
		return burak.findByLastName(lastName);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Owner findOwner(Long id) throws OwnerNotFoundException {
		Owner owner = burak.findById(id);
		if (owner == null) {
			throw new OwnerNotFoundException("Owner not found with id :" + id);
		}
		return owner;
	}

	@Override
	public void createOwner(Owner owner) {
		burak.create(owner);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("k@s");
		msg.setTo("m@y");
		msg.setSubject("Owner created!");
		msg.setText("Owner entity with id :" + owner.getId() + " created successfully.");

		mailSender.send(msg);
	}

	@Override
	public void updateOwner(Owner owner) {
		burak.update(owner);
	}

	@Override
	public void deleteOwner(Long id) {
		petRepository.deleteByOwnerId(id);
		burak.delete(id);
		// if(true) throw new RuntimeException("testing rollback...");
	}

	@Override
	public List<Vet> findVets() {
		return vetRepository.findAll();
	}

	@Override
	public Vet findVet(Long id) throws VetNotFoundException {
		return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet not found by id :" + id);});
	}

}
