package com.sideproject.gestao_vendas_nfe.repositories;

import com.sideproject.gestao_vendas_nfe.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
