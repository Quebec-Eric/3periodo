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
}
