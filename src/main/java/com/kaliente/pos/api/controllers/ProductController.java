package com.kaliente.pos.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kaliente.pos.domain.productaggregate.Product;

@RestController @RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getById/{id}")
	public ProductDetailsDto getById(@PathVariable("id") UUID id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping("/getAll")
	public List<ProductDetailsDto> getAll() {
		return this.productService.getAll();
	}
	
	@PostMapping("/addNewProduct")
	public int addNewProduct(@RequestBody ProductDetailsDto dto) {
		productService.createNewProduct(dto);
		return 1;
	}
	
	@PutMapping("/updateProduct")
	public int updateProduct(@RequestBody ProductDetailsDto dto) {
		this.productService.updateProduct(dto);
		return 1;
	}
	
	@DeleteMapping("/delete/{id}")
	public int deleteProduct(@PathVariable("id") UUID id) {
		return this.productService.deleteProduct(id);
	}
	
}