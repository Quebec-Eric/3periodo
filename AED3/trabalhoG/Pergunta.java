import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Pergunta implements RegistroP {

    private int idUsuario;
    private int idPergunta;
    private long criacao;
    private short nota;
    private String pergunta;
    private String palavrasChave;
    private boolean ativa;

    public Pergunta() {
        this.idUsuario = -1;
        this.idPergunta = -1;
        this.criacao = 0;
        this.nota = 0;
        this.pergunta = "";
        this.palavrasChave = "";
        this.ativa = false;

    }

    public Pergunta(int idUsuario1, int idPergunta1, long criacao1, short nota1, String pergunta1,
            String palavrasChave1, boolean ativa1) {
        this.idUsuario = idUsuario1;
        this.idPergunta = idPergunta1;
        this.criacao = criacao1;
        this.nota = nota1;
        this.pergunta = pergunta1;
        this.palavrasChave = palavrasChave1;
        this.ativa = ativa1;
    }

    public void setIdP(int n){
        this.idPergunta=n;
    }

    public int getIdP(){
        return idPergunta;
    }

    public int getIdUser() {
        return this.idPergunta;
    }

    public void setIdUser(int newID) {
        this.idPergunta = newID;
    }

    public String toString() {
        // TODO Auto-generated method stub

        /*
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss"); 
          Date date = new Date(); //dateFormat.format(date); 
         System.out.println(dateFormat.format(date));
         */

        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        System.out.println(sdf.format(data));
        
        // long longDate=data.getTime();
        
    
         
        return "\n" + criacao + "\n" + pergunta;

    }
    public boolean getAtiva(){
        return this.ativa;
    }

    public void setAtiva(boolean ativa){
        this.ativa=ativa;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        DataOutputStream DAOS = new DataOutputStream(BAOS);
        DAOS.writeInt(this.idUsuario);
        DAOS.writeInt(getIdUser());
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
        setIdUser(DAIS.readInt());
        this.criacao = DAIS.readLong();
        this.nota = DAIS.readShort();
        this.pergunta = DAIS.readUTF();
        this.palavrasChave = DAIS.readUTF();
        this.ativa = DAIS.readBoolean();

    }

}
