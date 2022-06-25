package com.kaliente.pos.application.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.requests.productcatalogue.ProductCatalogueAddRequestDto;
import com.kaliente.pos.application.models.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.requests.productcatalogue.ProductCatalogueUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;
import com.kaliente.pos.infrastructure.persistence.ProductCatalogueHibernateRepository;

@Service
public class ProductCatalogueService {


    @Autowired
    private ProductCatalogueHibernateRepository catalogueJpaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ProductCatalogueDetailsDto getProductCatalogueById(UUID catalogueId) {
        ProductCatalogue productCatalogue = this.catalogueJpaRepository.getProductCatalogueById(catalogueId);
        if (productCatalogue == null) {
            return null;
        }
        return modelMapper.map(productCatalogue, ProductCatalogueDetailsDto.class);
    }

    public List<ProductCatalogueDetailsDto> getAllCatalogues() {
        // List<ProductCatalogue> productCatalogues = this.catalogueRepository.findAll();

        Collection<ProductCatalogue> productCatalogues = this.catalogueJpaRepository.getProductCatalogues();
        return productCatalogues.stream().map(p -> modelMapper.map(p, ProductCatalogueDetailsDto.class)).toList();
    }

    public ProductCatalogue createNewProductCatalogue(ProductCatalogueAddRequestDto dto) {
        var createdProductCatalogue = this.catalogueJpaRepository.addProductCatalogue(dto.getTitle(), dto.getDescription(), dto.getParentCatalogueId());
        return createdProductCatalogue;
    }

    public ProductCatalogue updateProductCatalogue(ProductCatalogueUpdateRequestDto dto) {
        var createdProductCatalogue = this.catalogueJpaRepository.updateProductCatalogue(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getParentCatalogueId()
        );

        return createdProductCatalogue;
    }

    public UUID deleteProductCatalogue(UUID catalogueId) {
        var result = this.catalogueJpaRepository.archiveProductCatalogue(catalogueId);
        if (result)
            return catalogueId;

        return null;
    }


}
