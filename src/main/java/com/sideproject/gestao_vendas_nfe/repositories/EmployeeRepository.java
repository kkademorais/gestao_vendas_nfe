package com.sideproject.gestao_vendas_nfe.repositories;

import com.sideproject.gestao_vendas_nfe.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Repository;

// <T1,T2>
        // T1 = Entidade mapeada
        // T2 = TipoPrimitivo do ID
//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    UserDetails findByLogin(String login);
}
