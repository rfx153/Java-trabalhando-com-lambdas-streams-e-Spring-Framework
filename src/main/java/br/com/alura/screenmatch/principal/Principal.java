package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "http://www.omdbapi.com/?t=" ;

    private final String API_KEY = "&apikey=3ee3d737";

    

    public void exibeMenu(){
       /*  System.out.println("Digite o nome da série para ser exibida");
        var nomeSerie = leitura.nextLine();
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        */
		/*for(int i = 1; i<=dados.totalTemporadas(); i++){
        json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season="+ i +API_KEY);
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
		temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);*/
    /* 
            for(int i =0; i< dados.totalTemporadas(); i++){
                List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
                for (int j =0; j < episodiosTemporada.size(); j++){
                    System.out.println(episodiosTemporada.get(j).titulo());

                }
            }
    */
        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        //temporadas.forEach(System.out::println);

     List<String> nome = Arrays.asList("rfx", "paulo", "Nico", "Maria");

     nome.stream()
        .sorted()
        .limit(3)
        .filter(n -> n.startsWith("N"))
        .map(n -> n.toUpperCase())
            .forEach(System.out::println);

    }
}
