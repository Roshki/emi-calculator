package com.emicalculatorbackend.emicalculator.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmiDataRequest {

    private double loanValue;

    private double yearlyInterestRate;

    private int loanTermInYears;
}
