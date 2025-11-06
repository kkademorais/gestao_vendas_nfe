package com.sideproject.gestao_vendas_nfe.controllers;

import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestLoginDTO;
import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestRegisterDTO;
import com.sideproject.gestao_vendas_nfe.services.EmployeeDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    
    private final EmployeeDetailsService employeeDetailsService;

    public AuthenticationController(EmployeeDetailsService employeeDetailsService){this.employeeDetailsService = employeeDetailsService;}

    @PostMapping(path = "/login")
    public ResponseEntity login(EmployeeRequestLoginDTO loginDTO){
        this.employeeDetailsService.loadUserByUsername(loginDTO.login());
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(EmployeeRequestRegisterDTO registerDTO){
        this.employeeDetailsService.register(registerDTO);
        return ResponseEntity.ok().build();
    }


}
