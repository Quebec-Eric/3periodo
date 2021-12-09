import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import tabelaHex.*;

public class ArquiVote<V extends RegistroVotos> {
  RandomAccessFile arquivo;
  protected int ultimoId = 0;
  protected int contRegistros = 0;
  Constructor<V> construtor;
  final int TAMANHO_CABECALHO = 4;
  ArvoreBMais<ParIntInt> arvoreR1;
  ArvoreBMais<ParIntInt> arvoreR2;
  HashExtensivel<Pidend> indiceDirer;

  public ArquiVote(String nomeArquivo, Constructor<V> c) throws Exception {

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
      arvoreR1 = new ArvoreBMais<>(ParIntInt.class.getConstructor(), 5, "dados/Varvore.db");
      arvoreR2 = new ArvoreBMais<>(ParIntInt.class.getConstructor(), 5, "dados/Voarvore.db");
      
      indiceDirer = new HashExtensivel<>(Pidend.class.getConstructor(), 4, "dados/" + nomeArquivo + ".hash_d.db",
          "dados/" + nomeArquivo + ".hash_c.db");

    }
  }


  

  public int create(V obj) throws Exception {

    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    int proximoID = ultimoID + 1;
    arquivo.seek(0);
    arquivo.writeInt(proximoID);

    arquivo.seek(arquivo.length());

    obj.setIdVoto(proximoID);
    byte[] ba = obj.toByteArray();
    long endereco = arquivo.getFilePointer();
    arquivo.writeByte(' ');
    arquivo.writeInt(ba.length);
    arquivo.write(ba);
    arvoreR1.create(new ParIntInt(obj.getIdVotadoPergunta(), obj.getIdVoto()));
    arvoreR2.create(new ParIntInt(obj.getIdUsuario(), obj.getIdVotadoPergunta()));

    indiceDirer.create(new Pidend(obj.getIdVoto(), endereco));

    return proximoID;
  }


  public int createR(V obj) throws Exception {

    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    int proximoID = ultimoID + 1;
    arquivo.seek(0);
    arquivo.writeInt(proximoID);

    arquivo.seek(arquivo.length());

    obj.setIdVoto(proximoID);
    byte[] ba = obj.toByteArray();
    long endereco = arquivo.getFilePointer();
    arquivo.writeByte(' ');
    arquivo.writeInt(ba.length);
    arquivo.write(ba);
    

    indiceDirer.create(new Pidend(obj.getIdVoto(), endereco));

    return proximoID;
  }

  public boolean desobrirSejavotou(int id) throws Exception {

    ArrayList<ParIntInt> lista2 = arvoreR2.read(new ParIntInt(id, -1));

    if(lista2.size()==0){
      return true;
    }
    return false;
  }

  public V read(int idProcurado) throws Exception {

    byte[] registro;
    int tamanhoRegistro;
    byte lapide;

    V objeto;
    Pidend pie = indiceDirer.read(idProcurado);
    if (pie != null) {
      arquivo.seek(pie.getEndereco());
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        objeto = this.construtor.newInstance();
        objeto.fromByteArray(registro);
        if (objeto.getIdVoto() == idProcurado) {
          return objeto;
        }
      }
    }
    return null;
  }

  public String readVVV(int id) throws Exception {

   // int tam = 0;
    String tudosOsids = "";
    //Pergunta perg = new Pergunta();
    // System.out.print(id +"ididi");
    ArrayList<ParIntInt> lista1 = arvoreR1.read(new ParIntInt(id, -1));
    // System.out.print(lista1.toString() + " ola ");
    int i = 0;
    for (i = 0; i < lista1.size(); i++) {

      tudosOsids += lista1.get(i);
    }
    tudosOsids += "|";
    tudosOsids += i;

    return tudosOsids;
  }

  public boolean javotoOuN(int idUser) throws Exception {

    ArrayList<ParIntInt> lista = arvoreR2.read(new ParIntInt(idUser, -1));

    if (lista.size() > 0) {
      return true;
    }
    return false;
  }

}
