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

import com.kaliente.pos.application.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.services.ProductService;

@RestController @RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ProductDetailsDto> getById(@PathVariable("id") UUID id) {
		ProductDetailsDto product = this.productService.getProductById(id);
		if(product != null) {
			return new ResponseEntity<ProductDetailsDto>(product, HttpStatus.OK);
		}
		return new ResponseEntity<ProductDetailsDto>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ProductDetailsDto>> getAll() {
		List<ProductDetailsDto> products = this.productService.getAll();
		return new ResponseEntity<List<ProductDetailsDto>>(products, HttpStatus.OK);
	}
	
	@PostMapping("/addNewProduct")
	public ResponseEntity<?> addNewProduct(@RequestBody ProductDetailsDto dto) {
		productService.createNewProduct(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDetailsDto dto) {
		this.productService.updateProduct(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") UUID id) {
		this.productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}