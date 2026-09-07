package br.com.fatec.tp2;

import java.io.IOException;
import java.util.Scanner;

/**
 * TP II - Menu de consulta a uma entidade (Série de TV) usando a API pública TVMaze.
 *
 * Opções do menu:
 *  1. Consultar -> busca uma série pelo nome na API e registra a consulta no log
 *  2. Listar    -> mostra o histórico de consultas já registradas no .log
 *  3. Sair      -> encerra o programa
 */
public class Main {

    public static void main(String[] args) {
        Scanner leitorTeclado = new Scanner(System.in);
        ConsultaSerieAPI api = new ConsultaSerieAPI();
        IConverteDados converteDados = new ConverteDados();
        RegistradorLog log = new RegistradorLog();

        boolean continuarNoLoop = true;

        System.out.println("==========================================");
        System.out.println(" Bem-vindo ao Consultor de Séries (TVMaze)");
        System.out.println("==========================================");

        while (continuarNoLoop) {
            exibirMenu();
            String opcaoEscolhida = leitorTeclado.nextLine().trim();

            switch (opcaoEscolhida) {
                case "1":
                    consultar(leitorTeclado, api, converteDados, log);
                    break;

                case "2":
                    log.listarTudo();
                    break;

                case "3":
                    continuarNoLoop = false;
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha 1, 2 ou 3.\n");
            }
        }

        leitorTeclado.close();
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Consultar");
        System.out.println("2. Listar");
        System.out.println("3. Sair");
        System.out.print("> ");
    }

    private static void consultar(Scanner leitorTeclado, ConsultaSerieAPI api,
                                   IConverteDados converteDados, RegistradorLog log) {
        System.out.print("\nDigite o nome da série que deseja consultar: ");
        String nomeSerie = leitorTeclado.nextLine().trim();

        if (nomeSerie.isEmpty()) {
            System.out.println("Você não digitou nenhum nome. Tente novamente.\n");
            return;
        }

        try {
            String json = api.buscarSeriePorNome(nomeSerie);

            if (json == null) {
                System.out.println("Nenhuma série encontrada com o nome \"" + nomeSerie + "\".\n");
                log.registrar("Consulta SEM RESULTADO para: \"" + nomeSerie + "\"");
                return;
            }

            DadosSerie serie = converteDados.obterDados(json, DadosSerie.class);

            System.out.println(serie);

            log.registrar("Consulta realizada: \"" + serie.getName() + "\" (id=" + serie.getId() + ")");

        } catch (IOException | InterruptedException e) {
            System.out.println("Não foi possível se conectar à API. Verifique sua internet e tente novamente.");
            e.printStackTrace();
        }

        System.out.println();
    }
}
