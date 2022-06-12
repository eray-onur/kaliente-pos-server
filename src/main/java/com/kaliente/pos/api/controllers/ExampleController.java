package com.kaliente.pos.api.controllers;

import com.kaliente.pos.application.models.base.BaseResponse;
import com.kaliente.pos.application.models.dtos.product.GetProductByIdResponseDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.domain.Example;
import com.kaliente.pos.domain.ExampleRepository;
import com.kaliente.pos.sharedkernel.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("example")
public class ExampleController {

    @Autowired
    private ExampleRepository exampleRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<Boolean>> update(@PathVariable("id") UUID id) {

        Optional<Example> foundExample = this.exampleRepository.findById(id);
        foundExample.get().setTitle("ALLAHHHH");
        this.exampleRepository.save(foundExample.get());

        return new ResponseEntity<>(new BaseResponse<>(true, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
