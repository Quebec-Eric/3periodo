import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Usuario implements tabelaHex.Registro {
  protected int idUsuario;
  protected String nome;
  protected String email;
  protected String senha;

  public Usuario(String t, String a, String admin) {
    this.idUsuario = -1;
    this.nome = t;
    this.email = a;
    this.senha = admin; 
  }

  public Usuario() {
    this.idUsuario = -1;
    this.nome = "";
    this.email = "";
    this.senha = ""; 
  }

  public void setID(int id) {
    this.idUsuario = id;
  }

  public int getID() {
    return this.idUsuario;
  }

  public String getEmail() {
    return this.email;
  }



  public String toString() {

    return "\nID....: " + this.idUsuario + "\nNome: " + this.nome + "\nEmail.: " + this.email + "\nSenha.: " + this.senha;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(nome);
    dos.writeUTF(email);
    dos.writeUTF(senha);
    dos.writeInt(idUsuario);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    nome = dis.readUTF();
    email = dis.readUTF();
    senha = dis.readUTF();
    idUsuario = dis.readInt();
    
  }

}