package com.sideproject.gestao_vendas_nfe.controllers;

import com.sideproject.gestao_vendas_nfe.domain.product.ProductRequestDTO;
import com.sideproject.gestao_vendas_nfe.domain.product.ProductResponseDTO;
import com.sideproject.gestao_vendas_nfe.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProductsList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("nome"));
        return ResponseEntity.ok(this.productService.getProductsList(pageable));
    }


    @GetMapping("/{codigo_interno}")
    public ResponseEntity<ProductResponseDTO> getProductByInternalCode(@PathVariable(name = "codigo_interno") String codigoInterno) throws Exception{
        return ResponseEntity.ok(this.productService.getProductByInternalCode(codigoInterno));
    }


    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        this.productService.addProduct(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{codigo_interno}")
    public ResponseEntity updateProductByInternalCode(@PathVariable(name = "codigo_interno") String codigoInterno, @RequestBody ProductRequestDTO productRequestDTO) throws Exception{
        this.productService.updateProductByInternalCode(codigoInterno, productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{codigo_interno}")
    public ResponseEntity deleteProductByInternalCode(@PathVariable(name = "codigo_interno") String codigoInterno) throws Exception{
        this.productService.deleteProductByInternalCode(codigoInterno);
        return ResponseEntity.ok().build();
    }



}
