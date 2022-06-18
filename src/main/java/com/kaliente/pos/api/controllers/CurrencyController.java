package com.kaliente.pos.api.controllers;

import com.kaliente.pos.application.models.CurrencyDto;
import com.kaliente.pos.application.models.base.BaseResponse;
import com.kaliente.pos.application.models.dtos.productcatalogue.GetProductCatalogueByIdResponseDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.requests.currency.AppendNewCurrencyRequest;
import com.kaliente.pos.application.services.CurrencyHistoryService;
import com.kaliente.pos.sharedkernel.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyHistoryService currencyHistoryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<ArrayList<CurrencyDto>>> getAll() {

        var response = this.currencyHistoryService.getAllCurrencies();

        return new ResponseEntity<>(new BaseResponse<ArrayList<CurrencyDto>>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
    @GetMapping("/getLatestByTitle/{title}")
    public ResponseEntity<BaseResponse<CurrencyDto>> getLatestByTitle(@PathVariable("title") String title) {

        var response = this.currencyHistoryService.getLatestRateByTitle(title);

        return new ResponseEntity<>(new BaseResponse<CurrencyDto>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
    @PostMapping("/appendNew")
    public ResponseEntity<BaseResponse<CurrencyDto>> appendNewCurrency(@RequestBody AppendNewCurrencyRequest request) {

        var response = this.currencyHistoryService.appendNewCurrency(request);

        return new ResponseEntity<>(new BaseResponse<CurrencyDto>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);

    }

}
