package com.abc.vehiclerental;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.vehiclerental.entity.GuestFeedback;
import com.abc.vehiclerental.repository.GuestFeedbackRepository;
import com.abc.vehiclerental.service.GuestFeedbackServiceImpl;

@SpringBootTest
public class GuestFeedbackServiceTest {

	@InjectMocks
	private GuestFeedbackServiceImpl guestFeedbackServiceImpl;

	@Mock
	private GuestFeedbackRepository guestFeedbackRepository;

	@Test
	public void testaddQuery() {

		GuestFeedback guestFeedback = new GuestFeedback();
		guestFeedback.setEmail("shreya2@gmail.com");
		guestFeedback.setMobile("9820765432");
		guestFeedback.setName("Shreya");
		guestFeedback.setQuery("Not able to book");

		guestFeedbackServiceImpl.addQuery(guestFeedback);
		verify(guestFeedbackRepository, times(1)).save(guestFeedback);

	}

	@Test
	public void testUpdateFeedback() {

		GuestFeedback guestFeedback = new GuestFeedback();
		guestFeedback.setQueryId(1);
		guestFeedback.setFeedback("Please check");

		Optional<GuestFeedback> optionalGuestFeedback = guestFeedbackRepository.findById(1L);
		if (optionalGuestFeedback.isPresent()) {

			guestFeedbackServiceImpl.updateFeedback(1, "Please Check");

			verify(guestFeedbackRepository, times(1)).save(guestFeedback);

		}

	}
}
