package menezes.diniz.willian.screemmatch.main;

import menezes.diniz.willian.screemmatch.model.DadosSerie;
import menezes.diniz.willian.screemmatch.model.DadosTemporada;
import menezes.diniz.willian.screemmatch.service.ConsumoApi;
import menezes.diniz.willian.screemmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=f513b2f7";

    public void exibeMenu(){

        System.out.println("Qual serie deseja ver ?");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);


		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
         temporadas.forEach(System.out::println); // isso faz o mesmo q o foreach
       // for(DadosTemporada temporada : temporadas){System.out.println(temporada);}
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }

}
