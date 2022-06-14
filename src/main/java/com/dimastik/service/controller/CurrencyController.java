package com.dimastik.service.controller;

import com.dimastik.service.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{currencyCod}")
    public RedirectView getCurrencyByCod(@PathVariable(required = false) String currencyCod) {
        return currencyService.inquire(currencyCod);
    }

    @GetMapping("*")
    public RedirectView inquire(
            @RequestParam(value = "currencyCod", defaultValue = "RUB") String currencyCod) {
        return currencyService.inquire(currencyCod);
    }

}
