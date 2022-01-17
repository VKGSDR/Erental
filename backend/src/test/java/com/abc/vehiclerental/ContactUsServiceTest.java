package com.abc.vehiclerental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.vehiclerental.entity.ContactUsDetails;
import com.abc.vehiclerental.exception.ResourceNotFoundException;
import com.abc.vehiclerental.repository.ContactRepository;
import com.abc.vehiclerental.service.ContactUsServiceImpl;

@SpringBootTest
public class ContactUsServiceTest {

	@InjectMocks
	private ContactUsServiceImpl contactUsServiceImpl;

	@Mock
	private ContactRepository contactRepository;

	@Test
	public void testAddContact() {

		ContactUsDetails contactUsDetails = new ContactUsDetails();

		contactUsDetails.setId(100L);
		contactUsDetails.setName("sadhana");
		contactUsDetails.setEmail("sadhanat@gmail.com");
		contactUsDetails.setFax("9458543875");
		contactUsDetails.setMobile("9345788485");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("Admin2");

		contactUsServiceImpl.addContact("admin", contactUsDetails);

		verify(contactRepository, times(1)).save(contactUsDetails);
	}

	@Test
	void testGetAllContacts() {

		ContactUsDetails contactUsDetails = new ContactUsDetails();
		contactUsDetails.setId(100L);
		contactUsDetails.setName("sadhana");
		contactUsDetails.setEmail("sadhanat@gmail.com");
		contactUsDetails.setFax("9458543875");
		contactUsDetails.setMobile("9345788485");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("Admin2");

		ContactUsDetails contactUsDetails1 = new ContactUsDetails();
		contactUsDetails.setId(200L);
		contactUsDetails.setName("akshata");
		contactUsDetails.setEmail("ak@gmail.com");
		contactUsDetails.setFax("9487362109");
		contactUsDetails.setMobile("9349865412");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("");

		ContactUsDetails contactUsDetails2 = new ContactUsDetails();
		contactUsDetails.setId(300L);
		contactUsDetails.setName("shalini");
		contactUsDetails.setEmail("s@gmail.com");
		contactUsDetails.setFax("9487362994");
		contactUsDetails.setMobile("9349865766");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("");

		List<ContactUsDetails> contactList = new ArrayList<>();
		contactList.add(contactUsDetails);
		contactList.add(contactUsDetails1);
		contactList.add(contactUsDetails2);

		when(contactRepository.findAll()).thenReturn(contactList);

		List<ContactUsDetails> contactdetailsList = contactUsServiceImpl.fetchAllContactDetails();
		assertEquals(3, contactdetailsList.size());
	}

	@Test
	public void testUpdateContact() {

		ContactUsDetails contactUsDetails = new ContactUsDetails();

		contactUsDetails.setId(100L);
		contactUsDetails.setName("sadhana");
		contactUsDetails.setEmail("sadhanat@gmail.com");
		contactUsDetails.setFax("9458543875");
		contactUsDetails.setMobile("9345788485");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("Admin2");

		Optional<ContactUsDetails> list = Optional.of(contactUsDetails);

		when(contactRepository.findById(100L)).thenReturn(list);

		contactUsServiceImpl.updateContact("admin", contactUsDetails);

		verify(contactRepository, times(1)).save(contactUsDetails);

	}

	@Test
	public void testDeleteContact() {

		ContactUsDetails contactUsDetails = new ContactUsDetails();

		contactUsDetails.setId(100L);
		contactUsDetails.setName("sadhana");
		contactUsDetails.setEmail("sadhanat@gmail.com");
		contactUsDetails.setFax("9458543875");
		contactUsDetails.setMobile("9345788485");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("Admin2");

		Optional<ContactUsDetails> list = Optional.of(contactUsDetails);

		when(contactRepository.findById(100L)).thenReturn(list);
		contactUsServiceImpl.deleteContact("admin", 100L);

		verify(contactRepository, times(1)).deleteById(100L);
	}

	@Test
	void testDeleteContactByIdThrowingException() {

		when(contactRepository.findById(120L)).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> contactUsServiceImpl.deleteContact("admin", 120L));
	}

	@Test
	void testUpdateContactByIdThrowingException() {

		ContactUsDetails contactUsDetails = new ContactUsDetails();

		contactUsDetails.setId(120L);
		contactUsDetails.setName("sadhana");
		contactUsDetails.setEmail("sadhanat@gmail.com");
		contactUsDetails.setFax("9458543875");
		contactUsDetails.setMobile("9345788485");
		contactUsDetails.setCreatedBy("sadhana");
		contactUsDetails.setModifiedBy("Admin2");

		when(contactRepository.findById(120L)).thenThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class,
				() -> contactUsServiceImpl.updateContact("admin", contactUsDetails));
	}

}