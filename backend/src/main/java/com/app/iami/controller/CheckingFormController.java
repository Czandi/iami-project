package com.app.iami.controller;

import com.app.iami.model.CheckingForm;
import com.app.iami.service.CheckingFormService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CheckingFormController {

    private final CheckingFormService checkingFormService;

    public CheckingFormController(CheckingFormService checkingFormService) {
        this.checkingFormService = checkingFormService;
    }

//    @GetMapping("/checking-forms")
//    public List<CheckingForm> getCheckingForms(){
//        return checkingFormService.getCheckingForms();
//    }
}
