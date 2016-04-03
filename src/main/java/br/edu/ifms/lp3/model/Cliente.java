package br.edu.ifms.lp3.model;

import javax.persistence.*;

/**
 * Classe Modelo Cliente
 */
@Entity
@NamedQueries(value = {
        @NamedQuery(name = Cliente.RETRIEVE_ALL, query = "SELECT nome, cpf, endereco, sexo, telefone FROM Cliente ORDER BY nome"),
        @NamedQuery(name = Cliente.RETRIEVE_BY_NAME, query = "SELECT nome, cpf, endereco, sexo, telefone FROM Cliente WHERE LOWER(nome) LIKE LOWER(:nameInFilter) ORDER BY nome")})
public class Cliente {

    public static final String RETRIEVE_ALL = "retrieveAll";
    public static final String RETRIEVE_BY_NAME = "retrieveByName";

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
