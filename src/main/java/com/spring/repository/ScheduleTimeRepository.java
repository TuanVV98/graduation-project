package com.spring.repository;

import com.spring.model.EWallet;
import com.spring.model.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Long> {
}
