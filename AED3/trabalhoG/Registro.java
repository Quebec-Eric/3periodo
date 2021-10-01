
public interface Registro {
  public int getID();

  public void setID(int n);

  public byte[] toByteArray() throws Exception;

  public void fromByteArray(byte[] b) throws Exception;
  public String getHash();
}