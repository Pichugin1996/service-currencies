package com.dimastik.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyResponse {

    private String timestamp;

    private String base;

    private Map<String, Double> rates;
}
