package com.sideproject.gestao_vendas_nfe.controllers;

import com.sideproject.gestao_vendas_nfe.domain.employee.Employee;
import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestLoginDTO;
import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestRegisterDTO;
import com.sideproject.gestao_vendas_nfe.infra.SecurityConfigurations;
import com.sideproject.gestao_vendas_nfe.services.EmployeeDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final EmployeeDetailsService employeeDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(EmployeeDetailsService employeeDetailsService, AuthenticationManager authenticationManager){
        this.employeeDetailsService = employeeDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid EmployeeRequestLoginDTO loginDTO){
        var usernameAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());    // Cria token para login
        var auth = authenticationManager.authenticate(usernameAuthToken);   // Autentica usuário no BD
        this.employeeDetailsService.loadUserByUsername(loginDTO.login());
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeRequestRegisterDTO registerDTO){
        if(this.employeeDetailsService.loadUserByUsername(registerDTO.login()).isEnabled()){
            EmployeeRequestLoginDTO loginDTO = new EmployeeRequestLoginDTO(registerDTO.login(), registerDTO.senha());
            login(loginDTO);
        }
        else{   // Caso usuário não exista no BD
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncoded = passwordEncoder.encode(registerDTO.senha());
            Employee employeeRegister = new Employee(registerDTO.login(), passwordEncoded, registerDTO.email(), registerDTO.role());
            this.employeeDetailsService.registerNewUser(employeeRegister);
        }
        return ResponseEntity.ok().build();
    }


}
