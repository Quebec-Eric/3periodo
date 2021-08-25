/*
Programa feito por
Eric Azevedo de oliveira 
git= https://github.com/Quebec-Eric
*/

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception{
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

  public static void fazerCliente() throws Exception{
    Scanner ler = new Scanner(System.in);
    System.out.println("Bem vindo aos Clientes");
    System.out.println("/////////////////////////////////////");
    System.out.println("/ Criar novo Clientes           == 1/");
    System.out.println("/ Ler Cliente                   == 2/");
    System.out.println("/ Remover Cliente               == 3/");
    System.out.println("/ Atualizar Cliente             == 4/");
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
        // code block
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

  public static void crieteCliente()throws Exception {
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    try {
      Cliente c = new Cliente();
      System.out.println("Nome = ");
      c.setNome(ler.nextLine());
      System.out.println("Email = ");
      c.setEmail(ler.nextLine());
      System.out.println("Idade = ");
      c.setIdade(ler.nextInt());
      // ESCRITA
      arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
      arqClientes.create(c);
      ler.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void lerCliente()throws Exception{
    Scanner ler = new Scanner(System.in);
    Arquivo<Cliente> arqClientes;
    arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());
    System.out.println("Qual id gostaria de ler");
    Cliente c3 = arqClientes.read(ler.nextInt());
    if(c3==null){
      System.out.println("Registro nao encontrado");  
    }
    else{
      System.out.println(c3);
    }

    ler.close();
  }
}