package com.sideproject.gestao_vendas_nfe.domain.product;

public record ProductResponseDTO(
        String nome,
        String codigoInterno,
        int quantidade,
        Double precoUnitario,
        Double valor
){
    public ProductResponseDTO(Product product){
        this(product.getNome(), product.getCodigoInterno(), product.getQuantidade(), product.getPrecoUnitario(), product.getValor());
    }
}
