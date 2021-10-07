import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Pergunta implements Registro {
   
    private int idUsuario;
    private int idPergunta;
    private long criacao;
    private short nota;
    private String pergunta;
    private String palavrasChave;
    private boolean ativa;




  public Pergunta(){
        this.idUsuario = -1;
        this.idPergunta= -1;
        this.criacao = 0;
        this.nota = 0;
        this.pergunta = "";
        this.palavrasChave = "";
        this.ativa = false;
       
    }
    public Pergunta(int idUsuario1, int idPergunta1, long criacao1, short nota1, String pergunta1, String palavrasChave1, boolean ativa1){
        this.idUsuario = idUsuario1;
        this.idPergunta= idPergunta1;
        this.criacao = criacao1;
        this.nota = nota1;
        this.pergunta = pergunta1;
        this.palavrasChave = palavrasChave1;
        this.ativa = ativa1;
    }


public int getID()
{
    return this.idPergunta;
}

public void setID(int newID)
{
this.idPergunta = newID;
}

   public String toString() {
        // TODO Auto-generated method stub

/* DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	 dateFormat.format(date);
        System.out.println(date);*/
        return "\n"+criacao+"\n" + pergunta;
        
    }

public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        DataOutputStream DAOS = new DataOutputStream(BAOS);
        DAOS.writeInt(this.idUsuario);
        DAOS.writeInt(getID());
        DAOS.writeLong(this.criacao);
        DAOS.writeShort(this.nota);
        DAOS.writeUTF(this.pergunta);
        DAOS.writeUTF(this.palavrasChave);
        DAOS.writeBoolean(this.ativa);
        return BAOS.toByteArray();
    }


    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream BAIS = new ByteArrayInputStream(ba);
        DataInputStream DAIS = new DataInputStream(BAIS);
        this.idUsuario = DAIS.readInt();
        setID(DAIS.readInt());
        this.criacao = DAIS.readLong();
        this.nota = DAIS.readShort();
        this.pergunta = DAIS.readUTF();
        this.palavrasChave = DAIS.readUTF();
        this.ativa = DAIS.readBoolean();

    }

    
    public String getHash(){
        return "test";
    }

    public String getEmail(){
        return "St";
    }

  public String getSenha(){return "s";}

  public void setSenha(String senha){};

}
