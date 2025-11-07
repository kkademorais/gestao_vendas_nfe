package com.sideproject.gestao_vendas_nfe.domain.employee;

public record EmployeeRequestRegisterDTO(
   String login,
   String senha,
   String email,
   EmployeeRole role
) {}
