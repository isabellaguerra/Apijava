package br.com.fatec.tp2;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Responsável apenas por conversar com a API pública TVMaze (https://api.tvmaze.com).
 * Não conhece nada sobre menu, console ou log — só busca o JSON e devolve.
 */
public class ConsultaSerieAPI {

    private static final String ENDERECO_BASE = "https://api.tvmaze.com/singlesearch/shows?q=";

    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Consulta uma série pelo nome.
     *
     * @param nomeSerie nome (ou parte do nome) da série buscada
     * @return o JSON bruto devolvido pela API, ou null se a série não for encontrada (HTTP 404)
     */
    public String buscarSeriePorNome(String nomeSerie) throws IOException, InterruptedException {
        String nomeCodificado = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);
        URI endereco = URI.create(ENDERECO_BASE + nomeCodificado);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            return null; // Nenhuma série encontrada com esse nome
        }

        if (response.statusCode() != 200) {
            throw new IOException("A API retornou um erro inesperado. Código HTTP: " + response.statusCode());
        }

        return response.body();
    }
}
