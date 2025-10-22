package validacoesEGeracoesDeDados;

import java.util.Scanner;

/** +
 * Classe que valida um número de CPF informado pelo usuário
 *
 * @author Felipe Gallesco
 * @version 1.0
 * @since 2025-09-20
 */
public class ValidadorCPF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CPF (com ou sem pontuação): ");
        String entrada = sc.nextLine();
        sc.close();

        if (isCPFValido(entrada)) {
            System.out.println("CPF válido.");
        } else {
            System.out.println("CPF inválido.");
        }
    }

    /** +
     *
     * @param cpf
     * @return
     */
    public static boolean isCPFValido(String cpf) {
        if (cpf == null) return false;

        // Remove tudo que não for dígito
        cpf = cpf.replaceAll("\\D", "");

        // Deve ter 11 dígitos
        if (cpf.length() != 11) return false;

        // Elimina CPFs formados por sequência de mesmo dígito (ex: "00000000000")
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) return false;

        try {
            int[] nums = new int[11];
            for (int i = 0; i < 11; i++) nums[i] = Character.getNumericValue(cpf.charAt(i));

            // Calcula primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += nums[i] * (10 - i);
            }
            int resto = soma % 11;
            int dig1 = (resto < 2) ? 0 : 11 - resto;

            // Checa primeiro dígito
            if (nums[9] != dig1) return false;

            // Calcula segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += nums[i] * (11 - i);
            }
            resto = soma % 11;
            int dig2 = (resto < 2) ? 0 : 11 - resto;

            // Checa segundo dígito
            return nums[10] == dig2;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
