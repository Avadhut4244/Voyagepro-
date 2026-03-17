package com.voyagepro.voyagepro_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer email is required")
    private String customerEmail;

    @Future(message = "Travel date must be in the future")
    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;

    private String status;

    @NotNull(message = "Travel package is required")
    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private TravelPackage travelPackage;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public String getStatus() {
        return status;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }
}