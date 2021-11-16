package utilitarios;
/*

Esta classe representa um PAR CHAVE VALOR para uma entidade qualquer. 
Seu objetivo é representar uma entrada de índice. 

Esse índice será direto, baseado no ID da pessoa. Ao fazermos a busca 
por ID, ele retornará o endereco do registro no arquivo de dados.
 
Implementado pelo Prof. Marcos Kutova
v1.0 - 2021
 
*/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIdEndereco implements utilitarios.RegistroHashExtensivel<ParIdEndereco> {

  private int id;
  private long endereco;
  private short TAMANHO = 12;

  public ParIdEndereco() {
    this(-1, -1);
  }

  public ParIdEndereco(int i, long e) {
    this.id = i;
    this.endereco = e;
  }

  @Override
  public int hashCode() {
    return this.id;
  }

  public long getEndereco() {
    return this.endereco;
  }

  public short size() {
    return this.TAMANHO;
  }

  public String toString() {
    return this.id + ";" + this.endereco;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(id);
    dos.writeLong(endereco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.id = dis.readInt();
    this.endereco = dis.readLong();
  }

}