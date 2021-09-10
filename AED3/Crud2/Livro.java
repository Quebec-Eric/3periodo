import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.DecimalFormat;

public class Livro implements aed3.Registro {
  protected int idLivro;
  protected String titulo;
  protected String autor;
  protected float preco;

  public Livro(int i, String t, String a, float p) {
    this.idLivro = i;
    this.titulo = t;
    this.autor = a;
    this.preco = p;
  }

  public Livro() {
    this.idLivro = -1;
    this.titulo = "";
    this.autor = "";
    this.preco = 0F;
  }

  public int getID() {
    return this.idLivro;
  }

  public void setID(int id) {
    this.idLivro = id;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("#,##0.00");

    return "\nID....: " + this.idLivro + "\nTítulo: " + this.titulo + "\nAutor.: " + this.autor + "\nPreço.: R$ "
        + df.format(this.preco);
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idLivro);
    dos.writeUTF(this.titulo);
    dos.writeUTF(this.autor);
    dos.writeFloat(this.preco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] b) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(b);
    DataInputStream dis = new DataInputStream(bais);
    this.idLivro = dis.readInt();
    this.titulo = dis.readUTF();
    this.autor = dis.readUTF();
    this.preco = dis.readFloat();
  }

}
