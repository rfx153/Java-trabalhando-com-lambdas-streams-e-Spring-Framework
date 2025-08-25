package br.com.alura.screenmatch.principal;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.model.Episodio;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "http://www.omdbapi.com/?t=" ;

    private final String API_KEY = "&apikey=3ee3d737";

    

    public void exibeMenu(){
        System.out.println("Digite o nome da série para ser exibida");
        var nomeSerie = leitura.nextLine();
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        
	    for(int i = 1; i<=dados.totalTemporadas(); i++)
        {
        json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season="+ i +API_KEY);
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
		temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
    
            for(int i =0; i< dados.totalTemporadas(); i++){
                List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
                for (int j =0; j < episodiosTemporada.size(); j++)
                {
                    System.out.println(episodiosTemporada.get(j).titulo());

                }
            }
                
    
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        //temporadas.forEach(System.out::println);

     /*List<String> nome = Arrays.asList("rfx", "paulo", "Nico", "Maria");

     nome.stream()
        .sorted()
        .limit(3)
        .filter(n -> n.startsWith("N"))
        .map(n -> n.toUpperCase())
            .forEach(System.out::println);*/

            List <DadosEpisodio> dadosEpisodios = temporadas.stream()
            .flatMap(t-> t.episodios().stream())
            .collect(Collectors.toList());
            //.toList();

            /*dadosEpisodios.add(new DadosEpisodio("teste", 3, "10" ,"2020-01-01" ));
            dadosEpisodios.forEach(System.out::println);*/
            // System.out.println("\n Top 5 episódios");
            // dadosEpisodios.stream()
            //     .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
            //     .peek(e -> System.out.println("primeiro filtro(N/A)" + e))
            //     .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
            //     .peek(e -> System.out.println("ordenação " + e))
            //     .limit(10)
            //     .peek(e -> System.out.println("Limite " + e))
            //     .map(e -> e.titulo().toUpperCase())
            //     .peek(e -> System.out.println("Mapeamento " + e))
            //     .forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
            .flatMap(t-> t.episodios().stream()
            .map(d-> new Episodio(t.numero(), d)))
            .collect(Collectors.toList());

            //  episodios.forEach(System.out::println);
            //busca de ep pelo nome
            // System.out.println("Digite o texto do titulo do ep") ;
            // var trechoTitulo = leitura.nextLine();
            // Optional<Episodio> episodioBuscado = episodios.stream()
            //     .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
            //     .findFirst();
            // if (episodioBuscado.isPresent()){
            //     System.out.println("episódio encontrando");
            //     System.out.println("temporada: " + episodioBuscado.get().getTemporada());
            // }
            // else {
            //     System.out.println("episódio não encontrado");
            // }

            // System.out.println("A partir de que ano você deseja ver os eps?");
            // var ano = leitura.nextInt();
            // leitura.nextLine();

            // LocalDate dataBusca = LocalDate.of(ano, 1, 1);

            // DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // episodios.stream()
            //     .filter(e -> e.getdataLancamento() != null && e.getdataLancamento().isAfter(dataBusca))
            //     .forEach(e -> 
            //         System.out.println( 
            //             "Temporada" + e.getTemporada() + 
            //             " Episodio: " + e.getTitulo() + 
            //             " Data Lançamento: " + e.getdataLancamento().format(formatador)
                    
            //         )
            //     );

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
        .filter(e -> e.getAvaliacao()> 0.0)
        .collect(Collectors.groupingBy(Episodio::getTemporada, 
                 Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

    }
}

//{1=8.154545454545454, 2=7.95, 3=8.93, 4=9.23, 5=7.860000000000001, 6=8.98, 7=9.028571428571428, 8=6.3999999999999995}
//{1=8.97, 2=8.833333333333334, 3=8.93, 4=9.23, 5=8.733333333333334, 6=8.98, 7=9.028571428571428, 8=6.3999999999999995}