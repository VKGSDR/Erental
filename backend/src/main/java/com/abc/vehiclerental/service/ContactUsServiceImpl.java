package com.abc.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.vehiclerental.entity.ContactUsDetails;
import com.abc.vehiclerental.exception.AuthenticationFailureException;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.ContactRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void addContact(String role, ContactUsDetails contact) {
		if (role.equals("admin")) {
			contactRepository.save(contact);
		} else {
			throw new AuthenticationFailureException("Only admins can update contact details");
		}
	}

	@Override
	public void updateContact(String role, ContactUsDetails contact) {
		if (role.equals("admin")) {
			Optional<ContactUsDetails> optionalContact = contactRepository.findById(contact.getId());
			if (optionalContact.isEmpty()) {
				throw new ResourceNotFoundException("Contact does not exist with id: " + contact.getId());
			}
			contactRepository.save(contact);
		} else {
			throw new AuthenticationFailureException("Only admins can update contact details");
		}
	}

	@Override
	public void deleteContact(String role, long id) {
		if (role.equals("admin")) {
			Optional<ContactUsDetails> optionalContact = contactRepository.findById(id);
			if (optionalContact.isEmpty()) {
				throw new ResourceNotFoundException("Contact does not exist with id: " + id);
			}
			contactRepository.deleteById(id);
		} else {
			throw new AuthenticationFailureException("Only admins can update contact details");
		}
	}

	@Override
	public List<ContactUsDetails> fetchAllContactDetails() {
		List<ContactUsDetails> contactDetailsList = contactRepository.findAll();
		return contactDetailsList;

	}

}