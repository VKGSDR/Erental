package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.vehiclerental.entity.ContactUsDetails;

public interface ContactRepository extends JpaRepository<ContactUsDetails, Long> {

}
