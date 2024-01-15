package com.emicalculatorbackend.emicalculator.service;

import com.emicalculatorbackend.emicalculator.exceptions.EmiException;
import com.emicalculatorbackend.emicalculator.model.EmiData;
import com.emicalculatorbackend.emicalculator.model.EmiDataRequest;
import com.emicalculatorbackend.emicalculator.repository.EmiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmiService {

    private final EmiRepository emiRepository;

    public double getEmiResult(EmiDataRequest emiDataRequest) {
        double P = emiDataRequest.getLoanValue();
        double R = emiDataRequest.getYearlyInterestRate() / 12;
        int N = emiDataRequest.getLoanTermInYears() * 12;
        validateZeroDivision(R, N);
        double emi = (P * R * Math.pow(1 + R, N))
                / (Math.pow(1 + R, N) - 1);
        emiRepository.save(EmiData.builder()
                .result(emi).build());
        return emi;
    }

    private static void validateZeroDivision(double R, int N) {
        if (Math.pow(1 + R, N) - 1 == 0) {
            throw new EmiException("leading to division by zero. Verify you inputs!");
        }
    }


}
