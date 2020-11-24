package com.app.iami.repository;

import com.app.iami.model.CheckingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingFormRepository extends JpaRepository<CheckingForm, Integer> {
}
