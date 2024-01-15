package com.emicalculatorbackend.emicalculator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmiDataResponse {
    double emi;
    String message;
}
