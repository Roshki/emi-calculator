package com.emicalculatorbackend.emicalculator.controller;

import com.emicalculatorbackend.emicalculator.exceptions.EmiException;
import com.emicalculatorbackend.emicalculator.model.EmiDataRequest;
import com.emicalculatorbackend.emicalculator.model.EmiDataResponse;
import com.emicalculatorbackend.emicalculator.service.EmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmiController {

    private final EmiService emiService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/calculate-emi")
    public ResponseEntity<EmiDataResponse> getCountOfUamAndAudit(@RequestBody EmiDataRequest emiDataRequest) {
        try {
            double emiResult = emiService.getEmiResult(emiDataRequest);
            return ResponseEntity.status(HttpStatus.OK).body(EmiDataResponse.builder().emi(emiResult).message("The result is successfully calculated and saved").build());
        } catch (EmiException emiException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EmiDataResponse.builder().message(emiException.getMessage()).build());
        }
    }
}
