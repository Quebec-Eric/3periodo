import java.io.*;

/*

Programa feito por
Eric Azevedo de oliveira 
git= https://github.com/Quebec-Eric
*/



public class Main {
  public static void main(String[] args) throws Exception {
    // remover em um código real

    Livro l1 = new Livro(-1, "Eu, Robô", "Isaac Asimov", 14.99F);
    Livro l2 = new Livro(-1, "Eu Sou A Lenda", "Richard Matheson", 21.99F);
    Livro l3 = new Livro(-1, "Número Zero", "Umberto Eco", 9021);
    Arquivo<Livro> arqLivros;
    arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
    int id1 = arqLivros.creat(l1);
    l1.setID(id1);
    int id2 = arqLivros.creat(l2);
    l1.setID(id2);
    int id3 = arqLivros.creat(l3);
    l1.setID(id3);

    (new File("dados/livros/arquivo.db")).deleteOnExit();
    (new File("dados/livros.hash_c.db")).deleteOnExit();
    (new File("dados/livros.hash_d.db")).deleteOnExit();
   
    

    System.out.println("\n\nBUSCA POR ID: ");
    System.out.println("\nLIVRO 3:" + arqLivros.read(id3));
    System.out.println("\nLIVRO 2:" + arqLivros.read(id2));
    System.out.println("\nLIVRO 1:" + arqLivros.read(id1));

    System.out.println("Mudando para Autor Maior");
    l2.autor="Richard Matheson Nascimento";
    arqLivros.update(l2);
    System.out.println("\nLIVRO 1:" + arqLivros.read(id2));

    System.out.println("Mudando para Autor Menor");
    l3.autor="Umberto Ec";
    arqLivros.update(l3);
    System.out.println("\nLIVRO 1:" + arqLivros.read(id3));


    System.out.println("Remover");
    boolean saber=arqLivros.remove(id3);
    if(saber){
      System.out.println(true);
    }
    else{
      System.out.println(false);
    }

  }
}
