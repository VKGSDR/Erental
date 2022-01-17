package com.abc.vehiclerental.payload;

public class DashboardPayload {

	private int noOfBookedCars;
	private int noOfCars;
	private int noOfRegisteredUsers;
	private int noOfSubscribers;
	private int noOfQuery;

	public int getNoOfRegisteredUsers() {
		return noOfRegisteredUsers;
	}

	public int getNoOfQuery() {
		return noOfQuery;
	}

	public void setNoOfQuery(int noOfQuery) {
		this.noOfQuery = noOfQuery;
	}

	public void setNoOfRegisteredUsers(int noOfRegisteredUsers) {
		this.noOfRegisteredUsers = noOfRegisteredUsers;
	}

	public int getNoOfSubscribers() {
		return noOfSubscribers;
	}

	public void setNoOfSubscribers(int noOfSubscribers) {
		this.noOfSubscribers = noOfSubscribers;
	}

	public int getNoOfBookedCars() {
		return noOfBookedCars;
	}

	public void setNoOfBookedCars(int noOfBookedCars) {
		this.noOfBookedCars = noOfBookedCars;
	}

	public int getNoOfCars() {
		return noOfCars;
	}

	public void setNoOfCars(int noOfCars) {
		this.noOfCars = noOfCars;
	}

}
