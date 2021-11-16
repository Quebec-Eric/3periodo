package logicas;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Ajudante {
    public static int idUsuarioGlobal = -1;
    public void apagarDados() {
        File d;
        if (!(d = new File("dados")).exists())
            d.mkdir();
        if (!(d = new File("dados/usuarios")).exists())
            d.mkdir();
        if (!(d = new File("dados/perguntas")).exists())
            d.mkdir();
    }

    public static void limpar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausa(String mensagem) {
        System.out.println("\n" + mensagem);
        System.out.println("\nPressione ENTER para continuar...");
        System.out.println("");
    }

    public static long StringDateToLong(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setLenient(false);
        Date dData = sdf.parse(s);
        return dData.getTime();
    }

    public static String LongDateToString(long l) {
        Date dData = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(dData);
    }

    public static void armazenaUsuario(int id) {
        idUsuarioGlobal = id;
      }
    
      public static int usuarioGlobal() {
        return idUsuarioGlobal;
      }

}
