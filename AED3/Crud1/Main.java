/*
Programa feito por
Eric Azevedo de oliveira 
git= https://github.com/Quebec-Eric
*/

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    // remover em um código real
    Scanner ler = new Scanner(System.in);
    System.out.println("*************************************");
    System.out.println("* Criar arquivo de Clientes     == 1*");
    System.out.println("* Criar arquivo de livro        == 2*");
    System.out.println("* Criar arquivo de Supermercado == 3*");
    System.out.println("*************************************");
    int x = ler.nextInt();
    switch (x) {
      case 1:
        fazerCliente();
        ler.close();
        break;
      case 2:
        fazerLivro();
        break;
      case 3:
        Supermercado();
        break;

      default:
        // code block
    }
    ler.close();

  }

  public static void fazerCliente() throws Exception {
    Scanner ler = new Scanner(System.in);
    System.out.println("Bem vindo aos Clientes");
    System.out.println("/////////////////////////////////////");
    System.out.println("/ Criar novo Clientes           == 1/");
    System.out.println("/ Ler Cliente                   == 2/");
    System.out.println("/ Remover Cliente               == 3/");
    System.out.println("/ Atualizar Cliente             == 4/");
    System.out.println("/ Ler Todos Cliente             == 5/");
    System.out.println("/////////////////////////////////////");
    int x = ler.nextInt();
    switch (x) {
      case 1:
        crieteCliente();
        break;
      case 2:
        lerCliente();
        break;
      case 3:
        excluirClientes();
        break;
      case 4:
        AtualizarAcliente();
        break;
      case 5:
        lerTodosClientes();
        break;

      default:
        // code block
    }
    ler.close();

  }

  public static void fazerLivro() {
    Scanner ler = new Scanner(System.in);
    System.out.println("Bem vindo aos Livro");
    System.out.println("/////////////////////////////////////");
    System.out.println("/ Criar novo Livro           == 1/");
    System.out.println("/ Ler livro                   == 2/");
    System.out.println("/ Remover Livro               == 3/");
    System.out.println("/ Atualizar Livro             == 4/");
    System.out.println("/////////////////////////////////////");
    int x = ler.nextInt();
    switch (x) {
      case 1:

        break;
      case 2:
        // code block
        break;
      case 3:
        // code block
        break;

      default:
        // code block
    }
    ler.close();
  }

  public static void Supermercado() {
    Scanner ler = new Scanner(System.in);
    System.out.println("Bem vindo ao Supermercado");
    System.out.println("/////////////////////////////////////");
    System.out.println("/ Criar novo Produto            == 1/");
    System.out.println("/ Ler Produto                   == 2/");
    System.out.println("/ Remover Produto               == 3/");
    System.out.println("/ Atualizar Produto             == 4/");
    System.out.println("/////////////////////////////////////");
    int x = ler.nextInt();
    switch (x) {
      case 1:

        break;
      case 2:
        // code block
        break;
      case 3:
        // code block
        break;

      default:
        // code block
    }
    ler.close();
  }

  // parte de criar as coisas

  public static void crieteCliente() throws Exception {
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    try {
      Cliente c = new Cliente();
      System.out.print("Nome = ");
      System.out.println();
      c.setNome(ler.nextLine());
      System.out.print("Email = ");
      System.out.println();
      c.setEmail(ler.nextLine());
      System.out.print("Idade = ");
      System.out.println();
      c.setIdade(ler.nextInt());
      // ESCRITA
      arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
      arqClientes.create(c);
      ler.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void AtualizarAcliente() throws Exception {
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
    System.out.println("qual o seu id Porfavor?");
    int receberId = ler.nextInt();
    Cliente c3 = arqClientes.read(receberId);
    System.out.println("Oque gostaria de atualizar");
    System.out.println("Nome =1, Email=2 , idade =3");
    int receberResposta = ler.nextInt();

    if (receberResposta == 1) {
      System.out.println("novo nome");
      String rec = ler.nextLine();
      rec = ler.nextLine();
      c3.setNome(rec);
      if (arqClientes.atualizarC(c3)) {
        System.out.println("atualizado ");
        System.out.println(arqClientes.read(receberId).toString());
      }

      else {
        System.out.println("nao esta atualizado ");
      }

    } else if (receberResposta == 2) {
      System.out.println("novo email");
      String rec = ler.nextLine();
      rec = ler.nextLine();
      c3.setEmail(rec);
      if (arqClientes.atualizarC(c3)) {
        System.out.println("atualizado ");
        System.out.println(arqClientes.read(receberId).toString());
      }

      else {
        System.out.println("nao esta atualizado ");
      }

    } else {

      System.out.println("nova idade");
      int idade = ler.nextInt();
      if (c3.getIdade() != idade) {
        c3.setIdade(idade);
        if (arqClientes.atualizarC(c3)) {
          System.out.println("atualizado ");
          System.out.println(arqClientes.read(receberId).toString());

        }
      }
    }

    ler.close();
  }

  public static void lerCliente() throws Exception {
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
    System.out.println("Qual id gostaria de ler");
    Cliente c3 = arqClientes.read(ler.nextInt());
    if (c3 == null) {
      System.out.println("Registro nao encontrado");
    } else {
      System.out.println(c3);
    }

    ler.close();
  }

  public static void lerTodosClientes() throws Exception {
    Arquivo<Cliente> arqClientes;
    arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
    int saberTamanho = arqClientes.saberQuantidadet();
    for (int i = 0; i <= saberTamanho; i++) {
      Cliente c3 = arqClientes.read(i);
      if (c3 != null) {
        System.out.println(c3);
      }
    }
  }

  public static void excluirClientes() throws Exception {
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
    System.out.println("Qual id gostaria de apagar");
    boolean saber = arqClientes.excluir(ler.nextInt());
    if (saber) {
      System.out.println("Removido com sucesso");
    } else {
      System.out.println("Deu ruim para remover");
    }
    ler.close();
  }
}