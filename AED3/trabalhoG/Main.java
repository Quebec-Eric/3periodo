import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
  public static Arquivo<Usuario> arqUser;
  public static ArquivoU<Pergunta> perguntas;
  public static RespostasUsar<Respostas> respostas;
  public static ArquiVote<Votos> voto;
  public static int idglobal;
  public static ArrayList<Integer> repostaId = new ArrayList();
  public static Scanner leitura = new Scanner(System.in);
  // public static Scanner let2 = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // remover em um código real

    (new File("dados/Usuario/arquivo.db")).delete();
    (new File("dados/Usuario.hash_c.db")).delete();
    (new File("dados/Usuario.hash_d.db")).delete();
    (new File("dados/Pergunta/arquivo.db")).delete();
    (new File("dados/Respostas/arquivo.db")).delete();
    (new File("dados/arvore.db")).delete();
    (new File("dados/bloco.listainv.bd")).delete();
    (new File("dados/dicionario.listainv.db")).delete();
    // (new File("dados/Puarvore.db")).delete();
    // (new File("dados/Rarvore.db")).delete();

    // Arquivo<Livro> arqUser;
    arqUser = new Arquivo<>("Usuario", Usuario.class.getConstructor());
    perguntas = new ArquivoU<>("Pergunta", Pergunta.class.getConstructor());
    respostas = new RespostasUsar<>("Respostas", Respostas.class.getConstructor());
    voto = new ArquiVote<>("Votos", Votos.class.getConstructor());

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
          String pegar = acessoUsuario(arqUser, perguntas);
          if (pegar.charAt(1) == '0') {
            int pegarIdUsuario = pegar.charAt(0) - '0';
            do {
              // limpar();
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
                              if (status == 1) {
                                perguntas.arquivarPergunta(ids, true);
                              } else if (status == 2) {
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
                  int op4 = 1;

                  while (op4 != 0) {

                    System.out.println("              Buscar");
                    System.out.println("-------------------------------");
                    System.out.println("Busque as perguntas por palavra chave separadas por ponto e vírgula");
                    System.out.println("Ex: política;Brasil;eleições");
                    System.out.print("\n palavras chave: ");
                    leitura.nextLine();
                    String palavrasChaves = leitura.nextLine();
                    palavrasChaves = perguntas.removerAcentos(palavrasChaves);
                    String[] palavrasSeparadas = perguntas.separadorDePontoEVirgula(palavrasChaves);
                    ArrayList<Integer> idsT = new ArrayList<>();
                    idsT = perguntas.pegarIdPalavrachave(palavrasSeparadas);
                    // System.out.println(idsT.toString());
                    int dado = 1;
                    Pergunta[] pp = ordernarMaiornota(idsT);
                    for (int i = 0; i < pp.length; i++) {
                      System.out.print(dado + ")");
                      pp[i].toString();
                      dado++;
                    }
                    System.out.println("Que pergunta deseja visualizar (0 para voltar)?");
                    op4 = leitura.nextInt();

                    if (op4 > 0) {
                      fazerRespostas(pp[op4 - 1].getIdP());
                    }
                  }
                  op4 = 0;

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
          Teste(arqUser);
          break;

        default:
          System.out.print("Opcao Invalida");
          System.out.println("Aperte enter para continuar");
          String teste23 = leitura.nextLine();
          break;
      }

    } while (oqfazer != 0);

  }

  public static void fazerRespostas(int id) throws Exception {

    int dod = 0;
    do {
      Pergunta p = perguntas.read1(id);
      p.toString(null);
      String rec = respostas.readRRRR(id);
      int local = rec.indexOf("|");
      int tamanhoPetguntas = rec.charAt(local + 1) - '0';
      String idsTT = sabertodosID(rec);
      System.out.println("\n");

      if (idsTT.length() > 1) {
        for (int i = 1; i <= tamanhoPetguntas; i++) {

          System.out.println(respostas.read(idsTT.charAt(i - 1) - '0'));

          // System.out.println(idsTT.charAt(i));
        }

      } else if (idsTT.length() > 0) {
        System.out.println(respostas.read(idsTT.charAt(0) - '0'));
      }

      System.out.println("1) Responder");
      System.out.println("2) Avaliar a pergunta");
      System.out.println("3) Avaliar uma resposta");
      System.out.println("0) Voltar");
      dod = leitura.nextInt();

      switch (dod) {
        case 1:

          Respostas r = new Respostas();
          String eric = pegarResp();
          r.setResposta(eric);
          r.setUsuario(arqUser.readNomeUser(idglobal).getNome());
          r.setIdUsuario(idglobal);
          r.setIdPergunta(id);
          respostas.create(r);

          System.out.println("\n\n" + r.toString());
          break;
        case 2:
          System.out.println("Avaliar a pergunta");
          if(voto.desobrirSejavotou(idglobal)){

         
          Boolean saberV = false;
          int valorV = 0;
          System.out.println("\nVote 1 para boa e 0 para ruim");
          short notaV = p.getNota();
          valorV = leitura.nextInt();
          if (valorV == 1) {
            saberV = true;

            notaV++;
            p.setNota(notaV);
          } else if (notaV > 0) {
            notaV--;
            p.setNota(notaV);
          }

          Votos tt = new Votos();
          tt.setIdUsuario(idglobal);
          tt.setIdVotadoPergunta(id);
          tt.setVoto(saberV);
          voto.create(tt);
          // mudarvalorNota(p, tt);

          if (perguntas.atualizarC(p)) {
            System.out.println("\nVoto creditaco");
          } else {
            System.out.println("\nErro ao creditar o voto");
          }
        
        }

        else {
          System.out.println("Voce ja avaliou a pergunta");
        }
        
          break;
        case 3:
          System.out.println("Avaliar uma resposta");
          if(!repostaId.contains(idglobal)){
          System.out.println("\n");
          int vetor = 1;
          if (idsTT.length() > 1) {
            for (int i = 1; i <= tamanhoPetguntas; i++) {

              System.out.println(vetor + ")\n" + respostas.read(idsTT.charAt(i - 1) - '0'));
              vetor++;
              // System.out.println(idsTT.charAt(i));
            }

          } else if (idsTT.length() > 0) {
            System.out.println(vetor + ")\n" + respostas.read(idsTT.charAt(0) - '0'));
          }
          System.out.println("\nQual resposta gostaria de avaliar?");
          int receberesposta = leitura.nextInt();
          Boolean saberR = false;
          int valorR = 0;
          Respostas receber = respostas.read(idsTT.charAt(receberesposta - vetor) - '0');
          System.out.println(receber.toString());
          System.out.println("\nVote 1 para boa e 0 para ruim");
          valorR = leitura.nextInt();
          short notaR = receber.getNota();
          if (valorR == 1) {
            saberR = true;

            notaR++;
            receber.setNota(notaR);
          } else if (notaR > 0) {
            notaR--;
            receber.setNota(notaR);
          }

          if (respostas.update(receber)) {
            System.out.println("\nVoto creditaco");
          } else {
            System.out.println("\nErro ao creditar o voto");
          }
          repostaId.add(idglobal);
        }
        else{
          System.out.println("Voce ja avaliou a Resposta");
        }
          break;
        default:
          break;
      }
    } while (dod != 0);
  }


  public static String pegarResp() throws Exception {
    String resp = "";
    System.out.println("Qual sua resposta? ");
    leitura.nextLine();
    resp = leitura.nextLine();
    return resp;

  }

  public static boolean saberSejafoiVotado(int idPergunta) throws Exception {
    
    Votos saber = voto.read(idPergunta);
    System.out.println(saber.toString());
    /*
    if (saber.getIdUsuario() == idglobal) {
      return false;
    }
    */

    return true;
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
      if (!arqUser.saberLocal(email))// verificar cadastro -- ak caso n tiver cadastro CONFERIR SE JA EXISTE
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
            int i1 = arqUser.creat(u1);
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
    if (arqUser.saberLocal(email)) {
      System.out.print("Digite a Senha : ");
      senha = leitura.nextLine();
      if (arqUser.sabersenha(email, senha)) {
        System.out.println("Login realizado, redirecionando...");
        tudo += arqUser.read(email, senha).getID();
        idglobal = arqUser.read(email, senha).getID();
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
      perguntas.read1(idsTT.charAt(i - 1) - '0').toString();

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

      Pergunta ppp = perguntas.read1(idsTT.charAt(i - 1) - '0');
      ppp.toString();
      System.out.println("Esta ativada == " + ppp.getAtiva());

    }
    System.out.println("\n \nAperte enter para continuar");
    leitura.nextLine();
    String teste24 = leitura.nextLine();
  }

  public static Pergunta[] ordernarMaiornota(ArrayList idsT) throws Exception {
    short teste = 0;
    Pergunta[] pp = new Pergunta[idsT.size()];
    for (int i = 0; i < idsT.size(); i++) {
      int t = (int) idsT.get(i);
      pp[i] = perguntas.read1(t);

    }
    Usuario er;
    pp = fazermudancas(pp);
    return pp;
  }

  public static Pergunta[] fazermudancas(Pergunta[] pp) {

    for (int i = pp.length - 1; i > 0; i--) {
      for (int z = 0; z < i; z++) {

        if (pp[z].getNota() < pp[z + 1].getNota()) {
          swap(z, z + 1, pp);
        }
      }
    }

    return pp;
  }

  public static void swap(int primeiraP, int segundaP, Pergunta[] pp) {
    Pergunta receber;
    receber = pp[primeiraP];
    pp[primeiraP] = pp[segundaP];
    pp[segundaP] = receber;
  }
}