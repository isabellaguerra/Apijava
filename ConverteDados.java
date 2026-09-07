package br.com.fatec.tp2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe responsável por converter (desserializar) uma String JSON em
 * um objeto Java de qualquer tipo, de forma genérica.
 *
 * Em vez de instanciar um ObjectMapper toda vez que precisamos ler um JSON,
 * concentramos essa responsabilidade aqui: qualquer outra classe do sistema
 * pode chamar obterDados(json, MinhaClasse.class) e receber o objeto pronto.
 */
public class ConverteDados implements IConverteDados {

    private final ObjectMapper mapper = new ObjectMapper();

    public ConverteDados() {
        // Se a API devolver campos que não mapeamos na nossa classe,
        // não queremos que o programa quebre por causa disso.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter o JSON recebido da API.", e);
        }
    }
}
