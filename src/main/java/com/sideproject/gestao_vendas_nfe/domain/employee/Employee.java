package com.sideproject.gestao_vendas_nfe.domain.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "employees")
@Entity(name = "employees")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    private EmployeeRole role;
    @NotBlank
    private String senha;

    public Employee(String email, String nome, EmployeeRole role, String senha){
        this.email = email;
        this.nome = nome;
        this.role = role;
        this.senha = senha;
    }
    public Employee(){}


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == EmployeeRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_VENDEDOR"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha; // Inserir password hasheada
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); // True
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked(); //True
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired(); // True
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled(); // True
    }
}
