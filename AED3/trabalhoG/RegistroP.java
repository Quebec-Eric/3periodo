import java.io.IOException;
public interface RegistroP {
    public int getIdP();

    public void setIdP(int n);
    
    public int getIdUser();

    public void setIdUser(int newID);

    public boolean getAtiva();

    public void setAtiva(boolean ativa);

    public byte[] toByteArray() throws IOException;

    public void fromByteArray(byte[] ba) throws IOException, CloneNotSupportedException, Exception;

   
    public String getPalavrasChave();
    public Long getCriacao();
    public short getNota();
    public void setCriacao(long passada) ;
    public void setPalavrasChave(String passada);
     public void setNota(Short passada);
      public String getPergunta();
      public void setPergunta(String nova);
}
