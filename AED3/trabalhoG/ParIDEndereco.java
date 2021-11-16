import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import tabelaHex.*;
public class ParIDEndereco implements RegistroHashExtensivel<ParIDEndereco> {

  private String email;
  private long endereco;
  private short TAMANHO = 48;

  public ParIDEndereco() {
    this(" ", -1);
  }

  public ParIDEndereco(String string, long i) {
    try {
      this.email = string;
      this.endereco = i;
      if (string.length() + 4 > TAMANHO)
        throw new Exception("Número de caracteres do email maior que o permitido. Os dados serão cortados.");
    } catch (Exception ec) {
      ec.printStackTrace();
    }
  }

  @Override
  public int hashCode() {
    return this.email.hashCode();
  }

  public short size() {
    return this.TAMANHO;
  }

  public long getEndereco() {
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
    byte[] bs = baos.toByteArray();
    byte[] bs2 = new byte[TAMANHO];
    for (int i = 0; i < TAMANHO; i++)
      bs2[i] = ' ';
    for (int i = 0; i < bs.length && i < TAMANHO; i++)
      bs2[i] = bs[i];
    return bs2;
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.email = dis.readUTF();
    this.endereco = dis.readLong();
  }

}