package com.saritarimes.gerenciadorestacionamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Veiculo {
    /* ---------- Propriedades ---------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(nullable = false, length = 20)
    @Size(message = "O campo marca não pode ser deixado em branco.") //verificar se nao eh necessario colocar o min
    @Size(max = 20, message = "O nome da marca é muito longo.")
    @Pattern(regexp = "^[A-Za-z0-9]$", message = "O nome da marca deve conter apenas letras ou números.")
    private String marca;

    @Column(nullable = false, length = 20)
    @Size(message = "O campo modelo não pode ser deixado em branco.") //verificar se nao eh necessario colocar o min
    @Size(max = 20, message = "O nome do modelo é muito longo.")
    @Pattern(regexp = "^[A-Za-z0-9]$", message = "O nome do modelo deve conter apenas letras ou números.")
    private String modelo;

    @Column(nullable = false, length = 15)
    @Size(message = "O campo cor não pode ser deixado em branco.") //verificar se nao eh necessario colocar o min
    @Size(max = 15, message = "O nome da cor é muito longo.")
    @Pattern(regexp = "^[A-Za-z]$", message = "O nome da cor deve conter apenas letras.")
    private String cor;

    @Column(nullable = false, length = 7) //supondo placas de veiculos brasileiros
    @Size(min = 7, max = 7, message = "O campo placa deve conter sete caracteres.") //verificar se nao eh necessario colocar o min
    @Pattern(regexp = "^[A-Za-z0-9]$", message = "A placa deve conter apenas letras ou números.")
    private String placa;

    @Column(nullable = false)
    @Size(min = 7, max = 7, message = "O campo placa deve conter sete caracteres.") //verificar se nao eh necessario colocar o min
    @Pattern(regexp = "^(cCmM)$", message = "O tipo do veículo deve ser C (carro) ou M (moto).")
    private char tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Estabelecimento estabelecimento;


    /* ---------- Construtores ---------- */
    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, String cor, String placa, char tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipo = tipo;
    }


    /* ---------- Metodos acessores ---------- */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
