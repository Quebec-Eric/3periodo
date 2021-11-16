package logicas;

import java.util.ArrayList;

//import java.util.Scanner;
import entidades.perguntas.*;

public class PerguntasTotais {
    // private static Scanner leitura = new Scanner(System.in);
    private static ArquivoPergunta arqPerguntas;

    public static void Totais() throws Exception {
        System.out.println("\n\nBuscar");
        System.out.println("-------------------------------");
        ArrayList<Integer> resposta = new ArrayList<>();
        try {
         resposta=arqPerguntas.buscarPalavraC(InterfacesPergunta.lePalavrasChave());
           
        } catch (Exception e) {
            Ajudante.pausa(e.toString());
            System.out.println(e);
        }

        resposta.toString();

    }

    public static Pergunta[] fazermudancas(Pergunta[] pp) {

        for (int i = pp.length - 1; i > 0; i--) {
            for (int z = 0; z < i; z++) {

                if (pp[z].getNota() < pp[z + 1].getNota()) {
                    swap(z, z + 1, pp);
                }
            }
        }

        return pp;
    }

    public static void swap(int primeiraP, int segundaP, Pergunta[] pp) {
        Pergunta receber;
        receber = pp[primeiraP];
        pp[primeiraP] = pp[segundaP];
        pp[segundaP] = receber;
    }

}
