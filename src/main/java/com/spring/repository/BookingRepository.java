package com.spring.repository;

import com.spring.model.Accounts;
import com.spring.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select bk from Booking bk where bk.scheduleTime.id=?1")
    Optional<Booking> findByScheduleTimeId(Long id);

    @Query("select bk from Booking bk where bk.customerProfile.id=?1")
    List<Booking> findByCustomerId(Long id);

    @Query("select bk from Booking bk where bk.dentistProfile.id=?1")
    List<Booking> findByDentistId(Long id);
}
