package com.example.consultaVeiculos.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca")String marca,
        @JsonAlias("Modelo")String Modelo,
        @JsonAlias("AnoModelo")Integer anoModelo,
        @JsonAlias("Combustivel") String combustivel
) {
}
