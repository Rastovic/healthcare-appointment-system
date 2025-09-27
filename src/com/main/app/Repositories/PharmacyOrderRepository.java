package com.main.app.Repositories;

import com.main.app.Model.PharmacyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {
    List<PharmacyOrder> findByPharmacyId(Long pharmacyId);
    List<PharmacyOrder> findByPrescriptionId(Long prescriptionId);
    List<PharmacyOrder> findByStatus(String status);}
