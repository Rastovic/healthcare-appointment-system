package com.main.app.Controllers;


import com.main.app.Dto.PharmacyOrderDTO;
import com.main.app.Services.PharmacyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class PharmacyOrderController {

    private final PharmacyOrderService orderService;

    @PostMapping
    public PharmacyOrderDTO createOrder(@RequestBody PharmacyOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @PatchMapping("/{id}/status")
    public PharmacyOrderDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public PharmacyOrderDTO getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/pharmacy/{pharmacyId}")
    public List<PharmacyOrderDTO> getOrdersByPharmacy(@PathVariable Long pharmacyId) {
        return orderService.getOrdersByPharmacy(pharmacyId);
    }

    @GetMapping("/prescription/{prescriptionId}")
    public List<PharmacyOrderDTO> getOrdersByPrescription(@PathVariable Long prescriptionId) {
        return orderService.getOrdersByPrescription(prescriptionId);
    }
}

