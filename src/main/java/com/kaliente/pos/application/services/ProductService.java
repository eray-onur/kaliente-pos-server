package com.kaliente.pos.application.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.kaliente.pos.api.configs.AssetsFolderConfig;
import com.kaliente.pos.domain.productaggregate.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.models.dtos.product.ProductAddRequestDto;
import com.kaliente.pos.application.models.dtos.product.ProductDetailsDto;
import com.kaliente.pos.application.models.dtos.product.ProductUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.infrastructure.persistence.ProductHibernateRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	AssetsFolderConfig assetsFolderConfig;
	@Autowired
	private ProductHibernateRepository productHibernateRepository;
	@Autowired
	private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
	
	
	public ProductDetailsDto getProductById(UUID productId) {
		Product product =  this.productHibernateRepository.getProductById(productId);
		if(product == null) {
			return null;
		}
		return modelMapper.map(product, ProductDetailsDto.class);
	}
	
	public List<ProductDetailsDto> getAll() {
		Collection<Product> products = this.productHibernateRepository.getProducts();
		return products.stream().map(p -> modelMapper.map(p, ProductDetailsDto.class)).toList();
	}
	
	public Product createNewProduct(ProductAddRequestDto dto) {
		// Product newProduct = modelMapper.map(dto, Product.class);
		// newProduct.setCatalogue(newProduct.getCatalogue());

		var result = this.productHibernateRepository.addProduct(dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getCatalogueId());
		
		// var result = this.productRepository.save(newProduct);
		return result;
	}
	
	public Product updateProduct(ProductUpdateRequestDto dto) {
		var updatedProduct = this.productHibernateRepository.updateProduct(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getCatalogueId());
		return updatedProduct;
	}
	
	public UUID deleteProduct(UUID productId) {
		this.productHibernateRepository.archiveProduct(productId);
		return productId;
	}

	public boolean uploadImage(MultipartFile productImage, UUID productId) {
//		Product productToUpdate = productRepository.getById(productId);
		Optional<Product> productToUpdate = productRepository.findById(productId);

		try(InputStream inputStream = productImage.getInputStream()) {
			String assetsPath = System.getProperty("user.dir");
			Path uploadPath = Paths.get(assetsPath, assetsFolderConfig.getProductImagesPath());
			String[] fileExtension = productImage.getOriginalFilename().split("\\.");

			var storedImagePathname = String.valueOf(productId) + "." + fileExtension[fileExtension.length - 1];

			var loader =

					Files.copy(inputStream, uploadPath.resolve(storedImagePathname), StandardCopyOption.REPLACE_EXISTING);


//			productRepository.setImagePathById(storedImagePathname, productToUpdate.getId());

			productToUpdate.get().setImagePath(storedImagePathname);
			productRepository.save(productToUpdate.get());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
}
