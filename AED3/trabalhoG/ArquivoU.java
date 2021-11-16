
import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import java.text.Normalizer;
import java.util.ArrayList;

import tabelaHex.*;

public class ArquivoU<P extends RegistroP>  {

    protected int contadorID = 0;
    protected int contadorRe = 0;
    Constructor<P> construtor;
    protected long porteiro;

    ArvoreBMais<ParIntInt> arvore;

    RandomAccessFile arquivo;
    RandomAccessFile arquivoRR;
    ListaInvertida lista;
    

    ArquivoU(String file, Constructor<P> c) throws Exception {
        File f = new File("dados");
        if (!f.exists()) {
            f.mkdir();
        }
        f = new File("dados/" + file);
        if (!f.exists()) {
            f.mkdir();
        }
        arquivo = new RandomAccessFile("dados/" + file + "/arquivo.db", "rw");
       
        construtor = c;
        if (arquivo.length() == 0) {
            arquivo.writeInt(0);
            arvore = new ArvoreBMais<>(ParIntInt.class.getConstructor(), 5, "dados/arvore.db");
            lista = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
        }
    }

    public int Create(Pergunta obj) throws Exception {
        arquivo.seek(0); // colocar o ponteiro na posicao 1 do arquivo
        int idUltimo = arquivo.readInt();// saber qual o ultimo id no arquivo
        obj.setIdP(idUltimo + 1);// colocar mais 1 id no indice
        arquivo.seek(0);// voltar para a posicao 0
        arquivo.writeInt(obj.getIdP());

        arquivo.seek(arquivo.length());
        long enderecoArk = arquivo.getFilePointer();
        // String
        // palavra = removerAcentos(obj.toLowerCase());
        String vergulaColocar = obj.getPalavrasChave();
        try {
            String[] palavrasChaves = separadorDePontoEVirgula(obj.getPalavrasChave());
            int i = 0;
            for (i = 0; i < palavrasChaves.length; i++) {
                lista.create(palavrasChaves[i], obj.getIdP());

            }

            obj.setPalavrasChave(vergulaColocar);
            byte[] registro = obj.toByteArray();
            arquivo.writeByte(' ');
            arquivo.writeInt(registro.length);
            arquivo.write(registro);
            // System.out.println("ididididid para aarquivo "+ obj.getIdP() + "idididi user"
            // + obj.getIdUser());
            arvore.create(new ParIntInt(obj.getIdUser(), obj.getIdP()));

        } catch (Exception e) {

        }

        return 1;
    }



    

    public String readPId(int id) throws Exception {

        int tam = 0;
        String tudosOsids = "";
        Pergunta perg = new Pergunta();
        ArrayList<ParIntInt> lista1 = arvore.read(new ParIntInt(id, -1));
        // System.out.print(lista1.toString() + " ola ");
        int i = 0;
        for (i = 0; i < lista1.size(); i++) {

            tudosOsids += lista1.get(i);
        }
        tudosOsids += "|";
        tudosOsids += i;

        // byte[] ba = new byte[0];

        return tudosOsids;
    }



   

  

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public P read1(int idProcurado) throws Exception {// ler id especifico

        // System.out.println("iddddd "+ idProcurado);
        arquivo.seek(4); // pular o cabeçalho e se posicionar no primeiro registro
        byte lapide;
        int tam;
        P obj = construtor.newInstance();
        byte[] ba;
        while (arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tam = arquivo.readInt();
            if (lapide == ' ') {
                ba = new byte[tam];
                arquivo.read(ba);
                obj.fromByteArray(ba);
                // System.out.println("HEY YOOO "+ obj.getIdP()+" == " + idProcurado);
                // System.out.println(obj.toString());
                if (obj.getIdP() == idProcurado) {
                    //System.out.println("\nid da PErgunta == " + obj.getIdP());
                    return obj;
                }
            } else
                arquivo.skipBytes(tam);
        }
        return null;
    }

    public P readProf(int idProcurado) throws Exception {
        // System.out.println("iddddd "+ idProcurado);
        arquivo.seek(4); // pular o cabeçalho e se posicionar no primeiro registro
        byte lapide;
        int tam;
        P obj = construtor.newInstance();
        byte[] ba;
        while (arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tam = arquivo.readInt();
            if (lapide == ' ') {
                ba = new byte[tam];
                arquivo.read(ba);
                obj.fromByteArray(ba);
                // System.out.println("HEY YOOO "+ obj.getIdP()+" == " + idProcurado);
                // System.out.println(obj.toString());
                if (obj.getIdP() == idProcurado) {
                    // System.out.println(obj.toString());
                    return obj;
                }
            } else
                arquivo.skipBytes(tam);
        }
        return null;
    }

    public String[] separadorDePontoEVirgula(String passada) throws Exception {
        String[] novo = removerAcentos(passada).toLowerCase().split(";");

        for (int i = 0; i < novo.length; i++) {
            novo[i] = novo[i].trim();
        }
        return novo;
    }

    public boolean arquivarPergunta(int idPergunta, boolean t) throws Exception {

        arquivo.seek(4);
        byte lapide;
        int tam;
        P obj = construtor.newInstance();
        byte[] ba;
        while (arquivo.getFilePointer() < arquivo.length()) {
            long x = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tam = arquivo.readInt();
            if (lapide == ' ') {
                ba = new byte[tam];
                arquivo.read(ba);
                obj.fromByteArray(ba);

                if (obj.getIdP() == idPergunta) {
                    if (t) {

                        arquivarVerdade(obj, x);

                    } else {
                        arquivarfalse(obj, x);

                    }

                }
            } else
                arquivo.skipBytes(tam);
        }
        return false;

    }

    public boolean arquivarVerdade(P obj, long x) throws Exception {

        // arvore.create(new ParIntInt(obj.getIdUser(), obj.getIdP()));
        String[] palavrasChaves = separadorDePontoEVirgula(obj.getPalavrasChave());
        int i = 0;
        for (i = 0; i < palavrasChaves.length; i++) {
            lista.create(palavrasChaves[i], obj.getIdP());

        }
        obj.setAtiva(true);
        colocarnoMesmolugar(obj, x);
        return true;
    }

    public boolean arquivarfalse(P obj, long x) throws Exception {
        // arvore.delete(new ParIntInt(obj.getIdUser(), obj.getIdP()));
        String[] palavrasChaves = separadorDePontoEVirgula(obj.getPalavrasChave());
        int i = 0;
        for (i = 0; i < palavrasChaves.length; i++) {
            lista.delete(palavrasChaves[i], obj.getIdP());
        }
        obj.setAtiva(false);
        colocarnoMesmolugar(obj, x);
        return true;
    }

    public void colocarnoMesmolugar(P obj, long x) throws Exception {
        arquivo.seek(x);

        byte[] ba = obj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeInt(ba.length);
        arquivo.write(ba);

    }

    public boolean update(int idProcurado, String perguntaN, String palavraC) throws Exception {

        arquivo.seek(4);
        byte lapide;
        int tam;
        P obj = construtor.newInstance();

        byte[] ba;
        byte[] ba2;
        while (arquivo.getFilePointer() < arquivo.length()) {
            long x = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tam = arquivo.readInt();
            if (lapide == ' ') {
                ba = new byte[tam];
                arquivo.read(ba);
                obj.fromByteArray(ba);

                if (obj.getIdP() == idProcurado) {
                    P newobj = construtor.newInstance();
                    newobj.setIdUser(obj.getIdUser());
                    newobj.setIdP(obj.getIdP());
                    newobj.setCriacao(obj.getCriacao());
                    newobj.setNota(obj.getNota());
                    newobj.setPalavrasChave(palavraC);
                    newobj.setAtiva(obj.getAtiva());
                    newobj.setPergunta(perguntaN);
                    ba2 = newobj.toByteArray();
                    if (ba2.length > ba.length) {
                        deletarbj(obj);
                        Criarrbj(newobj);
                        // quer dizer que o novo registro e maior
                        arquivo.seek(x); // reseta o ponteiro para a lapide
                        arquivo.writeByte('$');// anula o registro
                        arquivo.seek(arquivo.length());
                        // o id do objeto e mantido
                        arquivo.writeByte(' ');
                        arquivo.writeInt(ba2.length);
                        arquivo.write(ba2);
                    } else {
                        deletarbj(obj);
                        Criarrbj(newobj);
                        colocarnoMesmolugar(newobj, x);
                    }
                }

            } else
                arquivo.skipBytes(tam);
        }
        return false;
    }

    public void deletarbj(P obj) throws Exception {

        String[] palavrasChaves = separadorDePontoEVirgula(obj.getPalavrasChave());
        int i = 0;
        for (i = 0; i < palavrasChaves.length; i++) {
            lista.delete(palavrasChaves[i], obj.getIdP());
        }

    }

    public void Criarrbj(P obj) throws Exception {
        String[] palavrasChaves = separadorDePontoEVirgula(obj.getPalavrasChave());
        int i = 0;
        for (i = 0; i < palavrasChaves.length; i++) {
            lista.create(palavrasChaves[i], obj.getIdP());

        }
    }

    public ArrayList<Integer> pegarIdPalavrachave(String[] busca) throws Exception {
        int[] listaid;
        ArrayList<Integer> resposta = new ArrayList<Integer>();
        if (busca.length > 0) {
            listaid = lista.read(busca[0].trim());
            for (int id : listaid) {
                resposta.add(id);
            }
            int i = 1;
            while (i < busca.length) {
                listaid = lista.read(busca[i]);
                ArrayList<Integer> lista2 = new ArrayList<Integer>();
                for (int id : listaid) {
                    lista2.add(id);
                }
                resposta.retainAll(lista2);
                i++;
            }

        }
       
        
        return resposta;
    }

}

