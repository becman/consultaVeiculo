package com.example.consultaVeiculos.principal;

import com.example.consultaVeiculos.model.DadosVeiculo;
import com.example.consultaVeiculos.model.Modelos;
import com.example.consultaVeiculos.model.Veiculo;
import com.example.consultaVeiculos.service.ConsumoAPI;
import com.example.consultaVeiculos.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConverteDados conversor = new ConverteDados();

    private ConsumoAPI  consumoAPI = new ConsumoAPI();
    public void exibeMenu(){
        var menu = """
                *** OPÇÔES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das Opções para consultar
                """;

        System.out.println(menu);
        var opcao = leitura.nextLine();

        String endereco;

        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        }else if(opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        }else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoAPI.obterDados(endereco);
//        System.out.println(json);
        List<DadosVeiculo> dados = conversor.obterLista(json, DadosVeiculo.class);
        dados.stream()
                .sorted(Comparator.comparing(DadosVeiculo::codigo))
                .forEach(System.out::println);

        System.out.println("\n*** Informe o código da marca para consulta ***");
        var codigoMarca = leitura.nextLine();

        endereco = endereco+"/"+codigoMarca+"/modelos";
        json = consumoAPI.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculo::codigo))
                .forEach(System.out::println);

        System.out.println("\n*** Digite um trecho do nome do veículo que deseja ***");
        var nomeVeiculo = leitura.nextLine();

        List<DadosVeiculo> modeloListaVeiculos = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase())).collect(Collectors.toList());

        System.out.println("\n *** Modelos Existentes ***");
        modeloListaVeiculos.forEach(System.out::println);

        System.out.println("\n*** Digite por favor o código do modelo que deseja ***");
        var codigoModelo = leitura.nextLine();

        endereco = endereco +"/"+codigoModelo + "/anos";
        json = consumoAPI.obterDados(endereco);

        List<DadosVeiculo> anos = conversor.obterLista(json, DadosVeiculo.class);
        List<Veiculo> veiculos = conversor.obterLista(json, Veiculo.class);

        for (int i = 0; i < anos.size(); i++){
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoAPI.obterDados(enderecoAnos);

            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados por avaliações por ano:");
        veiculos.forEach(System.out::println);

    }
}
