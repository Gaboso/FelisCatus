package com.github.gaboso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(value = {
    @NamedQuery(name = User.FIND_ALL, query = "SELECT name, cpf, address, sex, phone FROM User ORDER BY name"),
    @NamedQuery(name = User.FIND_BY_NAME,
        query = "SELECT name, cpf, address, sex, phone FROM User WHERE LOWER(name) LIKE LOWER(:nameInFilter) ORDER BY name")})
@Table(name = "TB_USER", schema = "felis")
public class User {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_NAME = "findByName";

    @Id
    @Column(length = 14, nullable = false)
    private String cpf;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 16)
    private String phone;

    @Column(length = 150)
    private String address;

    @Column
    private char sex;

    public User() {
    }

    public User(String cpf, String name, String phone, String address, char sex) {
        this.cpf = cpf;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sexo) {
        this.sex = sexo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String telefone) {
        this.phone = telefone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String endereco) {
        this.address = endereco;
    }

}