package logicas;

//import java.util.ArrayList;
import java.util.Scanner;
import entidades.perguntas.*;

public class PetguntasUser {
  public static Scanner leitura = new Scanner(System.in);
  private static ArquivoPergunta arqPerguntas;

  public static void menu() throws Exception {

    arqPerguntas = new ArquivoPergunta("dados/perguntas");

    int opcao;
    do {
      System.out.println("\n\nPERGUNTAS 1.0");
      System.out.println("=============");
      System.out.println("\nINÍCIO > MINHA ÁREA > MINHAS PERGUNTAS");
      System.out.println("\n1) Listar");
      System.out.println("2) Incluir");
      System.out.println("3) Alterar");
      System.out.println("4) Arquivar");
      System.out.println("\n0) Retornar");

      System.out.print("\nOpcao: ");
      try {
        opcao = Integer.valueOf(leitura.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
      case 1:
        listarMinhasPerguntas();
        break;
      case 2:
        incluirMinhaPergunta();
        break;
      case 3:
        alterarMinhaPergunta();
        break;
      case 4:
        arquivarMinhaPergunta();
        break;
      case 0:
        break;
      default:
        System.out.println("Opção inválida");
      }

    } while (opcao != 0);

    arqPerguntas.close();

  }

  // --------------------------------------------------------
  // LISTAR MINHAS PERGUNTAS
  // --------------------------------------------------------
  public static void listarMinhasPerguntas() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Ajudante.usuarioGlobal());
    int i = 0;
    if (perguntas.length == 0) {
      Ajudante.pausa("Nenhuma pergunta encontrada!");
      return;
    }
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    Ajudante.pausa("");
    return;
  }

  // --------------------------------------------------------
  // INCLUIR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void incluirMinhaPergunta() throws Exception {
    System.out.println("\nNOVA PERGUNTA");
    String pergunta, palavrasChave;
    try {
      pergunta = InterfacesPergunta.lePergunta();
      palavrasChave = InterfacesPergunta.lePalavrasChave();
    } catch (Exception e) {
      Ajudante.pausa("Operação cancelada");
      return;
    }

    Pergunta p = new Pergunta(Ajudante.usuarioGlobal(), pergunta, palavrasChave);

    System.out.print("\nConfirma inclusão? (S/N) ");
    char confirma = leitura.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.create(p);
      Ajudante.pausa("Pergunta cadastrada!");
    } else {
      Ajudante.pausa("Inclusão cancelada!");
    }
    return;

  }

  // --------------------------------------------------------
  // ALTERAR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void alterarMinhaPergunta() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Ajudante.usuarioGlobal(), true);
    if (perguntas.length == 0) {
      Ajudante.pausa("Nenhuma pergunta encontrada!");
      return;
    }
    int i = 0;
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    System.out.print("\nSelecione a pergunta que deseja alterar: ");
    String sel = leitura.nextLine();
    if (sel.compareTo("") == 0)
      return;
    int nPergunta = Integer.valueOf(sel);
    if (nPergunta <= 0 || nPergunta > perguntas.length) {
      return;
    }

    Pergunta p = perguntas[nPergunta - 1];
    InterfacesPergunta.mostraPerguntaSelecionada(p);

    String pergunta = "", palavrasChave = "";
    System.out.println("\nNOVOS DADOS (deixe em branco para não mudar)");

    pergunta = InterfacesPergunta.lePergunta(false);
    palavrasChave = InterfacesPergunta.lePalavrasChave(false);
    if (pergunta.length() == 0 && palavrasChave.length() == 0) {
      Ajudante.pausa("Nenhuma alteração realizada");
      return;
    }

    if (pergunta.length() > 0)
      p.setPergunta(pergunta);
    if (palavrasChave.length() > 0)
      p.setPalavrasChave(palavrasChave);

    System.out.print("\nConfirma alteração? (S/N) ");
    char confirma = leitura.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.update(p);
      Ajudante.pausa("Pergunta alterada!");
    } else {
      Ajudante.pausa("Alteração cancelada!");
    }
    return;
  }

  // --------------------------------------------------------
  // ARQUIVAR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void arquivarMinhaPergunta() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Ajudante.usuarioGlobal(), true);
    int i = 0;
    if (perguntas.length == 0) {
      Ajudante.pausa("Nenhuma pergunta encontrada!");
      return;
    }
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    System.out.print("\nSelecione a pergunta que deseja arquivar: ");
    String sel = leitura.nextLine();
    if (sel.compareTo("") == 0)
      return;
    int nPergunta = Integer.valueOf(sel);
    if (nPergunta <= 0 || nPergunta > perguntas.length) {
      return;
    }

    Pergunta p = perguntas[nPergunta - 1];
    InterfacesPergunta.mostraPerguntaSelecionada(p);

    p.setAtiva(false);

    System.out.print("\nConfirma arquivamento? (S/N) ");
    char confirma = leitura.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.update(p);
      Ajudante.pausa("Pergunta arquivada!");
    } else {
      Ajudante.pausa("Arquivamento cancelado!");
    }
    return;
  }


}
