package com.main.app.Services;


import com.main.app.Dto.PharmacyOrderDTO;
import com.main.app.Mappers.PrescriptionMapper;
import com.main.app.Model.PharmacyOrder;
import com.main.app.Repositories.PharmacyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyOrderService {

    private final PharmacyOrderRepository orderRepository;

    // Create new pharmacy order (send prescription to pharmacy)
    public PharmacyOrderDTO createOrder(PharmacyOrderDTO dto) {
        PharmacyOrder entity = PrescriptionMapper.toOrderEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus("PENDING"); // default status

        PharmacyOrder saved = orderRepository.save(entity);
        return PrescriptionMapper.toOrderDTO(saved);
    }

    // Update order status
    public PharmacyOrderDTO updateStatus(Long id, String status) {
        PharmacyOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        PharmacyOrder saved = orderRepository.save(order);
        return PrescriptionMapper.toOrderDTO(saved);
    }

    // Get by ID
    public PharmacyOrderDTO getOrder(Long id) {
        return orderRepository.findById(id)
                .map(PrescriptionMapper::toOrderDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Get all by pharmacy
    public List<PharmacyOrderDTO> getOrdersByPharmacy(Long pharmacyId) {
        return orderRepository.findByPharmacyId(pharmacyId).stream()
                .map(PrescriptionMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get all by prescription
    public List<PharmacyOrderDTO> getOrdersByPrescription(Long prescriptionId) {
        return orderRepository.findByPrescriptionId(prescriptionId).stream()
                .map(PrescriptionMapper::toOrderDTO)
                .collect(Collectors.toList());
    }
}
