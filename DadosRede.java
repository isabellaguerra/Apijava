package br.com.fatec.tp2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa o objeto "network": { "name": "HBO" } dentro do JSON da série.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosRede {

    private String name;

    public String getName() {
        return name;
    }
}
