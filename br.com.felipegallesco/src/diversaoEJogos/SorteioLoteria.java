package diversaoEJogos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe que gera três jogos de loteria com seis números únicos entre 1 e 60.
 *
 * Depois ordena os números de cada jogo e exibe-os junto com a data atual em ordem crescente.
 *
 * @author Felipe Gallesco
 * @version 1.0
 * @since 2025-09-20 *
 *
 */
public class SorteioLoteria {

    public static void main(String[] args) {

        Random random = new Random();

        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

        // Define o formato da data
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formato);

        System.out.println("Sugestão de jogos para loteria");
        System.out.println();
        System.out.println("Cartão com três jogos gerado em " + dataFormatada);
        System.out.println();

        // Gera três jogos
        for (int i = 1; i <= 3; i++) {

            Set<Integer> numeros = new HashSet<>();

            // Gera seis números únicos entre 1 e 60
            while (numeros.size() < 6) {
                numeros.add(random.nextInt(60) + 1);
            }

            // Ordena os números
            List<Integer> numerosOrdenados = new ArrayList<>(numeros);
            Collections.sort(numerosOrdenados);

            // Exibe o jogo
            System.out.print("Jogo " + i + ":    ");
            for (int numero : numerosOrdenados) {
                System.out.print(numero + " ");
            }
            System.out.println();

            //testesshgit
        }
    }
}