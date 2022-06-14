package com.dimastik.service.client;

import com.dimastik.service.dto.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client", url = "${currency.url}")
public interface CurrencyClient {

    @RequestMapping(value = "/api/latest.json", method = RequestMethod.GET)
    ResponseEntity<CurrencyResponse> getCurrentCurrency(@RequestParam("app_id") String key,
                                                        @RequestParam(value = "base",required = false) String base);

    @RequestMapping(value = "/api/historical/{date}.json", method = RequestMethod.GET)
    ResponseEntity<CurrencyResponse> getYesterdaysCurrency(@PathVariable String date,
                                                           @RequestParam("app_id") String key);

}
