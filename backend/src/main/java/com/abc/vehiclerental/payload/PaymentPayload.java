package com.abc.vehiclerental.payload;

public class PaymentPayload {

	private long userBookingId;
	private int noOfDays;

	public long getUserBookingId() {
		return userBookingId;
	}

	public void setUserBookingId(long userBookingId) {
		this.userBookingId = userBookingId;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

}