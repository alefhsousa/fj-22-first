package br.com.caelum.ingresso.clients.imdb;

import br.com.caelum.ingresso.model.Filme;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdClient {

    public <T> Optional<T> buscaDetalheFilme(Filme filme, Class<T> tClass) {
        String urlBase = "https://omdb-fj22.herokuapp.com/movie?title=";
        String nomeDoFilme = filme.getNome().replace(" ", "+");
        String urlDoServico  = urlBase + nomeDoFilme;
        RestTemplate restTemplate = new RestTemplate();

        try {

            return Optional.of(restTemplate.getForObject(urlDoServico, tClass));
        }catch (RestClientException rc) {
            System.out.println("deu problema ao procurar o filme:  " + nomeDoFilme);
            return Optional.empty();
        }
    }
}
