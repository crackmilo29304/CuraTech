package com.medicore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Medicine;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
    Medicine findByName(String name);
}
