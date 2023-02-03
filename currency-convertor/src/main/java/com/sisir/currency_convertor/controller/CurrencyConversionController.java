package com.sisir.currency_convertor.controller;

import com.sisir.currency_convertor.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@Slf4j
@RestController("/cur-convertor/api/v1")
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/convert")
    public HttpResponse<String> convertor(@PathVariable String fromCurrency, @PathVariable String toCurrency, @PathVariable Integer amount) {

        log.info("converting {} to {} for {} nos.", fromCurrency, toCurrency, amount);
        if (StringUtils.isNoneEmpty(fromCurrency, toCurrency) && amount != null) {
            return currencyConversionService.convert(fromCurrency, toCurrency, amount);

        } else {
            throw new RuntimeException("found invalid params");
        }
    }

}
