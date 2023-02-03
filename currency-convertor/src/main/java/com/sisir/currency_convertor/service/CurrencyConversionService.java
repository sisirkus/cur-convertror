package com.sisir.currency_convertor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class CurrencyConversionService {

    public HttpResponse<String> convert(String fromCurrency, String toCurrency, Integer amount){

        HttpResponse<String> response = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from="
                        +fromCurrency+"&to="+toCurrency+"&amount="+amount))
                .header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
                .header("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            log.error("found error while performing currency conversion, cause : {}", e);
            throw new RuntimeException("something went wrong!");
        }
        return response;
    }
}
