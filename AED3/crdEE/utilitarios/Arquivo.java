package utilitarios;

import java.io.*;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro> {

  RandomAccessFile arquivo;
  HashExtensivel<ParIdEndereco> indiceDireto;
  Constructor<T> construtor;
  String nomePasta;

  public Arquivo(Constructor<T> c, String nome) throws Exception {
    this.nomePasta = nome;
    this.construtor = c;
    arquivo = new RandomAccessFile(this.nomePasta + "/dados.db", "rw");
    if (arquivo.length() == 0)
      arquivo.writeInt(0);
    indiceDireto = new HashExtensivel<>(ParIdEndereco.class.getConstructor(), 4, this.nomePasta + "/id1.db",
        this.nomePasta + "/id2.db");
  }

  public int create(T objeto) throws Exception {

    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    objeto.setID(ultimoID + 1);
    arquivo.seek(0);
    arquivo.writeInt(objeto.getID());

    arquivo.seek(arquivo.length());
    long endereco = arquivo.getFilePointer();
    byte[] registro = objeto.toByteArray();
    arquivo.writeByte(' ');
    arquivo.writeInt(registro.length);
    arquivo.write(registro);
    indiceDireto.create(new ParIdEndereco(objeto.getID(), endereco));

    return objeto.getID();
  }

  public T read(int id) throws Exception {
    byte[] registro;
    int tamanhoRegistro;
    byte lapide;

    T objeto;
    ParIdEndereco pie = indiceDireto.read(id);
    if (pie != null) {
      arquivo.seek(pie.getEndereco());
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        objeto = this.construtor.newInstance();
        objeto.fromByteArray(registro);
        if (objeto.getID() == id) {
          return objeto;
        }
      }
    }
    return null;
  }

  public boolean update(T objetoNovo) throws Exception {
    long endereco;
    byte[] registroAtual;
    byte[] registroNovo;
    int tamanhoRegistroAtual;
    byte lapide;
    T objetoAtual;

    ParIdEndereco pie = indiceDireto.read(objetoNovo.getID());
    if (pie != null) {
      endereco = pie.getEndereco();
      arquivo.seek(endereco);
      lapide = arquivo.readByte();
      tamanhoRegistroAtual = arquivo.readInt();
      registroAtual = new byte[tamanhoRegistroAtual];
      arquivo.read(registroAtual);
      if (lapide == ' ') {
        objetoAtual = this.construtor.newInstance();
        objetoAtual.fromByteArray(registroAtual);
        if (objetoAtual.getID() == objetoNovo.getID()) {
          registroNovo = objetoNovo.toByteArray();
          if (registroNovo.length < tamanhoRegistroAtual) {
            arquivo.seek(endereco + 5);
            arquivo.write(registroNovo);
          } else {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            arquivo.seek(arquivo.length());
            long novoEndereco = arquivo.getFilePointer();
            arquivo.writeByte(' ');
            arquivo.writeInt(registroNovo.length);
            arquivo.write(registroNovo);
            indiceDireto.update(new ParIdEndereco(objetoNovo.getID(), novoEndereco));
          }
          return true;
        }
      }
    }
    return false;
  }

  public boolean delete(int id) throws Exception {
    long endereco;
    byte[] registro;
    int tamanhoRegistro;
    byte lapide;
    T objeto;

    ParIdEndereco pie = indiceDireto.read(id);
    if (pie != null) {
      endereco = pie.getEndereco();
      arquivo.seek(endereco);
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        objeto = this.construtor.newInstance();
        objeto.fromByteArray(registro);
        if (objeto.getID() == id) {
          arquivo.seek(endereco);
          arquivo.writeByte('*');
          indiceDireto.delete(id);
          return true;
        }
      }
    }
    return false;
  }

  public void close() throws Exception {
    arquivo.close();
    indiceDireto.close();
  }

}