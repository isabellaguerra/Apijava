package br.com.fatec.tp2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa o objeto "rating": { "average": 8.5 } dentro do JSON da série.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAvaliacao {

    private Double average;

    public Double getAverage() {
        return average;
    }
}
