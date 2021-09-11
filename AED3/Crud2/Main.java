import java.io.*;
import java.util.Scanner;

/*

Programa feito por
Eric Azevedo de oliveira 
git= https://github.com/Quebec-Eric
*/

public class Main {
  public static Arquivo<Livro> arqLivros;
  public static Scanner ler = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // remover em um código real
    (new File("dados/livros/arquivo.db")).delete();
    (new File("dados/livros.hash_c.db")).delete();
    (new File("dados/livros.hash_d.db")).delete();
    // Arquivo<Livro> arqLivros;
    arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
    //Scanner ler = new Scanner(System.in);
    int oqFazer = 0;

    do {
      System.out.println("\nCriar Novo Livro== 1 ");
      System.out.println("Ler Livro       == 2 ");
      System.out.println("Remover Livro   == 3 ");
      System.out.println("Update livro    == 4 ");
      System.out.println("Sair            == 5 ");
      oqFazer = Integer.valueOf(ler.nextLine());
      switch (oqFazer) {
        case 1:
          novoLivro(arqLivros);
          break;
        case 2:
          lerLivreo(arqLivros);
          break;

        case 3:
         removerLivreo(arqLivros);
          break;

        case 4:
        updateLivreo(arqLivros);
       
          break;
        default:
         
      }
     
    } while (oqFazer != 5);
  ler.close();

  }

  public static void novoLivro(Arquivo<Livro> arqLivros) throws Exception {
    // Arquivo<Livro> arqLivros;

    Livro l1 = new Livro(-1, "Eu, Robô", "Isaac Asimov", 14.99F);
    Livro l2 = new Livro(-1, "Eu Sou A Lenda", "Richard Matheson", 21.99F);
    Livro l3 = new Livro(-1, "Número Zero", "Umberto Eco", 9021);
    int id1 = arqLivros.creat(l1);
    l1.setID(id1);
    int id2 = arqLivros.creat(l2);
    l1.setID(id2);
    int id3 = arqLivros.creat(l3);
    l1.setID(id3);
    System.out.println("Id Criados==: " + id1 + " " + id2 + " " + id3);
  }

  public static void lerLivreo(Arquivo<Livro> arqLivros) throws Exception {
   // Scanner ler = new Scanner(System.in);
    System.out.println("\nBUSCA POR ID: ");
    System.out.println(arqLivros.read(Integer.valueOf(ler.nextLine())));
    //ler.close();
  }

  public static void updateLivreo(Arquivo<Livro> arqLivros) throws Exception {

    Livro l2 = new Livro(-1, "Eu Sou A Lenda", "Richard Matheson", 21.99F);
    System.out.println("\n ID update: ");
    System.out.println("Mudando para Autor Maior");
    l2.autor = "Richard Matheson Nascimento";
    arqLivros.update(l2);
    System.out.println("\nLIVRO 1:" + arqLivros.read(2));


  }

  public static void removerLivreo(Arquivo<Livro> arqLivros) throws Exception {
    System.out.println("Remover");
  //  Scanner ler = new Scanner(System.in);
    boolean saber = arqLivros.remove(Integer.valueOf(ler.nextLine()));
    if (saber) {
      System.out.println(true);
    } else {
      System.out.println(false);
    }
   // ler.close();
  }
    
}
