import java.util.Scanner;
import logicas.*;

public class Main {

    public static Scanner leitura = new Scanner(System.in);
    public static Ajudante ajudar = new Ajudante();

    public static void main(String[] args) throws Exception {

        // apagar todos os dados antigos
        ajudar.apagarDados();
        //System.out.println(Inicio.menu());
        if (Inicio.menu()) {
            menuPrincipal();
        } else {
            System.out.println("\n\nFalha ao conectar os sistema");
        }
        

    }

    public static void menuPrincipal() throws Exception {

        int opcao;
        do {
            System.out.println("              MENU");
            System.out.println("-------------------------------");
            System.out.println("1 - Minha area");
            System.out.println("2 - Buscar perguntas");
            System.out.println("\n0 - Voltar");
            try {
                opcao = leitura.nextInt();
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
            case 1:
                DentroSistema.menu();
                break;
            case 2:
             PerguntasTotais.Totais();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
            }

        } while (opcao != 0);
    }

}
