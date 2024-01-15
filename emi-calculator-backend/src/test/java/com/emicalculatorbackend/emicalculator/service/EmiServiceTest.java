package com.emicalculatorbackend.emicalculator.service;

import com.emicalculatorbackend.emicalculator.exceptions.EmiException;
import com.emicalculatorbackend.emicalculator.model.EmiDataRequest;
import com.emicalculatorbackend.emicalculator.repository.EmiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EmiServiceTest {

    @Mock
    private EmiRepository emiRepository;

    @InjectMocks
    private EmiService emiService;


    @Test
    void getEmiResult_success() {
        EmiDataRequest emiDataRequest = EmiDataRequest.builder()
                .loanValue(10000)
                .loanTermInYears(6)
                .yearlyInterestRate(12).build();

        assertEquals(10000.0, emiService.getEmiResult(emiDataRequest));

    }

    @Test
    void getEmiResult_success_allowsDecimalsForInterest() {
        EmiDataRequest emiDataRequest = EmiDataRequest.builder()
                .loanValue(10000)
                .loanTermInYears(6)
                .yearlyInterestRate(12.37).build();

        assertEquals(10308.333333333332, emiService.getEmiResult(emiDataRequest));

    }

    @Test
    void getEmiResult_wrong_value_division_byZero() {
        EmiDataRequest emiDataRequest = EmiDataRequest.builder()
                .loanValue(10000)
                .loanTermInYears(0)
                .yearlyInterestRate(12).build();
        assertThrows(EmiException.class, () -> emiService.getEmiResult(emiDataRequest));
    }
}