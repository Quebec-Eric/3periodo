import java.io.*;
import java.util.Scanner;

public class Main {
    public static Arquivo<Usuario> arqLivros;
    public static Scanner leitura = new Scanner(System.in);
    // public static Scanner let2 = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // remover em um código real
        (new File("dados/livros/arquivo.db")).delete();
        (new File("dados/livros.hash_c.db")).delete();
        (new File("dados/livros.hash_d.db")).delete();
        // Arquivo<Livro> arqLivros;
        arqLivros = new Arquivo<>("Usuario", Usuario.class.getConstructor());
        int oqfazer = 0;
        do {
            oqfazer = leitura.nextInt();
            switch (oqfazer) {
                case 1:
                    Teste(arqLivros);
                    break;

                case 2:
                   // arqLivros.remove("felipe@hotmail.com");
                    break;

                default:
                    break;
            }

        } while (oqfazer != 0);

       

    }


    public static void Teste(Arquivo a)  {
        
      

         String nome = "";
        String email = "";
        String senha = "";

        leitura.nextLine();
        System.out.println("Hallo");
        // leitura.nextLine();
        System.out.print("Digite o Email : ");
        email = leitura.nextLine();
        System.out.println();
        if (!email.equals("")) {
            if (email.length() > 4)// verificar cadastro -- ak caso n tiver cadastro CONFERIR SE JA EXISTE
            {
                System.out.print("Digite o Nome : ");
                nome = leitura.nextLine();
                System.out.println();
                System.out.print("Digite a Senha : ");
                senha = leitura.nextLine();
                System.out.println();

                System.out.println("Deseja confirmar o cadastro -> 0 - Nao  1 - Sim ");
                int confirm = leitura.nextInt();
                if (confirm == 1) {

                    // Usuario U1 = new Usuario(-1,email,nome, senha);
                    Usuario u1 = new Usuario(-1, email, nome, senha);
                    try{
                    int i1 = arqLivros.creat(u1);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("Cadastro realizado com sucesso");
                } else {
                    System.out.println("Cadastro abortado");
                }

            }
            else
            {
                System.out.println("Email ja cadastrado");
            }

        }
    }

}
