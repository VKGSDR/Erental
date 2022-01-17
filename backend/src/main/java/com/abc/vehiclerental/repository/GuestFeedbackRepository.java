package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.vehiclerental.entity.GuestFeedback;

public interface GuestFeedbackRepository extends JpaRepository<GuestFeedback, Long> {

	@Modifying(clearAutomatically = true)
	@Query("UPDATE GuestFeedback c SET c.feedback = :feedback WHERE c.queryId= :queryId")
	int updateFeedback(@Param("queryId") long queryId, @Param("feedback") String feedback);

	@Query("Select count(*) from GuestFeedback")
	public int findByQuery();

}
