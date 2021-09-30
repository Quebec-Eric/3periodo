
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



public class ParIDEndereco implements tabelaHex.RegistroHashExtensivel<ParIDEndereco> {

  private int id;
  private long endereco;
  private String email;
  private short TAMANHO = 12;

  public ParIDEndereco() {
    this(-1, -1);
  }

  public ParIDEndereco(int i, long e) {
      this.id = i;
      this.endereco = e;
      //this.email=s;
  }

  @Override
  public int hashCode() {
    return this.id;
  }
  public String hashCodeE() {
    return this.email;
  }

  public short size() {
    return this.TAMANHO;
  }

  public long getEndereco(){
    return this.endereco;
  }

  public String getEmail(){
    return this.email;
  }

 

  public String toString() {
    return this.id + ";" + this.endereco;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.id);
    dos.writeLong(this.endereco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.id = dis.readInt();
    this.endereco = dis.readLong();
  }

}