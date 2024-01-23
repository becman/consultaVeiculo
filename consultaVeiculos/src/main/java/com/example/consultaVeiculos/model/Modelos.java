package com.example.consultaVeiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<DadosVeiculo> modelos) {
}
