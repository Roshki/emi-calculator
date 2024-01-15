package com.emicalculatorbackend.emicalculator.repository;

import com.emicalculatorbackend.emicalculator.model.EmiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmiRepository extends JpaRepository<EmiData, Long> {
}
