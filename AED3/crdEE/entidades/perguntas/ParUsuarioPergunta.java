/*
Esta classe representa um objeto para uma entidade,
que será armazenado em uma árvore B+
 
Implementado pelo Prof. Marcos Kutova
v1.0 - 2021
*/
package entidades.perguntas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParUsuarioPergunta implements utilitarios.RegistroArvoreBMais<ParUsuarioPergunta> {

  private int idUsuario;
  private int idPergunta;
  private short TAMANHO = 8;

  public ParUsuarioPergunta() {
    this(-1, -1);
  }

  public ParUsuarioPergunta(int u, int p) {
    try {
      this.idUsuario = u;
      this.idPergunta = p;
    } catch (Exception ec) {
      ec.printStackTrace();
    }
  }

  public int getIDUsuario() {
    return this.idUsuario;
  }

  public int getIDPergunta() {
    return this.idPergunta;
  }

  public short size() {
    return this.TAMANHO;
  }

  public int compareTo(ParUsuarioPergunta a) {
    if (this.idUsuario != a.idUsuario)
      return this.idUsuario - a.idUsuario;
    else
      return this.idPergunta == -1 ? 0 : this.idPergunta - a.idPergunta;
  }

  @Override
  public ParUsuarioPergunta clone() {
    return new ParUsuarioPergunta(this.idUsuario, this.idPergunta);
  }

  public String toString() {
    return String.format("%3d", this.idUsuario) + ";" + String.format("%-3d", this.idPergunta);
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idUsuario);
    dos.writeInt(this.idPergunta);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.idUsuario = dis.readInt();
    this.idPergunta = dis.readInt();
  }

}