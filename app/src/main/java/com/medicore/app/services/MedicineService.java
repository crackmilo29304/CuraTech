package com.medicore.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.BatchStock;
import com.medicore.app.models.Medicine;
import com.medicore.app.models.Prescription;
import com.medicore.app.repository.BatchStockRepository;
import com.medicore.app.repository.MedicineRepository;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private BatchStockRepository batchStockRepository;

    public List<BatchStock> getStock(){
        return batchStockRepository.findAll();
    }
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    public boolean isAvailable(Prescription prescription) {
       return batchStockRepository.existsByMedicineAndQuantityGreaterThan(prescription.getMedicine(), 0);
    }
    public Prescription setBatchStock(Prescription prescription) {
       BatchStock batchStock = batchStockRepository.findByMedicineAndQuantityGreaterThan(prescription.getMedicine(), 0);
       batchStock.setQuantity(batchStock.getQuantity() - 1);
       prescription.setBatchStock(batchStock);
       return prescription;
       
    }

}
