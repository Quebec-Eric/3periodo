package logicas;

import java.util.Scanner;


public class DentroSistema {
    public static Scanner leitura = new Scanner(System.in);

    public static void menu() throws Exception {

        int opcao;
        do {
            System.out.println("\n\nPERGUNTAS 1.0");
            System.out.println("=============");
            System.out.println("\nINÍCIO > MINHA ÁREA");
            System.out.println("\n1) Minhas perguntas");
            System.out.println("2) Minhas respostas");
            System.out.println("3) Meus votos em perguntas");
            System.out.println("4) Meus votos em respostas");
            System.out.println("\n0) Retornar ao menu anterior");

            System.out.print("\nOpcao: ");
            try {
                opcao = Integer.valueOf(leitura.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
            case 1:
                PetguntasUser.menu();
                break;
            case 2:
                System.out.println("Ainda não implementado.");
            
                break;
            case 3:
                System.out.println("Ainda não implementado.");
                // meusVotosEmPerguntas();
                break;
            case 4:
                System.out.println("Ainda não implementado.");
                // meusVotosEmRespostas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
            }

        } while (opcao != 0);
    }

  

}
