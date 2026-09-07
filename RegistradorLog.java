package br.com.fatec.tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsável por registrar, em um arquivo .log, cada entidade (série)
 * consultada pelo usuário, junto com a data/hora e o fuso horário da consulta.
 * Também é capaz de listar tudo o que já foi registrado.
 */
public class RegistradorLog {

    private static final String NOME_ARQUIVO = "consultas.log";
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");

    /**
     * Registra uma linha no log com o horário de Brasília (America/Sao_Paulo).
     */
    public void registrar(String descricao) {
        ZonedDateTime agoraEmBrasilia = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String linha = "[" + agoraEmBrasilia.format(FORMATO_DATA) + "] " + descricao;

        try {
            FileWriter escrita = new FileWriter(NOME_ARQUIVO, true);
            escrita.write(linha + System.lineSeparator());
            escrita.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o registro no log.");
            e.printStackTrace();
        }
    }

    /**
     * Lê e imprime, no console, todas as consultas já registradas no log.
     */
    public void listarTudo() {
        try {
            FileReader leitura = new FileReader(NOME_ARQUIVO);
            BufferedReader bufferedReader = new BufferedReader(leitura);

            String linha;
            boolean encontrouAlgumaLinha = false;

            System.out.println("\n===== HISTÓRICO DE CONSULTAS =====");
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
                encontrouAlgumaLinha = true;
            }

            if (!encontrouAlgumaLinha) {
                System.out.println("Nenhuma consulta foi registrada ainda.");
            }
            System.out.println("===================================\n");

            bufferedReader.close();
            leitura.close();
        } catch (IOException e) {
            System.out.println("Ainda não existe nenhum registro de consulta (o arquivo de log será criado na primeira consulta).");
        }
    }
}
