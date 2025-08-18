package br.com.alura.screenmatch.principal;

import java.util.Scanner;

import br.com.alura.screenmatch.service.ConsumoApi;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();


    private final String ENDERECO = "http://www.omdbapi.com/?t=" ;

    private final String API_KEY = "&apikey=3ee3d737";

    

    public void exibeMenu(){
        System.out.println("Digite o nome da série para ser exibida");
        var nomeSerie = leitura.nextLine();
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
    }
}
