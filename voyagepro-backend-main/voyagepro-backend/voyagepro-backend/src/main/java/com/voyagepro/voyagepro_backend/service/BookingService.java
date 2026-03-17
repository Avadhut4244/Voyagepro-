package com.voyagepro.voyagepro_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.voyagepro.voyagepro_backend.model.Booking;
import com.voyagepro.voyagepro_backend.model.TravelPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.voyagepro.voyagepro_backend.repository.BookingRepository;
import com.voyagepro.voyagepro_backend.repository.TravelPackageRepository;
import com.voyagepro.voyagepro_backend.exception.ResourceNotFoundException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    // CREATE Booking
    public Booking createBooking(Booking booking) {

        Long packageId = booking.getTravelPackage().getId();

        TravelPackage fullPackage = travelPackageRepository.findById(packageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Travel Package not found with id: " + packageId));

        booking.setTravelPackage(fullPackage);
        booking.setStatus("BOOKED");

        return repository.save(booking);
    }

    // GET All Bookings
    public Page<Booking> getAllBookings(Pageable pageable) {
    return repository.findAll(pageable);
    }

    // GET Booking By ID
    public Booking getBookingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with id: " + id));
    }

    // CANCEL Booking
    public Booking cancelBooking(Long id) {
        Booking booking = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with id: " + id));

        booking.setStatus("CANCELLED");
        return repository.save(booking);
    }

    // DELETE Booking
    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    // FILTER by Email
    public List<Booking> getBookingsByEmail(String email) {
        return repository.findByCustomerEmail(email);
    }

    // FILTER by Status
    public List<Booking> getBookingsByStatus(String status) {
        return repository.findByStatus(status);
    }
}