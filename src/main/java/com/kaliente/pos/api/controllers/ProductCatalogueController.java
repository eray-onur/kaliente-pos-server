package com.kaliente.pos.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaliente.pos.application.models.dtos.productcatalogue.GetAllProductCataloguesResponseDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.GetProductCatalogueByIdResponseDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueAddRequestDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueAddResponseDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueUpdateRequestDto;
import com.kaliente.pos.application.models.dtos.productcatalogue.ProductCatalogueUpdateResponseDto;
import com.kaliente.pos.application.services.ProductCatalogueService;

@RestController @RequestMapping("/product_catalogue")
public class ProductCatalogueController {

	@Autowired
	ProductCatalogueService productCatalogueService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@GetMapping("/getById/{id}")
	public ResponseEntity<GetProductCatalogueByIdResponseDto> getById(@PathVariable("id") UUID id) {
		
		ProductCatalogueDetailsDto productCatalogue = this.productCatalogueService.getProductCatalogueById(id);
		var response = new GetProductCatalogueByIdResponseDto(productCatalogue);
		
		return new ResponseEntity<GetProductCatalogueByIdResponseDto>(response, HttpStatus.OK);
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@GetMapping("/getAll")
	public ResponseEntity<GetAllProductCataloguesResponseDto> getAll() {
		
		List<ProductCatalogueDetailsDto> productCatalogues = this.productCatalogueService.getAllCatalogues();
		var response = new GetAllProductCataloguesResponseDto(productCatalogues);
		
		return new ResponseEntity<GetAllProductCataloguesResponseDto>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@PostMapping("/add")
	public ResponseEntity<ProductCatalogueAddResponseDto> addNewCatalogue(@RequestBody ProductCatalogueAddRequestDto dto) {
		
		var result = productCatalogueService.createNewProductCatalogue(dto);
		var response = new ProductCatalogueAddResponseDto(result);
		
		return new ResponseEntity<ProductCatalogueAddResponseDto>(response, HttpStatus.CREATED);
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@PutMapping("/update")
	public ResponseEntity<ProductCatalogueUpdateResponseDto> updateCatalogue(@RequestBody ProductCatalogueUpdateRequestDto dto) {
		
		var result = this.productCatalogueService.updateProductCatalogue(dto);
		var response = new ProductCatalogueUpdateResponseDto(result);
		
		return new ResponseEntity<ProductCatalogueUpdateResponseDto>(response, HttpStatus.CREATED);
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCatalogue(@PathVariable("id") UUID id) {
		this.productCatalogueService.deleteProductCatalogue(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
