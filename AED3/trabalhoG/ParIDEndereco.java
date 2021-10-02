import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDEndereco implements tabelaHex.RegistroHashExtensivel<ParIDEndereco> {

  private String email;
  private long endereco;
  private short TAMANHO = 48;

  public ParIDEndereco() {
    this(" ", -1);
  }

  public ParIDEndereco(String i, long e) {
      this.email = i;
      this.endereco = e;
  }

  @Override
  public int hashCode() {
    return this.email.hashCode();
  }

  public short size() {
    return this.TAMANHO;
  }

  public long getEndereco(){
    return this.endereco;
  }

 

  public String toString() {
    return this.email + ";" + this.endereco;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(this.email);
    dos.writeLong(this.endereco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.email= dis.readUTF();
    this.endereco = dis.readLong();
  }

}