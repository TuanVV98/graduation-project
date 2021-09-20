package com.spring.repository;

import com.spring.model.Accounts;
import com.spring.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
