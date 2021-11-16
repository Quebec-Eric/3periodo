

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;






public class Respostas implements  PerguntaRegistro {
    private int idResposta;
    
    private int idPerguntas;
    private int idUsuario;
    protected String usuario;
    private Long criacao;
    private Short nota;
    protected String resposta;

    private boolean ativa;
    Date tempo = new Date();

    public Respostas() {
        this.idResposta = -1;
        this.idPerguntas = -1;
        this.idUsuario = -1;

        this.criacao = tempo.getTime();
        this.nota = 0;
        this.resposta = "";
        this.ativa = true;
    }


    public Respostas(int idPergunta, int idUsuario, String resposta, String usuario) {
        this.idResposta = -1;
        this.idPerguntas = idPergunta;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.criacao = tempo.getTime();
        this.nota = 0;
        this.resposta = resposta;
        this.ativa = true;
    }

    public Respostas(int id, int idPergunta, int idUsuario, String resposta, String usuario) {
        this.idResposta = id;
        this.idPerguntas = idPergunta;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.criacao = tempo.getTime();
        this.nota = 0;
        this.resposta = resposta;
        this.ativa = true;
    }

    public int getIdResposta() {
        return idResposta;
    }
    public void setResposta(String resposta){
        this.resposta= resposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public String getHash() {
        return Integer.toString(this.idResposta);
    }
    
  

    @Override
    public void setIdPergunta(int n) {
        this.idPerguntas = n;
    }

    @Override
    public int getIdPerguntas() {
        return this.idPerguntas;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setNota(Short nota) {
        this.nota = nota;
    }

    public Short getNota() {
        return nota;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }

 
    @Override
    public String toString() {
        String string;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        if (ativa) {
            string ="------------------------\n"+ this.resposta +"\n-------------------------"+ "\nRespondido em " + dateFormat.format(this.criacao) + " por: " + this.usuario
                    + "\nNota: " + nota;

        } else {
            string = "(Arquivada)\n" + this.resposta + "\nRespondido em " + dateFormat.format(this.criacao) + " por: "
                    + this.usuario + "\nNota: " + nota;
        }

        return string + "\n";
    }

    public void toPrint() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(dateFormat.format(this.tempo));
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        DataOutputStream DAOS = new DataOutputStream(BAOS);
        DAOS.writeInt(this.idResposta);
        DAOS.writeInt(this.idPerguntas);
        DAOS.writeInt(this.idUsuario);
        DAOS.writeUTF(this.usuario);
        DAOS.writeLong(this.criacao);
        DAOS.writeShort(this.nota);
        DAOS.writeUTF(this.resposta);
        DAOS.writeBoolean(this.ativa);
        return BAOS.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream BAIS = new ByteArrayInputStream(ba);
        DataInputStream DAIS = new DataInputStream(BAIS);
        this.idResposta = DAIS.readInt();
        this.idPerguntas = DAIS.readInt();
        this.idUsuario = DAIS.readInt();
        this.usuario = DAIS.readUTF();
        this.criacao = DAIS.readLong();
        this.nota = DAIS.readShort();
        this.resposta = DAIS.readUTF();
        this.ativa = DAIS.readBoolean();

    }
}
