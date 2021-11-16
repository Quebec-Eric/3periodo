package entidades.perguntas;

import java.util.Scanner;
import logicas.*;

public class InterfacesPergunta {

  private static Scanner console = new Scanner(System.in);

  public static void mostraPergunta(Pergunta p) {
    mostraPergunta(p, "");
  }

  public static void mostraPergunta(Pergunta p, String nomeUsuario) {
    System.out.println(p.getPergunta());
    if (nomeUsuario.length() > 0)
      System.out.println("Autor.........: " + nomeUsuario);
    System.out.println("Criação.......: " + Ajudante.LongDateToString(p.getCriacao()));
    System.out.println("Palavras chave: " + p.getPalavrasChave());
    System.out.println("Nota..........: " + p.getNota());
    if (!p.getAtiva())
      System.out.println("(ARQUIVADA)");
  }

  public static void mostraPerguntaSelecionada(Pergunta p) {
    mostraPerguntaSelecionada(p, "");
  }

  public static void mostraPerguntaSelecionada(Pergunta p, String nomeUsuario) {
    String barra = "-".repeat(p.getPergunta().length());
    System.out.println("\n+-" + barra + "-+");
    System.out.println("| " + p.getPergunta() + " |");
    System.out.println("+-" + barra + "-+");
    if (nomeUsuario.length() > 0)
      System.out.println("Autor.........: " + nomeUsuario);
    System.out.println("Criação.......: " + Ajudante.LongDateToString(p.getCriacao()));
    System.out.println("Palavras chave: " + p.getPalavrasChave());
    System.out.println("Nota..........: " + p.getNota());
    if (!p.getAtiva())
      System.out.println("(ARQUIVADA)");
  }

  public static String lePergunta() throws Exception {
    return lePergunta(true);
  }

  public static String lePergunta(boolean obrigatorio) throws Exception {
    String pergunta = "";
    System.out.println("\nInforme a pergunta" + (obrigatorio ? " ou FIM para cancelar" : ""));
    do {
      System.out.print("Pergunta: ");
      pergunta = console.nextLine();
      if (pergunta.equalsIgnoreCase("FIM"))
        throw new Exception("Operação cancelada!");
    } while (obrigatorio && pergunta.length() == 0);
    return pergunta;
  }

  public static String lePalavrasChave() throws Exception {
    return lePalavrasChave(true);
  }

  public static String lePalavrasChave(boolean obrigatorio) throws Exception {
    String palavras = "";
    System.out.println("\nInforme a lista de palavras chave separadas por ponto-e-vírgula"
        + (obrigatorio ? "ou FIM para cancelar" : ""));
    System.out.println("Ex.: Brasil;eleições;presidente");
    do {
      System.out.print("Palavras chave: ");
      palavras = console.nextLine();
      if (palavras.equalsIgnoreCase("FIM"))
        throw new Exception("Operação cancelada!");
    } while (obrigatorio && palavras.length() == 0);
    return palavras;
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
