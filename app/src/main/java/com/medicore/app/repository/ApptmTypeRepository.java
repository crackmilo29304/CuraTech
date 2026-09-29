package com.medicore.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicore.app.models.ApptmType;
import com.medicore.app.models.Role;

public interface ApptmTypeRepository extends JpaRepository<ApptmType, Integer> {
    Optional<ApptmType> findByRole(Role role);
}
