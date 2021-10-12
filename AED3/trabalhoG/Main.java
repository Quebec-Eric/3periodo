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
    (new File("dados/Pergunta/arquivo.db")).delete();
    (new File("dados/arvore.db")).delete();
    (new File("dados/bloco.listainv.bd")).delete();
    (new File("dados/dicionario.listainv.db")).delete();

    // Arquivo<Livro> arqLivros;
    arqLivros = new Arquivo<>("Usuario", Usuario.class.getConstructor());
    perguntas = new ArquivoU<>("Pergunta", Pergunta.class.getConstructor());
    int oqfazer = 0;
    int op2 = 1;
    int opcao = 0;
    int op3 = 1;
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
          String pegar = acessoUsuario(arqLivros, perguntas);
          if (pegar.charAt(1) == '0') {
            int pegarIdUsuario = pegar.charAt(0) - '0';
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
                  op2 = 1;
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
                        op3 = 1;
                        while (op3 != 0) {
                          System.out.println("              MENU");
                          System.out.println("-------------------------------");
                          System.out.println("1 - Listar");
                          System.out.println("2 - Incluir");
                          System.out.println("3 - Alternar");
                          System.out.println("4 - Arquviar");
                          System.out.println("\n0 - Voltar");
                          op3 = leitura.nextInt();
                          switch (op3) {
                            case 0:
                              ;
                              break;
                            case 1:
                              listarPerguntas(pegarIdUsuario, perguntas);

                              break;
                            case 2:
                              incluirPergunta(pegarIdUsuario, perguntas);
                              /*
                               * System.out.println("Digite sua pergunta :"); leitura.nextLine(); String
                               * novaPergunta = leitura.nextLine();
                               * System.out.println("Digite as palavras chave separadas por espaço :"); String
                               * palavrasChave = leitura.nextLine(); short g = 0; Pergunta tt = new
                               * Pergunta(pegarIdUsuario, -1, calendario(), g, novaPergunta, palavrasChave,
                               * true); perguntas.Create(tt); tt.toString();
                               */
                              break;
                            case 3:
                            listarPerguntasComId(pegarIdUsuario, perguntas);
                            System.out.println("\nGostaria de mudar qual Pergunta (Digite o id)");
                            int qualId = leitura.nextInt();
                            System.out.println("Digite sua nova pergunta :");
                            leitura.nextLine();
                            String novaPergunta = leitura.nextLine();
                            System.out.println("Digite as palavras chave separadas por ; :");
                            String palavrasChave = leitura.nextLine();
                            perguntas.update(qualId, novaPergunta, palavrasChave);
                             


                              break;
                            case 4:
                            
                            listarPerguntasComId(pegarIdUsuario, perguntas);
                            System.out.println("\nGostaria de mudar qual Pergunta (Digite o id)");
                            int ids = leitura.nextInt();
                            System.out.println("\nSelecione uma das opcoes: \n0 - Sair \n1 - Ativar \n2 - Desativar");
                            int status = leitura.nextInt();
                            if(status == 1)
                            {
                              perguntas.arquivarPergunta(ids, true);
                            }
                            else if(status == 2){
                            perguntas.arquivarPergunta(ids, false);
                            }


                              break;
                            default:
                              System.out.println("Opcao inexistente");
                              System.out.println("Aperte enter para continuar");
                              String teste24 = leitura.nextLine();
                              break;

                          }

                        }
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

  public static String acessoUsuario(Arquivo<Usuario> a, ArquivoU<Pergunta> p) throws Exception {

    String email = "";
    String senha = "";
    String tudo = "";
    limpar();
    leitura.nextLine();

    System.out.print("Digite o Email : ");
    email = leitura.nextLine();
    if (arqLivros.saberLocal(email)) {
      System.out.print("Digite a Senha : ");
      senha = leitura.nextLine();
      if (arqLivros.sabersenha(email, senha)) {
        System.out.println("Login realizado, redirecionando...");
        tudo += arqLivros.read(email, senha).getID();
        /*
         * long t = 2020; short g = 100; Pergunta tt = new Pergunta(3, 2, t, g,
         * "Se 2 + 2 sao 4 , por que o pc ta caro?", "caro", true); p.Create(tt);
         * 
         * p.readPId(3); Pergunta nova = p.read1(1); nova.toString(); // limpar();
         * System.out.println("Aperte enter para continuar"); String teste23 =
         * leitura.nextLine();
         */
        tudo += 0;

        return tudo;
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

    return tudo;
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

  public static long calendario() {
    Calendar c = Calendar.getInstance();
    Date data = c.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");

    String salvaData = sdf.format(data).toString();
    long datelong = Long.parseLong(salvaData);

    return datelong;
  }

  public static String sabertodosID(String pato) {

    String idsTodos = "";
    for (int i = 0; i < pato.length() - 1; i++) {

      if (pato.charAt(i) == ';') {
        idsTodos += pato.charAt(i + 1);
      }
    }

    return idsTodos;
  }

  public static void listarPerguntas(int pegarIdUsuario, ArquivoU<Pergunta> perguntas) throws Exception {
    String quantidadeP = perguntas.readPId(pegarIdUsuario);
    int local = quantidadeP.indexOf("|");
    int tamanhoPetguntas = quantidadeP.charAt(local + 1) - '0';
    String idsTT = sabertodosID(quantidadeP);
    // System.out.println(idsTT);
    for (int i = 1; i <= tamanhoPetguntas; i++) {
      perguntas.readProf(idsTT.charAt(i - 1) - '0').toString();

      

    }
    
  }

  public static void incluirPergunta(int pegarIdUsuario, ArquivoU<Pergunta> perguntas) throws Exception {
    System.out.println("Digite sua pergunta :");
    leitura.nextLine();
    String novaPergunta = leitura.nextLine();
    System.out.println("Digite as palavras chave separadas por ; :");
    String palavrasChave = leitura.nextLine();
    short g = 0;
    Pergunta tt = new Pergunta(pegarIdUsuario, -1, calendario(), g, novaPergunta, palavrasChave, true);
    perguntas.Create(tt);
    tt.toString();
     
  }

  public static void listarPerguntasComId(int pegarIdUsuario, ArquivoU<Pergunta> perguntas) throws Exception {
    String quantidadeP = perguntas.readPId(pegarIdUsuario);
    int local = quantidadeP.indexOf("|");
    int tamanhoPetguntas = quantidadeP.charAt(local + 1) - '0';
    String idsTT = sabertodosID(quantidadeP);
    // System.out.println(idsTT);
    for (int i = 1; i <= tamanhoPetguntas; i++) {

     Pergunta ppp= perguntas.read1(idsTT.charAt(i - 1) - '0');
     ppp.toString();
     System.out.println("Esta ativada == "+ppp.getAtiva());


    }
    System.out.println("\n \nAperte enter para continuar");
    leitura.nextLine();
    String teste24 = leitura.nextLine();
  }

}