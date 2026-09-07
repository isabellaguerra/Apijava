package br.com.fatec.tp2;

/**
 * Contrato para quem converte JSON em objetos Java.
 * Manter uma interface aqui deixa o sistema preparado caso, no futuro,
 * a gente troque o Jackson por outra biblioteca (Gson, por exemplo)
 * sem precisar mexer em quem usa ConverteDados.
 */
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
