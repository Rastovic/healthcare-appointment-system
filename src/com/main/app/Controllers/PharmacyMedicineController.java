package com.main.app.Controllers;


import com.main.app.Dto.PharmacyMedicineDTO;
import com.main.app.Services.PharmacyMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class PharmacyMedicineController {

    private final PharmacyMedicineService service;

    @PostMapping
    public PharmacyMedicineDTO create(@RequestBody PharmacyMedicineDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PharmacyMedicineDTO update(@PathVariable Long id, @RequestBody PharmacyMedicineDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public PharmacyMedicineDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<PharmacyMedicineDTO> getAll(@RequestParam(required = false) Long pharmacyId) {
        if (pharmacyId != null) {
            return service.getByPharmacy(pharmacyId);
        }
        return service.getAll();
    }
}
