package tabelaHex;
public interface Registro {

  public byte[] toByteArray() throws Exception;

  public void fromByteArray(byte[] ba) throws Exception;

  public void setID(int ba);
  //public void setIdade(int ba);

  public int getID();
  public String getEmail();
  

  //public int getIdade();
}
