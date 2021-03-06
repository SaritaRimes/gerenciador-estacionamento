package com.saritarimes.gerenciadorestacionamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Estabelecimento {
    /* ---------- Propriedades ---------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(nullable = false, length = 50)
    @Size(message = "O campo nome não pode ser deixado em branco.") //verificar se nao eh necessario colocar o min
    @Size(max = 50, message = "O nome é muito longo.")
    @Pattern(regexp = "^[A-Za-z]*", message = "O nome deve conter apenas letras.")
    private String nome;

    @Column(nullable = false, length = 14)
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 numeros.")
    @Pattern(regexp = "^[0-9]*", message = "O CNPJ deve conter apenas números.") //pode ser que nao funcione
    private String cnpj;

    @Column(nullable = false, length = 100)
    @Size(message = "O campo endereço deve ser preenchido.")
    @Size(max = 100, message = "O endereço é muito longo.")
    private String endereco;

    @Column(nullable = false, length = 11) //supondo telefones no Brasil
    @Size(min = 10, message = "O campo telefone não está completo.")
    @Size(max = 11, message = "O telefone é muito longo.")
    @Pattern(regexp = "^[0-9]*", message = "O telefone deve conter apenas números.")
    private String telefone;

    @Column(nullable = false)
    private int quantidadeVagasMotos;

    @Column(nullable = false)
    private int quantidadeVagasCarros;

    @Column(nullable = false)
    private int quantidadeMotosEstacionadas;

    @Column(nullable = false)
    private int quantidadeCarrosEstacionados;


    /* ---------- Construtores ---------- */
    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String cnpj, String endereco, String telefone,
                           int quantidadeVagasMotos, int quantidadeVagasCarros,
                           int quantidadeMotosEstacionadas, int quantidadeCarrosEstacionados) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.quantidadeVagasMotos = quantidadeVagasMotos;
        this.quantidadeVagasCarros = quantidadeVagasCarros;
        this.quantidadeMotosEstacionadas = quantidadeMotosEstacionadas;
        this.quantidadeCarrosEstacionados = quantidadeCarrosEstacionados;
    }


    /* ---------- Metodos acessores ---------- */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getQuantidadeVagasMotos() {
        return quantidadeVagasMotos;
    }

    public void setQuantidadeVagasMotos(int quantidadeVagasMotos) {
        this.quantidadeVagasMotos = quantidadeVagasMotos;
    }

    public int getQuantidadeVagasCarros() {
        return quantidadeVagasCarros;
    }

    public void setQuantidadeVagasCarros(int quantidadeVagasCarros) {
        this.quantidadeVagasCarros = quantidadeVagasCarros;
    }

    public int getQuantidadeMotosEstacionadas() {
        return quantidadeMotosEstacionadas;
    }

    public void setQuantidadeMotosEstacionadas(int quantidadeMotosEstacionadas) {
        this.quantidadeMotosEstacionadas = quantidadeMotosEstacionadas;
    }

    public int getQuantidadeCarrosEstacionados() {
        return quantidadeCarrosEstacionados;
    }

    public void setQuantidadeCarrosEstacionados(int quantidadeCarrosEstacionados) {
        this.quantidadeCarrosEstacionados = quantidadeCarrosEstacionados;
    }
}