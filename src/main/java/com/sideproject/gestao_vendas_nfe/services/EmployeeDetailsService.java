package com.sideproject.gestao_vendas_nfe.services;

import com.sideproject.gestao_vendas_nfe.domain.employee.Employee;
import com.sideproject.gestao_vendas_nfe.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

    // Implementa UserDetailsService para corresponder à busca pelos users no BD
@Service
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeRepository getEmployeeRepository() {return employeeRepository;}

        @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        return employeeRepository.findByNome(nome);
    }

    public void registerNewUser(Employee employeeRegister){
        this.employeeRepository.save(employeeRegister);
    }
}
