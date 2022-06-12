package com.kaliente.pos.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import com.kaliente.pos.api.configs.AssetsFolderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@RestController @RequestMapping("product")
public class ProductController {
	@Autowired
	ServletContext context;

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@GetMapping("/getById/{id}")
	public ResponseEntity<BaseResponse<GetProductByIdResponseDto>> getById(@PathVariable("id") UUID id) {
		
		ProductDetailsDto foundProduct = this.productService.getProductById(id);
		var response = new GetProductByIdResponseDto(foundProduct);
		
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
		var response = new ProductAddResponseDto(result.getId(), result.getTitle());
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@PutMapping("/update")
	public ResponseEntity<BaseResponse<ProductUpdateResponseDto>> updateProduct(@RequestBody ProductUpdateRequestDto dto) {
		
		var result = this.productService.updateProduct(dto);
		var response = new ProductUpdateResponseDto(result.getId(), result.getTitle());
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PERSONNEL')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<ProductDeleteResponseDto>> deleteProduct(@PathVariable("id") UUID id) {
		
		var result = this.productService.deleteProduct(id);
		var response = new ProductDeleteResponseDto(result);
		
		return new ResponseEntity<>(new BaseResponse<>(response, Constants.OPERATION_SUCCESS_MESSAGE), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_PERSONNEL')")
	@PostMapping("/upload-image/{productId}")
	public ResponseEntity<BaseResponse<Boolean>> uploadProductImage(@PathVariable("productId") UUID id, @RequestParam("image") MultipartFile image, ModelMap modelMap) {
		modelMap.addAttribute("image", image);
		productService.uploadImage(image, id);
		var response = new BaseResponse<>(true, Constants.OPERATION_SUCCESS_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}