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

    public ProductResponseDTO getProductByInternalCode(String codigoInterno) throws Exception{
        try{
            return productRepository
                    .findAll()
                    .stream()
                    .filter(product -> product.getCodigoInterno().equalsIgnoreCase(codigoInterno))
                    .map(product -> new ProductResponseDTO(product))
                    .findFirst()
                    .get();
        }
        catch(Exception e){
            throw new Exception("Produto não cadastrado", e);
        }
    }


        // Post
    public void addProduct(ProductRequestDTO productRequestDTO){
        Product productAdd = new Product(productRequestDTO);
        productAdd.setValor(productAdd.getPrecoUnitario() * productAdd.getQuantidade());
        this.productRepository.save(productAdd);
    }

        // Put
    public void updateProductByInternalCode(String codigoInterno, ProductRequestDTO productRequestDTO) throws Exception{
        try{
            Product productUpdate = new Product(productRequestDTO);
            productUpdate.setId(
                    this.productRepository
                            .findAll()
                            .stream()
                            .filter(product -> product.getCodigoInterno().equalsIgnoreCase(codigoInterno))
                            .findFirst()
                            .get()
                            .getId()
            );
            productUpdate.setValor(productRequestDTO.precoUnitario() * productRequestDTO.quantidade());
            this.productRepository.save(productUpdate);
        }
        catch (Exception e){
            throw new Exception("Produto não encontrado", e);
        }
    }

    public void deleteProductByInternalCode(String codigoInterno){
        Product productDelete = this.productRepository
                                        .findAll()
                                        .stream()
                                        .filter(product -> product.getCodigoInterno().equalsIgnoreCase(codigoInterno))
                                        .findFirst()
                                        .get();
        this.productRepository.delete(productDelete);
    }


}
