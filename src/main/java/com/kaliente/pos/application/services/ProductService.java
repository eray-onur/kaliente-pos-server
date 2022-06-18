package com.kaliente.pos.application.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


import com.kaliente.pos.api.configs.AssetsFolderConfig;
import com.kaliente.pos.domain.currency.CurrencyHistory;
import com.kaliente.pos.domain.productaggregate.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.infrastructure.persistence.ProductHibernateRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	AssetsFolderConfig assetsFolderConfig;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCatalogueRepository productCatalogueRepository;
	@Autowired
	private CurrencyHistoryService currencyHistoryService;

    @Autowired
    private ModelMapper modelMapper;
	
	
	public ProductDetailsDto getProductById(UUID productId) {
		Optional<Product> product =  productRepository.findById(productId);
		if(product.isEmpty()) {
			return null;
		}
		return modelMapper.map(product.get(), ProductDetailsDto.class);
	}
	
	public List<ProductDetailsDto> getAll() {
		var foundProducts = this.productRepository.findAll();

		ArrayList<Product> products = new ArrayList<>();
		foundProducts.forEach(products::add);

		return products.stream().map(p -> modelMapper.map(p, ProductDetailsDto.class)).toList();
	}
	
	public Product createNewProduct(ProductAddRequestDto dto) {

		 Product newProduct = modelMapper.map(dto, Product.class);
		 newProduct.setCatalogue(newProduct.getCatalogue());

		var latestHistory = currencyHistoryService.getLatestRateByTitle(dto.getCurrencyTitle());

		if(latestHistory != null) {
			ProductCurrency productCurrency = new ProductCurrency();
			productCurrency.setCurrencyTitle(latestHistory.getCurrencyTitle());
			productCurrency.setBaseCrossRate(latestHistory.getBaseCrossRate());
			productCurrency.setCurrencyRate(latestHistory.getCurrencyRate());
			productCurrency.setCurrencyDate(latestHistory.getCurrencyDate());
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
				ProductCurrency productCurrency = new ProductCurrency();
				productCurrency.setCurrencyTitle(latestHistory.getCurrencyTitle());
				productCurrency.setBaseCrossRate(latestHistory.getBaseCrossRate());
				productCurrency.setCurrencyRate(latestHistory.getCurrencyRate());
				productCurrency.setCurrencyDate(latestHistory.getCurrencyDate());
				productToUpdate.get().setCurrency(productCurrency);
			}

			productRepository.save(productToUpdate.get());

			return productToUpdate.get();

	}
	
	public UUID deleteProduct(UUID productId) {
		productRepository.deleteById(productId);
		return productId;
	}

	public boolean uploadImage(MultipartFile productImage, UUID productId) {
		Optional<Product> productToUpdate = productRepository.findById(productId);

		try(InputStream inputStream = productImage.getInputStream()) {
			String assetsPath = System.getProperty("user.dir");
			Path uploadPath = Paths.get(assetsPath, assetsFolderConfig.getProductImagesPath());
			String[] fileExtension = productImage.getOriginalFilename().split("\\.");

			var storedImagePathname = String.valueOf(productId) + "." + fileExtension[fileExtension.length - 1];

			var loader = Files.copy(inputStream, uploadPath.resolve(storedImagePathname), StandardCopyOption.REPLACE_EXISTING);


			productToUpdate.get().setImagePath(storedImagePathname);
			productRepository.save(productToUpdate.get());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
}
