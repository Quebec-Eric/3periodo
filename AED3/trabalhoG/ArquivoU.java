
import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import java.text.Normalizer;
import java.util.ArrayList;

import tabelaHex.*;

public class ArquivoU<T extends RegistroP> {

    protected int contadorID = 0;
    protected int contadorRe = 0;
    Constructor<T> construtor;
    protected long porteiro;

    ArvoreBMais<ParIntInt> arvore;
    RandomAccessFile arquivo;
    ListaInvertida lista;

    ArquivoU(String file, Constructor<T> c) throws Exception {
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
        byte[] registro = obj.toByteArray(); // String
        // palavra = removerAcentos(obj.toLowerCase());
        try {
            arquivo.writeByte(' ');
            arquivo.writeInt(registro.length);
            arquivo.write(registro);
            arvore.create(new ParIntInt(obj.getIdUser(), obj.getIdP()));
            arvore.print();
            System.out.println(obj.getIdUser() + " " + obj.getIdP());

        } catch (Exception e) {

        }

        return 1;
    }
//ado
    public Pergunta readP(int id) throws Exception {

        int tam = 0;

        Pergunta perg = new Pergunta();
        ArrayList<ParIntInt> lista1 = arvore.read(new ParIntInt(id, -1));
        System.out.print(lista1.toString() + " ola ");
        for (int i = 0; i < lista1.size(); i++)
            System.out.print(lista1.get(i) + " ");

        // byte[] ba = new byte[0];

        return null;
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
