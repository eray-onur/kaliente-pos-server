package com.kaliente.pos.application.services;

import com.kaliente.pos.application.configs.AppConfig;
import com.kaliente.pos.application.models.CurrencyModel;
import com.kaliente.pos.application.requests.currency.AppendNewCurrencyRequest;
import com.kaliente.pos.domain.currency.CurrencyHistory;
import com.kaliente.pos.domain.currency.CurrencyHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CurrencyHistoryService {

    @Autowired
    private CurrencyHistoryRepository currencyHistoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AppConfig appConfig;

    public CurrencyModel appendNewCurrency(AppendNewCurrencyRequest request) {
        CurrencyHistory ch = CurrencyHistory.builder()
                .currencyTitle(request.getCurrencyTitle())
                .baseCrossRate(request.getBaseCrossRate())
                .currencyRate(request.getCurrencyRate())
                .build();

        if(ch.getCurrencyTitle().equals(appConfig.getMainCurrencyTitle())) {
            ch.setCurrencyRate(1);
            ch.setBaseCrossRate(1);
        }

        var result =  currencyHistoryRepository.save(ch);

        var response = new CurrencyModel();

        response.setCurrencyTitle(result.getCurrencyTitle());
        response.setCurrencyRate(result.getCurrencyRate());
        response.setBaseCrossRate(result.getBaseCrossRate());
        response.setCurrencyDate(result.getCurrencyDate());

        return response;

    }

    public CurrencyModel getLatestRateByTitle(String currencyTitle) {
        var histories = currencyHistoryRepository.findByCurrencyTitle(currencyTitle);

        if(histories.isEmpty())
            return null;

        var latestHistory = histories.get(0);

        var response = new CurrencyModel();

        response.setCurrencyTitle(latestHistory.getCurrencyTitle());
        response.setCurrencyRate(latestHistory.getCurrencyRate());
        response.setBaseCrossRate(latestHistory.getBaseCrossRate());
        response.setCurrencyDate(latestHistory.getCreatedOn());

        return response;
    }

    public ArrayList<CurrencyModel> getAllCurrencies() {
        var histories = new ArrayList<>(currencyHistoryRepository.findAll());
        var responseList = new ArrayList<CurrencyModel>();

        for(CurrencyHistory history: histories) {
            var response = new CurrencyModel();

            response.setCurrencyTitle(history.getCurrencyTitle());
            response.setCurrencyRate(history.getCurrencyRate());
            response.setBaseCrossRate(history.getBaseCrossRate());
            response.setCurrencyDate(history.getCurrencyDate());

            responseList.add(response);
        }

        return responseList;
    }

}
