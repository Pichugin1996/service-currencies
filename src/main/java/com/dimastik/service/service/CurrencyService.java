package com.dimastik.service.service;

import com.dimastik.service.client.CurrencyClient;
import com.dimastik.service.dto.CurrencyResponse;
import com.dimastik.service.exception.CurrencyCodInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.OffsetDateTime;
import java.util.Map;

@Service
public class CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    private final CurrencyClient currencyClient;

    private final String CURRENCY_API_KEY;

    private final String CURRENCY_COD_CURRENCY;

    private final GifService gifService;

    @Autowired
    public CurrencyService(CurrencyClient currencyClient,
                           @Value("${currency.key}") String CURRENCY_API_KEY,
                           @Value("${currency.CodCurrency}") String CURRENCY_COD_CURRENCY,
                           GifService gifService) {
        this.currencyClient = currencyClient;
        this.CURRENCY_API_KEY = CURRENCY_API_KEY;
        this.CURRENCY_COD_CURRENCY = CURRENCY_COD_CURRENCY;
        this.gifService = gifService;
    }

    public RedirectView inquire(String currencyCod) {
        LOGGER.info("Cur: Сервис получение GIF в зависимости от значение валюты - START");
        LOGGER.info("Cur: Переданный код валюты: " + currencyCod);
        return new RedirectView(comparison(currencyCod));
    }

    private CurrencyResponse getCurrentCurrency() {
        ResponseEntity<CurrencyResponse> currencyResponse = currencyClient
                .getCurrentCurrency(CURRENCY_API_KEY, CURRENCY_COD_CURRENCY);
        return currencyResponse.getBody();
    }

    private CurrencyResponse getYesterdaysCurrency() {
        String date = OffsetDateTime.now().minusDays(1).toLocalDate().toString();
        ResponseEntity<CurrencyResponse> currencyResponse =
                currencyClient.getYesterdaysCurrency(date, CURRENCY_API_KEY);
        return currencyResponse.getBody();
    }

    private String comparison(String currencyCod) {
        Map<String, Double> currentCurrencyMap = getCurrentCurrency().getRates();
        if (!currentCurrencyMap.containsKey(currencyCod)) {
            LOGGER.info("Cur: Код валюты : " + currencyCod + " не найден");
            LOGGER.info("Cur: Установленно значение по умолочанию : RUB");
            currencyCod = "RUB";
//            throw new CurrencyCodInvalidException("Код валюты не найден");
        }

        Double currentCurrency = currentCurrencyMap.get(currencyCod);
        LOGGER.info("Cur: Текущее значение валюты: " + currencyCod
                + " по отношению к " + CURRENCY_COD_CURRENCY + " равна: " + currentCurrency);
        Double yesterdaysCurrency = getYesterdaysCurrency().getRates().get(currencyCod);
        LOGGER.info("Cur: Вчерашнее значение валюты: " + currencyCod
                + " по отношению к " + CURRENCY_COD_CURRENCY + " равна: " + yesterdaysCurrency);
        LOGGER.info("Cur: Вывод GIF по тегу: " + (currentCurrency >= yesterdaysCurrency ? "rich":"broke"));

        return currentCurrency >= yesterdaysCurrency
                ? gifService.getGif("rich") : gifService.getGif("broke");
    }
}













