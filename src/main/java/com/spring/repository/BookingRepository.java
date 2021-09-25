package com.spring.repository;

import com.spring.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select bk from Booking bk where bk.scheduleTime.id=?1")
    Optional<Booking> findByScheduleTimeId(Long id);
}
