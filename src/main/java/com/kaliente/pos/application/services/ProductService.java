package com.kaliente.pos.application.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


import com.kaliente.pos.application.configs.AppConfig;
import com.kaliente.pos.domain.productaggregate.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.requests.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.product.ProductDetailsDto;
import com.kaliente.pos.application.requests.product.ProductUpdateRequestDto;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

	private final AppConfig appConfig;
	private final ProductRepository productRepository;
	private final ProductCatalogueRepository productCatalogueRepository;
	private final CurrencyHistoryService currencyHistoryService;

    private final ModelMapper modelMapper;

	@Autowired
	public ProductService(
			ProductRepository productRepository,
			ProductCatalogueRepository productCatalogueRepository,
			CurrencyHistoryService currencyHistoryService,
			ModelMapper modelMapper, AppConfig appConfig) {
		this.appConfig = appConfig;
		this.productRepository = productRepository;
		this.productCatalogueRepository = productCatalogueRepository;
		this.currencyHistoryService = currencyHistoryService;
		this.modelMapper = modelMapper;
	}
	
	public ProductDetailsDto getProductById(UUID productId) {
		Optional<Product> product =  productRepository.findById(productId);
		if(product.isEmpty()) {
			return null;
		}
		return modelMapper.map(product.get(), ProductDetailsDto.class);
	}
	
	public List<ProductDetailsDto> getAll() {
		var foundProducts = this.productRepository.findAll();

		return foundProducts.stream().map(p -> modelMapper.map(p, ProductDetailsDto.class)).toList();
	}
	
	public Product createNewProduct(ProductAddRequestDto dto) {

		 Product newProduct = modelMapper.map(dto, Product.class);
		 newProduct.setCatalogue(newProduct.getCatalogue());

		var latestHistory = currencyHistoryService.getLatestRateByTitle(dto.getCurrencyTitle());

		if(latestHistory != null) {
			ProductCurrency productCurrency = modelMapper.map(latestHistory, ProductCurrency.class);

			newProduct.setCurrency(productCurrency);
		}

		return this.productRepository.save(newProduct);
	}

	public Product updateProduct(ProductUpdateRequestDto dto) {
			Optional<Product> productToUpdate = productRepository.findById(dto.getId());

			if(productToUpdate.isEmpty())
				throw new RuntimeException("No products of given name are available.");

			productToUpdate.get().setTitle(dto.getTitle());
			productToUpdate.get().setDescription(dto.getDescription());
			productToUpdate.get().setCost(dto.getCost());
			productToUpdate.get().setPrice(dto.getPrice());
			productToUpdate.get().setStockedUnits(dto.getStockedUnits());

			var latestHistory = currencyHistoryService.getLatestRateByTitle(dto.getCurrencyTitle());

			if(latestHistory != null) {
				ProductCurrency productCurrency = modelMapper.map(latestHistory, ProductCurrency.class);
				productToUpdate.get().setCurrency(productCurrency);
			}

			productRepository.save(productToUpdate.get());

			return productToUpdate.get();

	}
	
	public UUID deleteProduct(UUID productId) {
		var prod = productRepository.findById(productId);
		if(prod.isEmpty())
			return null;

		productRepository.delete(prod.get());
		return productId;
	}

	public void uploadImage(MultipartFile productImage, UUID productId) {
		Optional<Product> productToUpdate = productRepository.findById(productId);

		if(productToUpdate.isEmpty())
			throw new RuntimeException("Failed to find product by given ID.");

		try(InputStream inputStream = productImage.getInputStream()) {
			Path uploadPath = Paths.get(appConfig.getAssets().getAbsoluteRootPath(), appConfig.getAssets().getProductImages());
			String[] fileExtension = Objects.requireNonNull(productImage.getOriginalFilename()).split("\\.");

			var storedImagePathname = String.valueOf(productId) + "." + fileExtension[fileExtension.length - 1];

			Files.copy(inputStream, uploadPath.resolve(storedImagePathname), StandardCopyOption.REPLACE_EXISTING);


			productToUpdate.get().setImagePath(storedImagePathname);
			productRepository.save(productToUpdate.get());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
