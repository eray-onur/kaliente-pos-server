package com.kaliente.pos.api.controllers;

import java.util.UUID;

import com.kaliente.pos.application.models.base.BaseResponse;
import com.kaliente.pos.application.responses.administration.RemovePersonnelResponse;
import com.kaliente.pos.application.services.AdministrationService;
import com.kaliente.pos.sharedkernel.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administration")
public class AdministrationController {
    

    @Autowired
    private AdministrationService adminService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/removePersonnel")
    public ResponseEntity<BaseResponse<RemovePersonnelResponse>> removePersonnel(@RequestParam String personnelEmail) throws Exception {
        UUID serviceResult = adminService.removePersonnel(personnelEmail);
        var response = new RemovePersonnelResponse(serviceResult);
        return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);

    }

}
