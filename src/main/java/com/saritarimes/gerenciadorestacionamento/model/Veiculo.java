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

    /* ---------- Metodos acessores ---------- */

}
