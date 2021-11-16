package entidades.usuarios;

import java.util.Scanner;

public class InterfacesUsuario {

  private static Scanner console = new Scanner(System.in);

  public static void mostraUsuario(Usuario u) {
    System.out.println("\nID....: " + u.idUsuario + "\nNome..: " + u.nome + "\nE-mail: " + u.email);
  }

  public static String leEmail() throws Exception {
    return leEmail(true);
  }

  public static String leEmail(boolean obrigatorio) throws Exception {
    String email = "";
    System.out.println("\nInforme o e-mail" + (obrigatorio ? " ou FIM para cancelar" : ""));
    do {
      System.out.print("E-mail: ");
      email = console.nextLine();
      if (email.equalsIgnoreCase("FIM"))
        throw new Exception("Operação cancelada!");
      if (!email.contains("@")) {
        System.out.println("Email inválido! Tente novamente.");
        email = "";
        continue;
      }
      String partes[] = email.split("@");
      if (partes[0].length() < 1 || partes[1].length() < 4 || (!partes[1].contains("."))) {
        System.out.println("Email inválido! Tente novamente.");
        email = "";
      }
    } while (obrigatorio && email.length() == 0);
    return email;
  }

  public static String leNome() throws Exception {
    return leNome(true);
  }

  public static String leNome(boolean obrigatorio) throws Exception {
    String nome = "";
    System.out.println("\nInforme o nome" + (obrigatorio ? " ou FIM para cancelar" : ""));
    do {
      System.out.print("Nome: ");
      nome = console.nextLine();
      if (nome.equalsIgnoreCase("FIM"))
        throw new Exception("Operação cancelada!");

    } while (obrigatorio && nome.length() == 0);
    return nome;
  }

  public static int leSenha() throws Exception {
    return leSenha(true);
  }

  public static int leSenha(boolean obrigatorio) throws Exception {
    String senha = "";
    System.out.println("\nInforme senha" + (obrigatorio ? " ou FIM para cancelar" : ""));
    do {
      System.out.print("Senha: ");
      senha = console.nextLine();
      if (senha.equalsIgnoreCase("FIM"))
        throw new Exception("Operação cancelada!");
    } while (obrigatorio && senha.length() == 0);
    return senha.hashCode();
  }
}
