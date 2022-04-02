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

import com.kaliente.pos.application.models.base.BaseResponse;
import com.kaliente.pos.application.models.dtos.product.GetAllProductsResponseDto;
import com.kaliente.pos.application.models.dtos.product.GetProductByIdResponseDto;
import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductAddResponseDto;
import com.kaliente.pos.application.models.dtos.product.ProductDeleteResponseDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateResponseDto;
import com.kaliente.pos.application.services.ProductService;
import com.kaliente.pos.sharedkernel.Constants;

@RestController @RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@GetMapping("/getById/{id}")
	public ResponseEntity<BaseResponse<GetProductByIdResponseDto>> getById(@PathVariable("id") UUID id) {
		
		ProductDetailsDto product = this.productService.getProductById(id);
		var response = new GetProductByIdResponseDto(product);
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@GetMapping("/getAll")
	public ResponseEntity<BaseResponse<GetAllProductsResponseDto>> getAll() {
		
		List<ProductDetailsDto> products = this.productService.getAll();
		var response = new GetAllProductsResponseDto(products);

		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<ProductAddResponseDto>> addNewProduct(@RequestBody ProductAddRequestDto dto) {
		
		var result = productService.createNewProduct(dto);
		var response = new ProductAddResponseDto(result);
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@PutMapping("/update")
	public ResponseEntity<BaseResponse<ProductUpdateResponseDto>> updateProduct(@RequestBody ProductUpdateRequestDto dto) {
		
		var result = this.productService.updateProduct(dto);
		var response = new ProductUpdateResponseDto(result);
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<ProductDeleteResponseDto>> deleteProduct(@PathVariable("id") UUID id) {
		
		var result = this.productService.deleteProduct(id);
		var response = new ProductDeleteResponseDto(result);
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}
	
}