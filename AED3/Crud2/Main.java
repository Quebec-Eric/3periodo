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
        //crieteCliente();
        break;
      case 2:
        //lerCliente();
        break;
      case 3:
        //excluirClientes();
        break;
      case 4:
        //AtualizarAcliente();
        break;
      case 5:
        //lerTodosClientes();
        break;

      default:
        // code block
    }
    ler.close();

  }

  public static void fazerLivro()throws Exception {
    Scanner ler = new Scanner(System.in);
    System.out.println("Bem vindo aos Livro");
    System.out.println("/////////////////////////////////////");
    System.out.println("/ Criar novo Livro              == 1/");
    System.out.println("/ Ler livro                     == 2/");
    System.out.println("/ Remover Livro                 == 3/");
    System.out.println("/ Atualizar Livro               == 4/");
    System.out.println("/////////////////////////////////////");
    int x = ler.nextInt();
    switch (x) {
      case 1:
     // criarLivro();
        break;
      case 2:
      //lerCLivro();
        break;
      case 3:
      //excluirLivros();
        break;

        case 4:
        //atualizarLiVro();
      break;
      default:
        // code block
    }
    ler.close();
  }
}

 