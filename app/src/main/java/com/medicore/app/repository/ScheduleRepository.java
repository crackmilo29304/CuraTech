package com.medicore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicore.app.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
    
}
