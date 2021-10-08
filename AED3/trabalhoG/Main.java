import java.io.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
  public static Arquivo<Usuario> arqLivros;
  public static ArquivoU<Pergunta> perguntas;

  public static Scanner leitura = new Scanner(System.in);
  // public static Scanner let2 = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // remover em um código real
    (new File("dados/Usuario/arquivo.db")).delete();
    (new File("dados/Usuario.hash_c.db")).delete();
    (new File("dados/Usuario.hash_d.db")).delete();

    // Arquivo<Livro> arqLivros;
    arqLivros = new Arquivo<>("Usuario", Usuario.class.getConstructor());
    perguntas = new ArquivoU<>("Pergunta", Pergunta.class.getConstructor());
    int oqfazer = 0;
    int op2 = 1;
    int opcao = 0;
    do {
      limpar();
      System.out.println("              MENU");
      System.out.println("-------------------------------");
      System.out.println("1 - Acesso a conta (Minha area/Buscar perguntas)");
      System.out.println("2 - Novo usuario");
      System.out.println("\n0 - Sair");
      oqfazer = leitura.nextInt();

      switch (oqfazer) {
        case 0:
          ;
          break;

        case 1:

          if (acessoUsuario(arqLivros, perguntas) == 0) {

            do {
              limpar();
              System.out.println("              MENU");
              System.out.println("-------------------------------");
              System.out.println("1 - Minha area");
              System.out.println("2 - Buscar perguntas");
              System.out.println("\n0 - Voltar");
              opcao = leitura.nextInt();
              switch (opcao) {
                  case 0:
                  ;
                  break;
                case 1:
                  while (op2 != 0) {
                    limpar();
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
                        System.out.println("Aperte enter para continuar");
                        String teste23 = leitura.nextLine();
                        break;

                    }

                  }
                  break;

                case 2:
                  ;
                  break;

                default:
                  System.out.println("Opcao invalida");
                  System.out.println("Aperte enter para continuar");
                  String teste23 = leitura.nextLine();
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
          System.out.println("Aperte enter para continuar");
          String teste23 = leitura.nextLine();
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
          System.out.println("\nAperte enter para continuar");
          String teste23 = leitura.nextLine();
        } else {
          System.out.println("Cadastro abortado");
          System.out.println("Aperte enter para continuar");
          String teste23 = leitura.nextLine();
        }

      } else {
        System.out.println("Email ja cadastrado");
        System.out.println("Aperte enter para continuar");
        String teste23 = leitura.nextLine();
      }

    } else {
      System.out.println("Email em branco");
      System.out.println("Aperte enter para continuar");
      String teste23 = leitura.nextLine();
    }
  }

  public static int acessoUsuario(Arquivo<Usuario> a, ArquivoU<Pergunta> p) throws Exception {

    String email = "";
    String senha = "";
    limpar();
    leitura.nextLine();

    System.out.print("Digite o Email : ");
    email = leitura.nextLine();
    if (arqLivros.saberLocal(email)) {
      System.out.print("Digite a Senha : ");
      senha = leitura.nextLine();
      if (arqLivros.sabersenha(email, senha)) {
        System.out.println("Login realizado, redirecionando...");
        long t = 10 / 10 / 2020;
        short g = 100;
        Pergunta tt = new Pergunta(3, 2, t, g, "Se 2 + 2 sao 4 , por que o pc ta caro?", "caro", true);
        p.Create(tt);

        p.readP(1);
        //limpar();
        System.out.println("Aperte enter para continuar");
        String teste23 = leitura.nextLine();

        return 0;
      } else {
        System.out.println("Senha incorreta");
        System.out.println("Aperte enter para continuar");
        String teste23 = leitura.nextLine();
      }
    } else {
      System.out.println("Email nao encontrado");
      System.out.println("Aperte enter para continuar");
      String teste23 = leitura.nextLine();
    }
    return 1;
  }

  public static void limpar() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void inserir(String pergunta, String palavrachave, ArquivoU<Pergunta> p) throws Exception {

    long t = 10 / 10 / 2020;
    short g = 0;

    Calendar c = Calendar.getInstance();
    Date data = c.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");

    String salvaData = sdf.format(data).toString();
    Long datelong = Long.parseLong(salvaData);

    // int idUsuario1, int idPergunta1, long criacao1, short nota1, String
    // pergunta1, String palavrasChave1, boolean ativa1
    Pergunta tt = new Pergunta(-1, -1, datelong, g, pergunta, palavrachave, true);
    p.Create(tt);
    System.out.println(tt.toString());

  }

}