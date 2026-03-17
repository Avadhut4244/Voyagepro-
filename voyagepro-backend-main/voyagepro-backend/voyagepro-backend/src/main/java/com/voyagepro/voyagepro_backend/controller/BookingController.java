package com.voyagepro.voyagepro_backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.voyagepro.voyagepro_backend.model.Booking;
import com.voyagepro.voyagepro_backend.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService service;

    // CREATE Booking
    @PostMapping
    public Booking createBooking(@Valid @RequestBody Booking booking) {
        return service.createBooking(booking);
    }

    // GET All Bookings
    @GetMapping
    public Object getBookings(
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String status,
        Pageable pageable) {

    if (email != null) {
        return service.getBookingsByEmail(email);
    }

    if (status != null) {
        return service.getBookingsByStatus(status);
    }

    return service.getAllBookings(pageable);
    }

    // GET Booking By ID
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }

    // CANCEL Booking
    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(@PathVariable Long id) {
        return service.cancelBooking(id);
    }

    // DELETE Booking
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
        return "Booking deleted successfully";
    }
}