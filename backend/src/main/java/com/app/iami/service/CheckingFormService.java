package com.app.iami.service;

import com.app.iami.model.CheckingForm;
import com.app.iami.model.Course;
import com.app.iami.payload.request.CheckingFormRequest;
import com.app.iami.repository.CheckingFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingFormService {

    private final CheckingFormRepository checkingFormRepository;

    public CheckingFormService(CheckingFormRepository checkingFormRepository) {
        this.checkingFormRepository = checkingFormRepository;
    }
//
//    public List<CheckingForm> getCheckingForms(){
//        return checkingFormRepository.findAll();
//    }
//
//    public List<CheckingForm> findAll() {
//        return checkingFormRepository.findAll();
//    }

    public CheckingForm insertCheckingForm(CheckingFormRequest checkingForm, Course course){
       String name = checkingForm.getName();
       Float weight = checkingForm.getWeight();

        CheckingForm newCheckingForm = CheckingForm.builder()
                .name(name)
                .course(course)
                .weight(weight)
                .build();

        return checkingFormRepository.save(newCheckingForm);
    }

    public CheckingForm findById(Integer idCheckingForm) {
        return checkingFormRepository.findById(idCheckingForm).orElseThrow();
    }

    public List<CheckingForm> findAll() {
        return checkingFormRepository.findAll();
    }
}
