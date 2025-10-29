package com.sideproject.gestao_vendas_nfe.controllers;

import com.sideproject.gestao_vendas_nfe.domain.product.ProductRequestDTO;
import com.sideproject.gestao_vendas_nfe.domain.product.ProductResponseDTO;
import com.sideproject.gestao_vendas_nfe.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProductsList(){return ResponseEntity.ok(this.productService.getProductsList());}

    @PostMapping
    public ResponseEntity addProduct(ProductRequestDTO productRequestDTO){
        this.productService.addProduct(productRequestDTO);
        return ResponseEntity.ok().build();
    }

}
