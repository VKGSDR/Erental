package com.abc.vehiclerental.payload;

public class FeedbackPayload {

	private long queryId;
	private String feedback;

	public long getQueryId() {
		return queryId;
	}

	public void setQueryId(long queryId) {
		this.queryId = queryId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public FeedbackPayload(long queryId, String feedback) {
		super();
		this.queryId = queryId;
		this.feedback = feedback;
	}

	public FeedbackPayload() {

	}

}
