package validacoesEGeracoesDeDados;

import java.util.*;

/**
 * Classe que gera X números de CNPJs válidos, sendo X o número informado pelo usuário
 *
 * @author Felipe Gallesco
 * @version 1.0
 * @since 2025-09-20
 */

public class GeradorCNPJ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos CNPJs deseja gerar? ");
        int quantidade;

        try {
            quantidade = Integer.parseInt(scanner.nextLine());
            if (quantidade <= 0) {
                System.out.println("Informe um número positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite apenas números inteiros.");
            return;
        }

        System.out.println("\n--- CNPJs Gerados ---");
        for (int i = 0; i < quantidade; i++) {
            String cnpj = gerarCNPJ();
            System.out.println(cnpj);
        }
        scanner.close();
    }

    /**
     * Gera um CNPJ válido e formatado.
     */
    public static String gerarCNPJ() {
        Random random = new Random();

        // 8 primeiros dígitos: número base da empresa
        int[] numeros = new int[12];
        for (int i = 0; i < 8; i++) {
            numeros[i] = random.nextInt(10);
        }

        // 4 dígitos do "número de filial" — geralmente 0001
        numeros[8] = 0;
        numeros[9] = 0;
        numeros[10] = 0;
        numeros[11] = 1;

        // Calcula dígitos verificadores
        int dv1 = calcularDigito(numeros, new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        int[] numerosComDV1 = Arrays.copyOf(numeros, 13);
        numerosComDV1[12] = dv1;
        int dv2 = calcularDigito(numerosComDV1, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});

        // Monta string final com máscara
        StringBuilder cnpj = new StringBuilder();
        for (int n : numeros) cnpj.append(n);
        cnpj.append(dv1).append(dv2);

        return formatarCNPJ(cnpj.toString());
    }

    /**
     * Calcula o dígito verificador usando módulo 11.
     */
    private static int calcularDigito(int[] numeros, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += numeros[i] * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    /**
     * Formata um CNPJ "limpo" (14 dígitos) no formato 00.000.000/0000-00.
     */
    private static String formatarCNPJ(String cnpj) {
        return String.format("%s.%s.%s/%s-%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12, 14));
    }
}

