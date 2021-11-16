/*

Esta classe representa um PAR CHAVE VALOR (PCV) 
para uma entidade Livro. Seu objetivo é representar
uma entrada de índice. 

Esse índice será secundário e indireto, baseado no
título de um livro. Ao fazermos a busca por título,
ele retornará o ID desse livro, para que esse ID
possa ser buscado em um índice direto (que não é
apresentado neste projeto)

Um índice direto de ID precisaria ser criado por meio
de outra classe, cujos dados fossem um int para o ID
e um long para o endereço
 
Implementado pelo Prof. Marcos Kutova
v1.0 - 2021
 
*/
package entidades.usuarios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParEmailId implements utilitarios.RegistroHashExtensivel<ParEmailId> {

  private String email;
  private int id;
  private short TAMANHO = 44;

  public ParEmailId() {
    this("", -1);
  }

  public ParEmailId(String e, int i) {
    try {
      this.email = e;
      this.id = i;
      if (e.length() + 4 > TAMANHO)
        throw new Exception("Número de caracteres do e-mail maior que o permitido. Os dados serão cortados.");
    } catch (Exception ec) {
      ec.printStackTrace();
    }
  }

  @Override
  public int hashCode() {
    return this.email.hashCode();
  }

  public int getID() {
    return this.id;
  }

  public short size() {
    return this.TAMANHO;
  }

  public String toString() {
    return this.email + ";" + this.id;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(email);
    dos.writeInt(id);
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
    this.id = dis.readInt();
  }

}