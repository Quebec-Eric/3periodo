
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Voto {
    private int idVoto;
    private int idUsuario;
    private int idVotado;
    private boolean voto;

    public Voto() {
        this(-1, -1, -1, false);

    }

    public Voto(int idVoto, int idUsuario, int idVotado, boolean voto) {
        this.idVoto = idVoto;
        this.idUsuario = idUsuario;
        this.idVotado = idVotado;
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "{" + " idVoto='" + getIdVoto() + "'" + ", idUsuario='" + getIdUsuario() + "'" + ", idVotado='"
                + getIdVotado() + "'" + ", voto='" + isVoto() + "'" + "}";
    }

    public int getIdVoto() {
        return this.idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVotado() {
        return this.idVotado;
    }

    public void setIdVotado(int idVotado) {
        this.idVotado = idVotado;
    }

    public boolean isVoto() {
        return this.voto;
    }

    public boolean getVoto() {
        return this.voto;
    }

    public void setVoto(boolean voto) {
        this.voto = voto;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        DataOutputStream DAOS = new DataOutputStream(BAOS);
        DAOS.writeInt(this.idVoto);
        DAOS.writeInt(this.idUsuario);
        DAOS.writeInt(this.idVotado);
        DAOS.writeBoolean(this.voto);
        return BAOS.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream BAIS = new ByteArrayInputStream(ba);
        DataInputStream DAIS = new DataInputStream(BAIS);
        this.idVoto=DAIS.readInt();
        this.idUsuario = DAIS.readInt();
        this.idVotado=DAIS.readInt();
        this.voto=DAIS.readBoolean();
    }

}
