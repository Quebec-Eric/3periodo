import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro> {

  RandomAccessFile arquivo;
  protected int ultimoId = 0;
  protected int contRegistros = 0;
  Constructor<T> construtor;
  final int TAMANHO_CABECALHO = 4;

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
    }
  }

  public int create(T obj) throws Exception {

    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    int proximoID = ultimoID + 1;
    arquivo.seek(0);
    arquivo.writeInt(proximoID);

    arquivo.seek(arquivo.length());

    obj.setID(proximoID);
    byte[] ba = obj.toByteArray();
    arquivo.writeByte(' ');
    arquivo.writeInt(ba.length);
    arquivo.write(ba);

    return proximoID;
  }

  public T read(int idProcurado) throws Exception {// ler id especifico

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    int tam;
    T obj = construtor.newInstance();
    byte[] ba;
    while (arquivo.getFilePointer() < arquivo.length()) {
      lapide = arquivo.readByte();
      tam = arquivo.readInt();
      if (lapide != '$') {
        ba = new byte[tam];
        arquivo.read(ba);
        obj.fromByteArray(ba);
        if (obj.getID() == idProcurado)
          return obj;
      } else
        arquivo.skipBytes(tam);
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
        if (obj.getID() == idProcurado) {
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
        if (obj.getID() == novo.getID()) {
          byte[] novo1 = novo.toByteArray();
          if (novo1.length == ba.length) {
            colocarnoMesmolugar(novo, x);
          } else {
            excluir(novo.getID());
            arquivo.seek(0);
            int ultimoID = arquivo.readInt();
            int proximoID = ultimoID + 1;
            arquivo.seek(0);
            arquivo.writeInt(proximoID);
            arquivo.seek(arquivo.length());
            long o = arquivo.getFilePointer();
            colocarnoMesmolugar(novo,o);
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
