package com.main.app.Repositories;


import com.main.app.Model.PharmacyMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyMedicineRepository extends JpaRepository<PharmacyMedicine, Long> {
    List<PharmacyMedicine> findByPharmacyId(Long pharmacyId);
}

