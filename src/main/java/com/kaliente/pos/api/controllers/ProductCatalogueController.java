package com.kaliente.pos.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaliente.pos.application.dtos.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.services.ProductCatalogueService;

@RestController @RequestMapping("/product_catalogue")
public class ProductCatalogueController {

	@Autowired
	ProductCatalogueService productCatalogueService;
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ProductCatalogueDetailsDto> getById(@PathVariable("id") UUID id) {
		ProductCatalogueDetailsDto productCatalogue = this.productCatalogueService.getProductCatalogueById(id);
		if(productCatalogue != null) {
			return new ResponseEntity<ProductCatalogueDetailsDto>(productCatalogue, HttpStatus.OK);
		}
		return new ResponseEntity<ProductCatalogueDetailsDto>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ProductCatalogueDetailsDto>> getAll() {
		List<ProductCatalogueDetailsDto> productCatalogues = this.productCatalogueService.getAllCatalogues();
		return new ResponseEntity<List<ProductCatalogueDetailsDto>>(productCatalogues, HttpStatus.OK);
	}
	
	@PostMapping("/addNewProduct")
	public ResponseEntity<?> addNewProduct(@RequestBody ProductCatalogueDetailsDto dto) {
		productCatalogueService.createNewProductCatalogue(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductCatalogueDetailsDto dto) {
		this.productCatalogueService.updateProductCatalogue(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") UUID id) {
		this.productCatalogueService.deleteProductCatalogue(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
