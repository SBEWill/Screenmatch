package menezes.diniz.willian.screemmatch;

import menezes.diniz.willian.screemmatch.model.DadosSerie;
import menezes.diniz.willian.screemmatch.service.ConsumoApi;
import menezes.diniz.willian.screemmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreemmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreemmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var cosumoDeApi = new ConsumoApi();
		var json = cosumoDeApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=f513b2f7");
		//"https://www.omdbapi.com/?t=gilmore+girls&Seasson=1&apikey=f513b2f7";

		System.out.println(json);
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dados);



	}
}
