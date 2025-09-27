package com.main.app.Controllers;


import com.main.app.Dto.PharmacyDTO;
import com.main.app.Services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @PostMapping
    public PharmacyDTO createPharmacy(@RequestBody PharmacyDTO dto) {
        return pharmacyService.createPharmacy(dto);
    }

    @GetMapping
    public List<PharmacyDTO> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }

    @GetMapping("/{id}")
    public PharmacyDTO getPharmacy(@PathVariable Long id) {
        return pharmacyService.getPharmacy(id);
    }
}

