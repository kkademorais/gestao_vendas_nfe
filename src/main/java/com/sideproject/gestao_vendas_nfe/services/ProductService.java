package com.sideproject.gestao_vendas_nfe.services;

import com.sideproject.gestao_vendas_nfe.domain.product.Product;
import com.sideproject.gestao_vendas_nfe.domain.product.ProductRequestDTO;
import com.sideproject.gestao_vendas_nfe.domain.product.ProductResponseDTO;
import com.sideproject.gestao_vendas_nfe.repositories.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){this.productRepository = productRepository;}

        // Get
    public List<ProductResponseDTO> getProductsList(Pageable pageable){
        return productRepository
                .findAll(pageable)
                .stream()
                .map(product -> new ProductResponseDTO(product))
                .toList();
    }

        // Post
    public void addProduct(ProductRequestDTO productRequestDTO){
        Product productAdd = new Product(productRequestDTO);
        productAdd.setValor(productAdd.getPrecoUnitario() * productAdd.getQuantidade());
        this.productRepository.save(productAdd);
    }

}
