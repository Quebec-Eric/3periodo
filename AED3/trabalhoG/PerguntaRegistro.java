
import java.io.IOException;

public interface PerguntaRegistro {
    public int getIdPerguntas();

    public void setIdPergunta(int n);

    public void setIdUsuario(int idUsuario);
    public int  getIdResposta();

    public int getIdUsuario();

    public boolean isAtiva();

    public void setAtiva(boolean ativa);

    public byte[] toByteArray() throws IOException;

    public void fromByteArray(byte[] ba) throws IOException, CloneNotSupportedException, Exception;

    public void setIdResposta(int idResposta);
    public String getHash() ;
}