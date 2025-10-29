package com.sideproject.gestao_vendas_nfe.domain.product;


public record ProductRequestDTO(
        String nome,
        String codigoInterno,
        int quantidade,
        Double precoUnitario
) {}
