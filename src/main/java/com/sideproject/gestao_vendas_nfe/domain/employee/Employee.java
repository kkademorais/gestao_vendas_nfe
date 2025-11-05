package com.sideproject.gestao_vendas_nfe.domain.employee;

/*
- id: Long
- nome: String (obrigatório)
- email: String (obrigatório, único)
- senha: String (obrigatório) — armazenar de forma segura (hash)
- perfil: Enum (ex: ADMIN, VENDEDOR)
*/

/*
**Regras/validações**:

        - email deve ter formato válido, não duplicado
- senha com política mínima (ex: tamanho mínimo)
- perfil controlado por Enum
- rotas protegidas por perfil usando Spring Security + JWT
*/

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Table(name = "employees")
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotNull
    private EmployeeRole role;

    public Employee(String nome, String email, String senha, EmployeeRole role){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }
    public Employee(){}

    public Employee(EmployeeRequestDTO employeeRequestDTO){

    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public @NotBlank String getNome() {return nome;}
    public void setNome(@NotBlank String nome) {this.nome = nome;}

    public @NotBlank String getEmail() {return email;}
    public void setEmail(@NotBlank String email) {this.email = email;}

    public @NotBlank String getSenha() {return senha;}
    public void setSenha(@NotBlank String senha) {this.senha = senha;}

    public @NotNull EmployeeRole getRole() {return role;}
    public void setRole(@NotNull EmployeeRole role) {this.role = role;}

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", role=" + role +
                '}';
    }
}
