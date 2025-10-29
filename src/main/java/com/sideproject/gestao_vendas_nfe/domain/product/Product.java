package com.sideproject.gestao_vendas_nfe.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "products")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;  // UUID
    @NotBlank   // Usado para String
    private String nome;
    @NotBlank
    private String codigoInterno;
    @NotNull    // Usado para numéricos
    private int quantidade; // Será que por conta de ser int e não Integer o Hibernate mapeou automaticamente como NotNull?
    @NotNull
    private Double precoUnitario;
    private Double valor;

    public Product(String nome, String codigoInterno, int quantidade, Double precoUnitario, Double valor){
        this.nome = nome;
        this.codigoInterno = codigoInterno;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valor = valor;
    }
    public Product(){}
    public Product(ProductRequestDTO productRequestDTO){
        this.nome = productRequestDTO.nome();
        this.codigoInterno = productRequestDTO.codigoInterno();
        this.quantidade = productRequestDTO.quantidade();;
        this.precoUnitario = productRequestDTO.precoUnitario();
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCodigoInterno() {return codigoInterno;}
    public void setCodigoInterno(String codigoInterno) {this.codigoInterno = codigoInterno;}

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public Double getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(Double precoUnitario) {this.precoUnitario = precoUnitario;}

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", codigoInterno='" + codigoInterno + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", valor=" + valor +
                '}';
    }
}
