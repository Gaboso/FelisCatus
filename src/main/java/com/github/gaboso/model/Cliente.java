package com.github.gaboso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = Cliente.FIND_ALL, query = "SELECT nome, cpf, endereco, sexo, telefone FROM Cliente ORDER BY nome"),
        @NamedQuery(name = Cliente.FIND_BY_NAME, query = "SELECT nome, cpf, endereco, sexo, telefone FROM Cliente WHERE LOWER(nome) LIKE LOWER(:nameInFilter) ORDER BY nome")})
public class Cliente {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_NAME = "findByName";

    @Id
    @Column(length = 14, nullable = false)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14)
    private String telefone;

    @Column(length = 100)
    private String endereco;

    @Column
    private char sexo;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone, String endereco, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}