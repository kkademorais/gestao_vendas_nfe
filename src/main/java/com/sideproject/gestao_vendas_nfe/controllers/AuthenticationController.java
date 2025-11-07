package com.sideproject.gestao_vendas_nfe.controllers;

import com.sideproject.gestao_vendas_nfe.domain.employee.Employee;
import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestLoginDTO;
import com.sideproject.gestao_vendas_nfe.domain.employee.EmployeeRequestRegisterDTO;
import com.sideproject.gestao_vendas_nfe.services.EmployeeDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        var usernameAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.nome(), loginDTO.senha());    // Cria token para login
        var auth = authenticationManager.authenticate(usernameAuthToken);   // Autentica usuário no BD
        this.employeeDetailsService.loadUserByUsername(loginDTO.nome());
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeRequestRegisterDTO registerDTO){
        /*
        try{
            this.employeeDetailsService.loadUserByUsername(registerDTO.nome());
            System.out.println("Caiu aqui, try não retornou exception");
            //return ResponseEntity.badRequest().build();
        }
        catch(UsernameNotFoundException e){
            System.out.println("Caiu aqui: deu certo o register");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncoded = passwordEncoder.encode(registerDTO.senha());
            Employee employeeRegister = new Employee(registerDTO.nome(), passwordEncoded, registerDTO.email(), registerDTO.role());
            this.employeeDetailsService.registerNewUser(employeeRegister);
            return ResponseEntity.ok().build();
        }
        System.out.println("Vou retornar 400");
        return ResponseEntity.badRequest().build();
        */

        if(this.employeeDetailsService.getEmployeeRepository().findByNome(registerDTO.nome()) != null){
            return ResponseEntity.badRequest().build();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncoded = passwordEncoder.encode(registerDTO.senha());
        Employee employeeRegister = new Employee(registerDTO.email(), registerDTO.nome(), registerDTO.role(), passwordEncoded);
        this.employeeDetailsService.registerNewUser(employeeRegister);
        return ResponseEntity.ok().build();

    }


}
