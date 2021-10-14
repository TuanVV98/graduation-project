package com.spring.repository;

import com.spring.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {
	@Query(value = "select getCountBooking(?1)", nativeQuery = true)
	int getCountBooking(int idCustommer);

	List<Voucher> findAllByStartGreaterThanEqualAndStartLessThanEqual(LocalDateTime startDate, LocalDateTime endDate);

	List<Voucher> findByContent(String title);
}
