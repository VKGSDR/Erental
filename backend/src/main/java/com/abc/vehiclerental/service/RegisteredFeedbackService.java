package com.abc.vehiclerental.service;

import com.abc.vehiclerental.entity.RegisteredFeedback;

public interface RegisteredFeedbackService {

	public RegisteredFeedback addQuery(long userId, RegisteredFeedback registeredFeedback);

	public void updateFeedback(long queryId, String feedback);

}
