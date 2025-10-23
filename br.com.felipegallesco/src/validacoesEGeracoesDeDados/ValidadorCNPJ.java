package validacoesEGeracoesDeDados;

import java.util.Scanner;

/**
 *
 * Classe que valida um número de CNPJ informado pelo usuário
 *
 * @author Felipe Gallesco
 * @version 1.0
 * @since 2025-09-20
 */
public class ValidadorCNPJ {

    /** +
     *
     * @param cnpjEntrada
     * @return
     */
    public static boolean isValid(String cnpjEntrada) {
            if (cnpjEntrada == null) return false;

            // Remove tudo que não for número
            String cnpj = cnpjEntrada.replaceAll("\\D", "");

            // Deve ter 14 dígitos
            if (cnpj.length() != 14) return false;

            // Rejeita sequências de dígitos iguais (ex: 00000000000000)
            if (cnpj.chars().distinct().count() == 1) return false;

            try {
                int dv1 = calculateCheckDigit(cnpj.substring(0, 12), new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
                int dv2 = calculateCheckDigit(cnpj.substring(0, 12) + dv1, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

                int providedDv1 = Character.getNumericValue(cnpj.charAt(12));
                int providedDv2 = Character.getNumericValue(cnpj.charAt(13));

                return dv1 == providedDv1 && dv2 == providedDv2;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    /** +
     *
     * @param base
     * @param weights
     * @return
     */
        private static int calculateCheckDigit(String base, int[] weights) {
            int sum = 0;
            for (int i = 0; i < base.length(); i++) {
                int digit = Character.getNumericValue(base.charAt(i));
                sum += digit * weights[i];
            }
            int remainder = sum % 11;
            return (remainder < 2) ? 0 : (11 - remainder);
        }

    /** +
     *
     * @param args
     */
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o CNPJ: ");
            String entrada = scanner.nextLine();

            if (isValid(entrada)) {
                System.out.println("✅ CNPJ válido!");
            } else {
                System.out.println("❌ CNPJ inválido!");
            }

            scanner.close();
        }
    }