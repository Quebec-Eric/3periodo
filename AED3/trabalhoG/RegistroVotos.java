import java.io.IOException;

public interface RegistroVotos {
    public int getIdVoto();

    public byte[] toByteArray() throws IOException;

    public void fromByteArray(byte[] ba) throws IOException, CloneNotSupportedException, Exception;

    public void setIdVoto(int idVoto);

    public int getIdUsuario();

    public void setIdUsuario(int idUsuario);

    public int getIdVotadoPergunta();

    public void setIdVotadoPergunta(int idVotado);

    public boolean isVoto();

    public void setVoto(boolean voto);
}