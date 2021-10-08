
import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;

import tabelaHex.*;

public class Arquivo<T extends Registro> {

  RandomAccessFile arquivo;
  HashExtensivel<ParIDEndereco> indiceDireto;
  Constructor<T> construtor;
  //ArvoreBMais<ParIntInt> arvore;
  RandomAccessFile pergunta;
  Pergunta pqTeste;


  public Arquivo(String nomeArquivo, Constructor<T> c) throws Exception {
    File f = new File("dados");
    if (!f.exists()) {
      f.mkdir();
    }
    f = new File("dados/" + nomeArquivo);
    if (!f.exists()) {
      f.mkdir();
    }
    arquivo = new RandomAccessFile("dados/" + nomeArquivo + "/arquivo.db", "rw");
    
    construtor = c;
    if (arquivo.length() == 0) {
      arquivo.writeInt(0);
      indiceDireto = new HashExtensivel<>(ParIDEndereco.class.getConstructor(), 4,
          "dados/" + nomeArquivo + ".hash_d.db", "dados/" + nomeArquivo + ".hash_c.db");
      
    }
  }

  public int creat(T ob) throws Exception {

    arquivo.seek(0); // colocar o ponteiro na posicao 1 do arquivo
    int idUltimo = arquivo.readInt();// saber qual o ultimo id no arquivo
    ob.setID(idUltimo + 1);// colocar mais 1 id no indice
    arquivo.seek(0);// voltar para a posicao 0
    arquivo.writeInt(ob.getID());

    arquivo.seek(arquivo.length());
    long enderecoArk = arquivo.getFilePointer();
    byte[] registro = ob.toByteArray();
    arquivo.writeByte(' ');
    arquivo.writeInt(registro.length);
    arquivo.write(registro);
    indiceDireto.create(new ParIDEndereco(ob.getHash(), enderecoArk));

    return ob.getID();

  }

  public T read(String email, String senha) throws Exception {
    T ob;
    arquivo.seek(4);// pular as informacoes do cabecalho
    byte[] registro;
    int tamanhoRegistro;
    byte lapide;

    ParIDEndereco indiceId = indiceDireto.read(email.hashCode());

    if (indiceId != null) {

      arquivo.seek(indiceId.getEndereco());
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        ob = construtor.newInstance();
        ob.fromByteArray(registro);
        if (ob.getSenha().equals(senha)) {
          return ob;
        }

      }
    }

    return null;
  }

  public boolean remove(String email) throws Exception {

    byte[] registroRemover;
    int tamanhoRegistro = 0;
    byte lapida;
    T ob;
    ParIDEndereco indiceId = indiceDireto.read(email.hashCode());
    if (indiceId != null) {
      arquivo.seek(indiceId.getEndereco());
      lapida = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registroRemover = new byte[tamanhoRegistro];
      arquivo.read(registroRemover);
      if (lapida == ' ') {
        ob = construtor.newInstance();
        ob.fromByteArray(registroRemover);
        arquivo.seek(indiceId.getEndereco());
        arquivo.writeByte('$');
        indiceDireto.delete(email.hashCode());
        return true;
      }
    }

    return false;
  }

  public boolean sabersenha(String email, String senha) throws Exception {
    T ob;
    arquivo.seek(4);// pular as informacoes do cabecalho
    byte[] registro;
    int tamanhoRegistro;
    byte lapide;
    ParIDEndereco indiceId = indiceDireto.read(email.hashCode());
    if (indiceId != null) {
      arquivo.seek(indiceId.getEndereco());
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        ob = construtor.newInstance();
        ob.fromByteArray(registro);

        if (ob.getSenha().equals(senha)) {
          return true;
        }

      }
    }

    return false;
  }

  public boolean saberLocal(String email) throws Exception {
    ParIDEndereco indiceId = indiceDireto.read(email.hashCode());
    if (indiceId != null) {
      return true;
    }

    return false;
  }

  public boolean perguntaN(String email, String senha) throws Exception {
    T ob = read(email, senha);
    long t = 10/10/2020;
     short g =100; 
    pqTeste = new Pergunta(ob.getID(),2,t,g,"Se 2 + 2 sao 4 , por que o pc ta caro?","caro",true);
    //System.out.println(creatP(ob));


    return true;
  }

  public int creatP( T ob) throws Exception {

    pergunta.seek(0); // colocar o ponteiro na posicao 1 do arquivo
    int idUltimo = pergunta.readInt();// saber qual o ultimo id no arquivo
    ob.setID(idUltimo + 1);// colocar mais 1 id no indice
    pergunta.seek(0);// voltar para a posicao 0
    pergunta.writeInt(ob.getID());

    pergunta.seek(pergunta.length());
    long enderecoArk = pergunta .getFilePointer();
    byte[] registro = ob.toByteArray();
    pergunta.writeByte(' ');
    pergunta.writeInt(registro.length);
    pergunta.write(registro);
   // indiceDireto.create(new ParIDEndereco(ob.getHash(), enderecoArk));

    return ob.getID();

  }
 ///// tirar depois a linha 149 ate 178
  public boolean update(T obN) throws Exception {

    byte[] registroAntigo;
    byte[] registroNovo;
    int tamanhoRegistro;
    byte lapida;
    T obA;
    ParIDEndereco indiceId = indiceDireto.read(obN.getEmail().hashCode());

    if (indiceId != null) {
      arquivo.seek(indiceId.getEndereco());
      lapida = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registroAntigo = new byte[tamanhoRegistro];
      arquivo.read(registroAntigo);
      if (lapida == ' ') {
        obA = construtor.newInstance();
        obA.fromByteArray(registroAntigo);
        registroNovo = obN.toByteArray();
        if (registroAntigo.length > registroNovo.length) {
          arquivo.seek(indiceId.getEndereco() + 5);
          arquivo.write(registroNovo);
          return true;
        } else {
          arquivo.seek(indiceId.getEndereco());
          arquivo.writeByte('$');
          arquivo.seek(arquivo.length());
          long novoLugar = arquivo.getFilePointer();
          arquivo.writeByte(' ');
          arquivo.writeInt(registroNovo.length);
          arquivo.write(registroNovo);
          // indiceDireto.update(new ParIDEndereco(obN.getID(), novoLugar));
          return true;
        }

      }
    }

    return false;
  }

  public boolean trocarSenha(String email, String senha) throws Exception {

    byte[] registroAntigo;
    byte[] registroNovo;
    int tamanhoRegistro;
    byte lapida;
    T ob;
    ParIDEndereco indiceId = indiceDireto.read(email.hashCode());

    arquivo.seek(indiceId.getEndereco());
    lapida = arquivo.readByte();
    tamanhoRegistro = arquivo.readInt();
    registroAntigo = new byte[tamanhoRegistro];
    arquivo.read(registroAntigo);
    if (lapida == ' ') {
      ob = construtor.newInstance();
      ob.fromByteArray(registroAntigo);
      if (ob.getSenha().equals(senha)) {
        System.out.println("Senha igausi");
      } else {

        if (ob.getSenha().length() > senha.length()) {

          arquivo.seek(indiceId.getEndereco() + 5);
          ob.setSenha(senha);
          byte[] registro = ob.toByteArray();
          arquivo.write(registro);
          return true;
        } else {
          arquivo.seek(indiceId.getEndereco());
          arquivo.writeByte('$');
          arquivo.seek(arquivo.length());
          long novoLugar = arquivo.getFilePointer();
          ob.setSenha(senha);
          byte[] registro = ob.toByteArray();
          arquivo.write(registro);
          arquivo.writeByte(' ');
          arquivo.writeInt(registro.length);
          arquivo.write(registro);

          indiceDireto.update(new ParIDEndereco(ob.getHash(), novoLugar));
          return true;
        }

      }

    }

    return false;

  }

}
