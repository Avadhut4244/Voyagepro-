package com.voyagepro.voyagepro_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.voyagepro.voyagepro_backend.model.Booking;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerEmail(String customerEmail);

    List<Booking> findByStatus(String status);
}