package com.abc.vehiclerental.service;

import com.abc.vehiclerental.entity.GuestFeedback;

public interface GuestFeedbackService {

	public GuestFeedback addQuery(GuestFeedback guestFeedback);

	public void updateFeedback(long queryId, String feedback);

}
