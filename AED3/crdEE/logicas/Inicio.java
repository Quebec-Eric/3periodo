package logicas;

import java.util.Scanner;
import entidades.usuarios.*;
import entidades.usuarios.ArquivoUsuario;

public abstract class Inicio {

    private static ArquivoUsuario arqUsuarios;
    private static Scanner leitura = new Scanner(System.in);
    public static Ajudante ajudar = new Ajudante();

    public static boolean menu() throws Exception {

        arqUsuarios = new ArquivoUsuario("dados/usuarios");

        int oqfazer = 0;
        boolean saber = false;
        do {
            // ajudar.limpar();
            System.out.println("              MENU");
            System.out.println("-------------------------------");
            System.out.println("1 - Acesso a conta (Minha area/Buscar perguntas)");
            System.out.println("2 - Novo usuario");
            System.out.println("\n0 - Sair");
            oqfazer = leitura.nextInt();

            switch (oqfazer) {
            case 1:
                saber = acesso();
                System.out.println(saber);
                break;
            case 2:
                cadastro();
                break;
            case 0:
                break;
            }
        } while (!saber && oqfazer != 0);
        arqUsuarios.close();
        return saber;
    }

    /*
     * Inicio da criacao de um novo usuario do sistema iremos pegar as informacoes
     * de email , senha , nome essas operacoes iram ser feiras em uma funcao que se
     * incontra em um programa chamado InterfacesUsuario no qual todos as
     * inplementacoes de user iremos fazer
     */
    // inicio cadastro
    public static void cadastro() throws Exception {
        String nomeUser="";
        String emailUser="";
        int senha = 0;
        System.out.println("\nNovo Usuario");
        try {
            emailUser = InterfacesUsuario.leEmail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (arqUsuarios.read(emailUser) != null) {
            Ajudante.pausa("Email ja se encontra no sistema");
            return;
        }
        try {
            nomeUser = InterfacesUsuario.leNome();
            senha = InterfacesUsuario.leSenha();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Usuario newUu = new Usuario(nomeUser, emailUser, senha);
        System.out.println("\nConfirmar cadastro S/N");
        leitura.nextLine();
        String confirmar = leitura.nextLine();
        Ajudante.pausa("");
        if (confirmar.charAt(0) == 's' || confirmar.charAt(0) == 'S') {
            arqUsuarios.create(newUu);
            Ajudante.pausa("Criado");
        } else {
            Ajudante.pausa("Cancelado");
        }
        return;

    }

    public static boolean acesso() throws Exception {
        String email="";
        int hashSenha=0;;
        System.out.println("\nACESSO AO SISTEMA");
        try {
          email = InterfacesUsuario.leEmail();
          hashSenha = InterfacesUsuario.leSenha();
        } catch (Exception e) {
          Ajudante.pausa("Operação cancelada");
          return false;
        }
    
        Usuario u = arqUsuarios.read(email);
        if (u == null) {
          Ajudante.pausa("E-mail ou senha inválidos!");
          return false;
        }
    
        if (!u.validaSenha(hashSenha)) {
          Ajudante.pausa("E-mail ou senha inválidos!");
          return false;
        }
    
       Ajudante.armazenaUsuario(u.getID());
    
        return true;
      }

}
