package menezes.diniz.willian.screemmatch;

import menezes.diniz.willian.screemmatch.model.DadosEpisodio;
import menezes.diniz.willian.screemmatch.model.DadosSerie;
import menezes.diniz.willian.screemmatch.model.DadosTemporada;
import menezes.diniz.willian.screemmatch.service.ConsumoApi;
import menezes.diniz.willian.screemmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreemmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreemmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var cosumoDeApi = new ConsumoApi();
		var json = cosumoDeApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=f513b2f7");
		System.out.println(json);
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = cosumoDeApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=f513b2f7");
		DadosEpisodio dadosEpisodio = converteDados.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = cosumoDeApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=f513b2f7");
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
         temporadas.forEach(System.out::println); // isso faz o mesmo q o foreach
		//for(DadosTemporada temporada : temporadas){System.out.println(temporada);}






	}
}
