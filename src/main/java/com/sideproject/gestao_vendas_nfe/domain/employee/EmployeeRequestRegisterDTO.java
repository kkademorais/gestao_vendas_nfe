package com.sideproject.gestao_vendas_nfe.domain.employee;

public record EmployeeRequestRegisterDTO(
        String email,
        String nome,
        EmployeeRole role,
        String senha
) {}
