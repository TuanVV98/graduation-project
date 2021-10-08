package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Long> {
    //read all by deleteAt=TRUE
    public List<ScheduleTime> findByDeleteAtIsTrue();

    //read all by deleteAT=FALSE
    public List<ScheduleTime> findByDeleteAtIsFalse();

    @Query("SELECT e FROM ScheduleTime e WHERE e.dentistProfile.id=:dentistProfileId AND " +
            "e.deleteAt=FALSE")
    public List<ScheduleTime> findAllTimeByDentinstId(@Param("dentistProfileId") Long dentistProfileId);

//    @Query("SELECT e FROM ScheduleTime e WHERE e.bookings.=:bookingId AND " +
//            "e.deleteAt=FALSE")
//    public ScheduleTime findAllTimeByBookingId(@Param("bookingId") Long bookingId);
}
