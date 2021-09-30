import java.util.Scanner;
import java.io.*;

public class Main {

    public static Arquivo<Usuario> arqUsuario;
    public static Scanner leitura = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try {
            arqUsuario = new Arquivo<>("Usuario", Usuario.class.getConstructor());
            int oqFazer = 0;
            do {
                System.out.println("ACESSO\n");
                System.out.println("1) Acesso ao sistema");
                System.out.println("2) Novo Usuario (Primeiro acesso)");

                System.out.println("\n0) Sair");
                oqFazer = leitura.nextInt();
                switch (oqFazer) {
                    case 1:
                        // sistemaOpen();
                        break;
                    case 2:
                        newUsuario(arqUsuario);
                        break;

                    default:
                        break;
                }
            } while (oqFazer != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * public static void sistemaOpen() { String email = ""; String senha = "";
     * 
     * email = leitura.nextLine(); //buscar no index sec if(/*Busca pelo email no
     * hash//se existir no indice { senha = leitura.nextLine(); // comparar com a do
     * BD if(senha.equals("d"/*Senha puxado do BD/)) { System.out.
     * println("Logado com sucesso!, redirecionando para a pagina principal..."); }
     * else{ System.out.println("Senha incorreta"); } } else{
     * System.out.println("Email nao encontrado na base de dados do sistema"); } }
     */

    public static void newUsuario(Arquivo<Usuario> arqUsuario) throws Exception{
        arqUsuario = new Arquivo<>("Usuario", Usuario.class.getConstructor());
        String nome = "";
        String email = "";
        String senha = "";

        System.out.println("Hallo");
        leitura.nextLine();
        email = leitura.nextLine();
        if (!email.equals("")) {
            if (email.length() > 4)// verificar cadastro -- ak caso n tiver cadastro
            {
                nome = leitura.nextLine();
                senha = leitura.nextLine();

                System.out.println("Deseja confirmar o cadastro -> 0 - Nao  1 - Sim ");
                int confirm = leitura.nextInt();
                if (confirm == 1) {

                    Usuario U1 = new Usuario(nome, email, senha);
                    arqUsuario.creat(U1);
                    System.out.println("Cadastro realizado com sucesso");
                } else {
                    System.out.println("Email ja cadastrado!");
                }

            }

        }

    }

}
