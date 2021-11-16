package entidades.perguntas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Pergunta implements utilitarios.Registro {
  protected int idPergunta;
  protected int idUsuario;
  protected long criacao;
  protected short nota;
  protected String pergunta;
  protected String palavrasChave;
  protected boolean ativa;

  public Pergunta(int i, int u, long c, short n, String p, String pc, boolean a) {
    this.idPergunta = i;
    this.idUsuario = u;
    this.criacao = c;
    this.nota = n;
    this.pergunta = p;
    this.palavrasChave = pc;
    this.ativa = a;
  }

  public Pergunta(int u, String p, String pc) {
    this.idPergunta = -1;
    this.idUsuario = u;
    this.criacao = System.currentTimeMillis();
    this.nota = 0;
    this.pergunta = p;
    this.palavrasChave = pc;
    this.ativa = true;
  }

  public Pergunta() {
    this.idPergunta = -1;
    this.idUsuario = -1;
    this.criacao = 0;
    this.nota = 0;
    this.pergunta = "";
    this.palavrasChave = "";
    this.ativa = false;
  }

  public int getID() {
    return this.idPergunta;
  }

  public void setID(int id) {
    this.idPergunta = id;
  }

  public int getIDUsuario() {
    return this.idUsuario;
  }

  public short getNota() {
    return this.nota;
  }

  public long getCriacao() {
    return this.criacao;
  }

  public String getPergunta() {
    return this.pergunta;
  }

  public void setPergunta(String p) {
    this.pergunta = p;
  }

  public boolean getAtiva() {
    return this.ativa;
  }

  public void setAtiva(boolean b) {
    this.ativa = b;
  }

  public String getPalavrasChave() {
    return this.palavrasChave;
  }

  public void setPalavrasChave(String pc) {
    this.palavrasChave = pc;
  }

  public short incrementaNota() {
    return ++this.nota;
  }

  public short decrementaNota() {
    return --this.nota;
  }

  public String toString() {
    return "\nID......: " + this.idPergunta + "\nPergunta:\n" + this.pergunta;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idPergunta);
    dos.writeInt(this.idUsuario);
    dos.writeLong(this.criacao);
    dos.writeShort(this.nota);
    dos.writeUTF(this.pergunta);
    dos.writeUTF(this.palavrasChave);
    dos.writeBoolean(this.ativa);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] b) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(b);
    DataInputStream dis = new DataInputStream(bais);
    this.idPergunta = dis.readInt();
    this.idUsuario = dis.readInt();
    this.criacao = dis.readLong();
    this.nota = dis.readShort();
    this.pergunta = dis.readUTF();
    this.palavrasChave = dis.readUTF();
    this.ativa = dis.readBoolean();
  }

}