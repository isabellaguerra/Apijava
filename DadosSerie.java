package br.com.fatec.tp2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa a entidade "Série" consultada na API pública TVMaze
 * (https://api.tvmaze.com). Somente os campos que interessam ao
 * nosso sistema foram mapeados; o restante do JSON é ignorado.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosSerie {

    private Long id;
    private String name;
    private String type;
    private String language;
    private String status;
    private Integer runtime;
    private String premiered;

    @JsonAlias("officialSite")
    private String officialSite;

    private String[] genres;

    private DadosAvaliacao rating;
    private DadosRede network;
    private String summary;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getStatus() {
        return status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public String[] getGenres() {
        return genres;
    }

    public DadosAvaliacao getRating() {
        return rating;
    }

    public DadosRede getNetwork() {
        return network;
    }

    public String getSummary() {
        return summary;
    }

    /**
     * A API devolve o resumo em HTML (com tags <p>, <b>, etc.).
     * Este método devolve o texto "limpo", sem as tags, para impressão no console.
     */
    public String getSummarySemHtml() {
        if (summary == null) {
            return "Sem descrição disponível.";
        }
        return summary.replaceAll("<[^>]*>", "");
    }

    @Override
    public String toString() {
        String generos = (genres == null || genres.length == 0)
                ? "Não informado"
                : String.join(", ", genres);

        String notaMedia = (rating != null && rating.getAverage() != null)
                ? rating.getAverage().toString()
                : "Sem avaliação";

        String nomeRede = (network != null && network.getName() != null)
                ? network.getName()
                : "Não informado";

        return """
                ---------------------------------------------
                Nome.........: %s
                Tipo.........: %s
                Idioma.......: %s
                Status.......: %s
                Gêneros......: %s
                Estreia......: %s
                Duração (min): %s
                Nota média...: %s
                Emissora.....: %s
                Site oficial.: %s
                Sinopse......: %s
                ---------------------------------------------
                """.formatted(
                name,
                type,
                language,
                status,
                generos,
                premiered,
                runtime == null ? "Não informado" : runtime.toString(),
                notaMedia,
                nomeRede,
                officialSite == null ? "Não informado" : officialSite,
                getSummarySemHtml()
        );
    }
}
