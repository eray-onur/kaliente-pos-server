package com.kaliente.pos.application.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kaliente.pos.domain.productaggregate.ProductCatalogueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaliente.pos.application.requests.productcatalogue.ProductCatalogueAddRequestDto;
import com.kaliente.pos.application.models.productcatalogue.ProductCatalogueDetailsDto;
import com.kaliente.pos.application.requests.productcatalogue.ProductCatalogueUpdateRequestDto;
import com.kaliente.pos.domain.productaggregate.ProductCatalogue;

@Service
public class ProductCatalogueService {

    private final ProductCatalogueRepository productCatalogueRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductCatalogueService(ProductCatalogueRepository productCatalogueRepository, ModelMapper modelMapper) {
        this.productCatalogueRepository = productCatalogueRepository;
        this.modelMapper = modelMapper;
    }


    public ProductCatalogueDetailsDto getProductCatalogueById(UUID catalogueId) {
        ProductCatalogue productCatalogue = this.productCatalogueRepository.getProductCatalogueById(catalogueId);
        if (productCatalogue == null) {
            return null;
        }
        return modelMapper.map(productCatalogue, ProductCatalogueDetailsDto.class);
    }

    public List<ProductCatalogueDetailsDto> getAllCatalogues() {
        Collection<ProductCatalogue> productCatalogues = this.productCatalogueRepository.findAll();
        return productCatalogues.stream().map(p -> modelMapper.map(p, ProductCatalogueDetailsDto.class)).toList();
    }

    public ProductCatalogue createNewProductCatalogue(ProductCatalogueAddRequestDto dto) {

        Optional<ProductCatalogue> parentCatalogue = (dto.getParentCatalogueId() != null)
                ? productCatalogueRepository.findById(dto.getParentCatalogueId())
                : Optional.empty();

        var productCatalogue = ProductCatalogue.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .parentCatalogue((parentCatalogue.isEmpty()) ? null : parentCatalogue.get())
                .build();

        return this.productCatalogueRepository.save(productCatalogue);
    }

    public ProductCatalogue updateProductCatalogue(ProductCatalogueUpdateRequestDto dto) {
        var productCatalogue = productCatalogueRepository.findById(dto.getId());

        if(productCatalogue.isEmpty())
            return null;

        return this.productCatalogueRepository.save(
                productCatalogue.get()
        );
    }

    public UUID deleteProductCatalogue(UUID catalogueId) {
        this.productCatalogueRepository.deleteById(catalogueId);
        return catalogueId;
    }


}
