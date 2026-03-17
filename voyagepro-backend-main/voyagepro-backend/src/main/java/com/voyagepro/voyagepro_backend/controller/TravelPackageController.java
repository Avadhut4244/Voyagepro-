package com.voyagepro.voyagepro_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.voyagepro.voyagepro_backend.model.TravelPackage;
import com.voyagepro.voyagepro_backend.service.TravelPackageService;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin(origins = "*")
public class TravelPackageController {

    @Autowired
    private TravelPackageService service;

    // CREATE - Add new package
    @PostMapping
    public TravelPackage addPackage(@RequestBody TravelPackage travelPackage) {
        return service.addPackage(travelPackage);
    }

    // READ - Get all packages
    @GetMapping
    public List<TravelPackage> getAllPackages() {
        return service.getAllPackages();
    }

    // READ - Get package by ID
    @GetMapping("/{id}")
    public TravelPackage getPackageById(@PathVariable Long id) {
        return service.getPackageById(id);
    }

    // UPDATE - Update package
    @PutMapping("/{id}")
    public TravelPackage updatePackage(@PathVariable Long id,
                                       @RequestBody TravelPackage travelPackage) {
        return service.updatePackage(id, travelPackage);
    }

    // DELETE - Delete package
    @DeleteMapping("/{id}")
    public String deletePackage(@PathVariable Long id) {
        service.deletePackage(id);
        return "Package deleted successfully";
    }
}