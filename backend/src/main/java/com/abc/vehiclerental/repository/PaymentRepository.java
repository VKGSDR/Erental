package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.vehiclerental.entity.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

}
