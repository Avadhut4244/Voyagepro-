package com.voyagepro.voyagepro_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.voyagepro.voyagepro_backend.model.TravelPackage;
import com.voyagepro.voyagepro_backend.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

    @Autowired
    private TravelPackageRepository repository;

    public TravelPackage addPackage(TravelPackage travelPackage) {
        return repository.save(travelPackage);
    }

    public List<TravelPackage> getAllPackages() {
        return repository.findAll();
    }

    public TravelPackage getPackageById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TravelPackage updatePackage(Long id, TravelPackage updatedPackage) {
        return repository.findById(id).map(pkg -> {
            pkg.setName(updatedPackage.getName());
            pkg.setDestination(updatedPackage.getDestination());
            pkg.setPrice(updatedPackage.getPrice());
            pkg.setDuration(updatedPackage.getDuration());
            return repository.save(pkg);
        }).orElse(null);
    }

    public void deletePackage(Long id) {
        repository.deleteById(id);
    }
}