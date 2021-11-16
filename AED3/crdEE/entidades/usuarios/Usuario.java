package entidades.usuarios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Usuario implements utilitarios.Registro {
  protected int idUsuario;
  protected String nome;
  protected String email;
  protected int hashsenha;

  public Usuario(int i, String n, String e, int hs) {
    this.idUsuario = i;
    this.nome = n;
    this.email = e;
    this.hashsenha = hs;
  }

  public Usuario(String n, String e, int hs) {
    this.idUsuario = -1;
    this.nome = n;
    this.email = e;
    this.hashsenha = hs;
  }

  public Usuario() {
    this.idUsuario = -1;
    this.nome = "";
    this.email = "";
    this.hashsenha = 0;
  }

  public int getID() {
    return this.idUsuario;
  }

  public void setID(int id) {
    this.idUsuario = id;
  }

  public String getNome() {
    return this.nome;
  }

  public String getEmail() {
    return this.email;
  }

  public boolean validaSenha(int hs) {
    return this.hashsenha == hs;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idUsuario);
    dos.writeUTF(this.nome);
    dos.writeUTF(this.email);
    dos.writeInt(this.hashsenha);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] b) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(b);
    DataInputStream dis = new DataInputStream(bais);
    this.idUsuario = dis.readInt();
    this.nome = dis.readUTF();
    this.email = dis.readUTF();
    this.hashsenha = dis.readInt();
  }

}