package com.app.iami.service;

import com.app.iami.model.CheckingForm;
import com.app.iami.repository.CheckingFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingFormService {

    private final CheckingFormRepository checkingFormRepository;

    public CheckingFormService(CheckingFormRepository checkingFormRepository) {
        this.checkingFormRepository = checkingFormRepository;
    }

    public List<CheckingForm> getCheckingForms(){
        return checkingFormRepository.findAll();
    }
}
