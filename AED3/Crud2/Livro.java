import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Livro implements Registro {
  protected int idLivro;
  protected String titulo;
  protected String autor;
  protected String isbn;
  protected float preco;

  public Livro(String t, String a, String i, float p) throws Exception {
    this.idLivro = -1;
    this.titulo = t;
    this.autor = a;
    if (i.getBytes().length != 13)
      throw new Exception("Tamanho do ISBN inválido!");
    this.isbn = i;
    this.preco = p;
  }

  public Livro() {
    this.idLivro = -1;
    this.titulo = "";
    this.autor = "";
    this.isbn = "";
    this.preco = 0F;
  }

  public void setID(int id) {
    this.idLivro = id;
  }

   public void setPreco(float p){
    this.preco=p;
   } 
  public void setIsbn(String i){
    this.isbn=i;
  }
  public float getPreco(){
    return this.preco;
  }


  public int getID() {
    return this.idLivro;
  }

  public void setTitulo(String titulo){
    this.titulo=titulo;
  }

  public void setAutor(String autor){
    this.autor=autor;
  }
  public String toString() {
    DecimalFormat df = new DecimalFormat("#,##0.00");

    return "\nID....: " + this.idLivro + "\nTítulo: " + this.titulo + "\nAutor.: " + this.autor + "\nISBN..: "
        + this.isbn + "\nPreço.: R$ " + df.format(this.preco);
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idLivro);
    dos.writeUTF(this.titulo);
    dos.writeUTF(this.autor);
    dos.write(this.isbn.getBytes());
    dos.writeFloat(this.preco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    byte[] isbnAux = new byte[13];
    this.idLivro = dis.readInt();
    this.titulo = dis.readUTF();
    this.autor = dis.readUTF();
    dis.read(isbnAux);
    this.isbn = new String(isbnAux);
    this.preco = dis.readFloat();
  }

}