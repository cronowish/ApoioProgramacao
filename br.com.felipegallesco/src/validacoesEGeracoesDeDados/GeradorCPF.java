package validacoesEGeracoesDeDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** +
 *
 */

public class GeradorCPF {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantas massas de CPF deseja gerar? ");
        int quantidade = scanner.nextInt();

        List<String> cpfs = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            cpfs.add(gerarCPFValido());
        }

        // Exibe os CPFs gerados
        for (int i = 0; i < cpfs.size(); i++) {
            System.out.println("CPF " + (i + 1) + ": " + cpfs.get(i));
        }
    }

    public static String gerarCPFValido(){
        Random rand = new Random();
        int[] cpf = new int[11];

        // Gera os 9 primeiros digitos
        for (int i = 0; i < 9; i++){
            cpf[i] = rand.nextInt(10);
        }

        //Calcula o primeiro digito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++){
            soma1 += cpf[i] * (10 - i);
        }
        int digito1 = (soma1 * 10) % 11;
        cpf[9] = (digito1 == 10 || digito1 == 11) ? 0 : digito1;

        //Calcula o segundo digito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += cpf[i] * (11 - i);
        }
        int digito2 = (soma2 * 10) % 11;
        cpf[10] = (digito2 == 10 || digito2 == 11) ? 0 : digito2;

        //Monta o CPF em formato String
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 11; i++){
            sb.append(cpf[i]);
        }

        return sb.toString();

    }
}
