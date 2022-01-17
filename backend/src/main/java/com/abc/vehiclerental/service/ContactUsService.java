package com.abc.vehiclerental.service;

import java.util.List;

import com.abc.vehiclerental.entity.ContactUsDetails;

public interface ContactUsService {

	public void addContact(String role, ContactUsDetails contact);

	public List<ContactUsDetails> fetchAllContactDetails();

	public void updateContact(String role, ContactUsDetails contact);

	public void deleteContact(String role, long id);

}
