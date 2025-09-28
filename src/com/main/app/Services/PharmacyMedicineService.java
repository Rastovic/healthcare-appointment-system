package com.main.app.Services;



import com.main.app.Dto.PharmacyMedicineDTO;

import java.util.List;

public interface PharmacyMedicineService {
    PharmacyMedicineDTO create(PharmacyMedicineDTO dto);
    PharmacyMedicineDTO update(Long id, PharmacyMedicineDTO dto);
    void delete(Long id);
    PharmacyMedicineDTO getById(Long id);
    List<PharmacyMedicineDTO> getByPharmacy(Long pharmacyId);
    List<PharmacyMedicineDTO> getAll();
}

