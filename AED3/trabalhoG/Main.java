import java.io.*;
import java.util.Scanner;

public class Main {
  public static Arquivo<Usuario> arqLivros;

  public static Scanner leitura = new Scanner(System.in);
  // public static Scanner let2 = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // remover em um código real
    (new File("dados/Usuario/arquivo.db")).delete();
    (new File("dados/Usuario.hash_c.db")).delete();
    (new File("dados/Usuario.hash_d.db")).delete();
    // Arquivo<Livro> arqLivros;
    arqLivros = new Arquivo<>("Usuario", Usuario.class.getConstructor());
    int oqfazer = 0;
    int op2=1;
    int opcao=0;
    do {
      // limpar();
      System.out.println("              MENU");
      System.out.println("-------------------------------");
      System.out.println("1 - Acesso a conta");
      System.out.println("2 - Novo usuario");
      System.out.println("\n0 - Sair");
      oqfazer = leitura.nextInt();
      
      switch (oqfazer) {
        case 0:
          ;
          break;

        case 1:

          if (acessoUsuario(arqLivros) == 0) {

            do {
              System.out.println("              MENU");
              System.out.println("-------------------------------");
              System.out.println("1 - Minha area");
              System.out.println("2 - Buscar perguntas");
              System.out.println("\n0 - Voltar");
               opcao = leitura.nextInt();
              switch (oqfazer) {

                case 1:
                  while (op2 != 0) {
                    System.out.println("              MENU");
                    System.out.println("-------------------------------");
                    System.out.println("1 - Minhas perguntas");
                    System.out.println("2 - Minhas respostas");
                    System.out.println("3 - Meus votos em perguntas");
                    System.out.println("4 - Meus votos em respostas");
                    System.out.println("\n0 - Voltar");
                     op2 = leitura.nextInt();
                    switch (op2) {
                      case 0:
                        ;
                        break;
                      case 1:
                        ;
                        break;
                      case 2:
                        ;
                        break;
                      case 3:
                        ;
                        break;
                      case 4:
                        ;
                        break;
                      default:
                        System.out.println("Opcao inexistente");
                        break;

                    }

                  } 
                  break;

                case 2:
                  ;
                  break;

                default:
                  System.out.println("Opcao invalida");
                  break;
              }

            } while (opcao != 0);
          }
          break;

        case 2:
          Teste(arqLivros);
          break;

        default:
          System.out.print("Opcao Invalida");
          break;
      }

    } while (oqfazer != 0);

  }

  public static void Teste(Arquivo<Usuario> a) throws Exception {

    String nome = "";
    String email = "";
    String senha = "";

    leitura.nextLine();

    // leitura.nextLine();
    System.out.print("Digite o Email : ");
    email = leitura.nextLine();
    System.out.println();
    if (!email.equals("")) {
      if (!arqLivros.saberLocal(email))// verificar cadastro -- ak caso n tiver cadastro CONFERIR SE JA EXISTE
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
          try {
            int i1 = arqLivros.creat(u1);
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.println("Cadastro realizado com sucesso");
        } else {
          System.out.println("Cadastro abortado");
        }

      } else {
        System.out.println("Email ja cadastrado");
      }

    } else {
      System.out.println("Email em branco");
    }
  }

  public static int acessoUsuario(Arquivo<Usuario> a) throws Exception {

    String email = "";
    String senha = "";

    leitura.nextLine();

    System.out.print("Digite o Email : ");
    email = leitura.nextLine();
    if (arqLivros.saberLocal(email)) {
      System.out.print("Digite a Senha : ");
      senha = leitura.nextLine();
      if (arqLivros.sabersenha(email, senha)) {
        System.out.println("Login realizado, redirecionando...");
        arqLivros.perguntaN(email, senha);
        return 0;
      } else {
        System.out.println("Senha incorreta");
      }
    } else {
      System.out.println("Email nao encontrado");
    }
    return 1;
  }

  public static void limpar() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}