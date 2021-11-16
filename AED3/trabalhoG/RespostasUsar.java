
import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import java.text.Normalizer;
import java.util.ArrayList;

import tabelaHex.*;

public class RespostasUsar<T extends PerguntaRegistro> {

  RandomAccessFile arquivo;
  protected int ultimoId = 0;
  protected int contRegistros = 0;
  Constructor<T> construtor;
  final int TAMANHO_CABECALHO = 4;
  ArvoreBMais<ParIntInt> arvoreR1;
  ArvoreBMais<ParIntInt> arvoreR2;
  HashExtensivel<Pidend> indiceDirer;

  public RespostasUsar(String nomeArquivo, Constructor<T> c) throws Exception {

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
      arvoreR1 = new ArvoreBMais<>(ParIntInt.class.getConstructor(), 5, "dados/Rarvore.db");
      arvoreR2 = new ArvoreBMais<>(ParIntInt.class.getConstructor(), 5, "dados/Puarvore.db");
      indiceDirer = new HashExtensivel<>(Pidend.class.getConstructor(), 4, "dados/" + nomeArquivo + ".hash_d.db",
          "dados/" + nomeArquivo + ".hash_c.db");

    }
  }

  public int create(T obj) throws Exception {

    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    int proximoID = ultimoID + 1;
    arquivo.seek(0);
    arquivo.writeInt(proximoID);

    arquivo.seek(arquivo.length());

    obj.setIdResposta(proximoID);
    byte[] ba = obj.toByteArray();
    long endereco = arquivo.getFilePointer();
    arquivo.writeByte(' ');
    arquivo.writeInt(ba.length);
    arquivo.write(ba);
    arvoreR1.create(new ParIntInt(obj.getIdPerguntas(), obj.getIdResposta()));
    arvoreR2.create(new ParIntInt(obj.getIdPerguntas(), obj.getIdResposta()));

    indiceDirer.create(new Pidend(obj.getIdResposta(), endereco));

    return proximoID;
  }

  public String readRRRR(int id) throws Exception {

    int tam = 0;
    String tudosOsids = "";
    Pergunta perg = new Pergunta();
    // System.out.print(id +"ididi");
    ArrayList<ParIntInt> lista1 = arvoreR1.read(new ParIntInt(id, -1));
    // System.out.print(lista1.toString() + " ola ");
    int i = 0;
    for (i = 0; i < lista1.size(); i++) {

      tudosOsids += lista1.get(i);
    }
    tudosOsids += "|";
    tudosOsids += i;

    // byte[] ba = new byte[0];

    return tudosOsids;
  }

  public T read(int idProcurado) throws Exception {// ler id especifico

    byte[] registro;
    int tamanhoRegistro;
    byte lapide;

    T objeto;
    Pidend  pie = indiceDirer.read(idProcurado);
    if (pie != null) {
      arquivo.seek(pie.getEndereco());
      lapide = arquivo.readByte();
      tamanhoRegistro = arquivo.readInt();
      registro = new byte[tamanhoRegistro];
      arquivo.read(registro);
      if (lapide == ' ') {
        objeto = this.construtor.newInstance();
        objeto.fromByteArray(registro);
        if (objeto.getIdResposta() == idProcurado) {
          return objeto;
        }
      }
    }
    return null;
  }

  public int saberQuantidadet() throws Exception {
    arquivo.seek(0);
    int quantidadesArquivo = arquivo.readInt();
    return quantidadesArquivo;
  }

  public boolean excluir(int idProcurado) throws Exception {
    boolean saberVerdade = false;
    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    int tam;
    T obj = construtor.newInstance();
    byte[] ba;
    while (arquivo.getFilePointer() < arquivo.length()) {
      long x = arquivo.getFilePointer();
      lapide = arquivo.readByte();
      tam = arquivo.readInt();
      if (lapide == ' ') {
        ba = new byte[tam];
        arquivo.read(ba);
        obj.fromByteArray(ba);
        if (obj.getIdResposta() == idProcurado) {
          // System.out.println(x);
          arquivo.seek(x);
          arquivo.writeByte('$');
          // arquivo.writeLong(-1);
          return true;
        }

      } else {
        arquivo.skipBytes(tam);
      }

    }

    return saberVerdade;
  }

  public boolean atualizarC(T novo) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    int tam;
    T obj = construtor.newInstance();
    byte[] ba;
    while (arquivo.getFilePointer() < arquivo.length()) {
      long x = arquivo.getFilePointer();
      lapide = arquivo.readByte();
      tam = arquivo.readInt();
      if (lapide == ' ') {
        ba = new byte[tam];
        arquivo.read(ba);
        obj.fromByteArray(ba);
        if (obj.getIdResposta() == novo.getIdResposta()) {
          byte[] novo1 = novo.toByteArray();
          if (novo1.length == ba.length) {
            colocarnoMesmolugar(novo, x);
          } else {
            excluir(novo.getIdResposta());
            arquivo.seek(0);
            int ultimoID = arquivo.readInt();
            int proximoID = ultimoID + 1;
            arquivo.seek(0);
            arquivo.writeInt(proximoID);
            arquivo.seek(arquivo.length());
            long o = arquivo.getFilePointer();
            colocarnoMesmolugar(novo, o);
            return true;
          }

          return true;
        }

      } else {
        arquivo.skipBytes(tam);
      }
    }
    return false;
  }

  public void colocarnoMesmolugar(T obj, long x) throws Exception {
    arquivo.seek(x);

    byte[] ba = obj.toByteArray();
    arquivo.writeByte(' ');
    arquivo.writeInt(ba.length);
    arquivo.write(ba);

  }

}
