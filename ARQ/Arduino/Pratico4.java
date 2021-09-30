import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Pratico4 {

    /*
     * A funcao converte uma string em um array de bytes
     *
     * @param - String passada pra conversão
     * 
     * @return a - Devolve o byte array para ser colocado no arquivo
     */
    public static byte[] toByteArray(String event) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(event);
        return baos.toByteArray();
    }

    /*
     * A funcao converte um array de bytes em uma string
     *
     * @param - Byte array passado para conversão
     * 
     * @return a - Devolve a String convertida
     */
    public static String fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        String a = dis.readUTF();
        return a;
    }

    /*
     * A funcao le e registra as operacoes, mandando para o arquivo hexadecimal
     *
     * @param x - valor de x para gravacao
     * 
     * @param y - valor de y para gravacao
     */
    public static void leitura(int x, int y) {
        Scanner ler = new Scanner(System.in);
        RandomAccessFile arquivo;

        String guarda = ler.nextLine();
        String[] recurso = new String[4];
        /* Leitura de valores de X e Y para alteracao */
        while (!guarda.equals("fim")) {
            if (guarda.contains("X") || guarda.contains("Y")) {
                if (guarda.contains("X")) {
                    recurso = guarda.split("=");
                    recurso = recurso[1].split(";");

                    x = Integer.parseInt(recurso[0]);
                } else if (guarda.contains("Y")) {
                    recurso = guarda.split("=");
                    recurso = recurso[1].split(";");

                    y = Integer.parseInt(recurso[0]);
                }

            } else {
                /* Analise dos Mnenomios e transformacao em numeros */ 
                String event = "";
                event += x;
                event += y;

                recurso = guarda.split(";");

                if (recurso[0].equals("An")) {

                    event += "0";
                }
                if (recurso[0].equals("nAoB")) {

                    event += "1";
                }
                if (recurso[0].equals("AnB")) {

                    event += "2";
                }
                if (recurso[0].equals("zeroL")) {

                    event += "3";
                }
                if (recurso[0].equals("nAeB")) {

                    event += "4";
                }
                if (recurso[0].equals("Bn")) {

                    event += "5";
                }
                if (recurso[0].equals("AxB")) {

                    event += "6";
                }
                if (recurso[0].equals("ABn")) {

                    event += "7";
                }
                if (recurso[0].equals("AnoB")) {

                    event += "8";
                }
                if (recurso[0].equals("nAxB")) {

                    event += "9";
                }
                if (recurso[0].equals("B")) {

                    event += "A";
                }
                if (recurso[0].equals("AB")) {

                    event += "B";
                }
                if (recurso[0].equals("umL")) {

                    event += "C";
                }
                if (recurso[0].equals("AoBn")) {

                    event += "D";
                }
                if (recurso[0].equals("AoB")) {

                    event += "E";
                }
                if (recurso[0].equals("A")) {

                    event += "F";
                }
                /* 
                * Abertura e escrita no arquivo em hexa 
                * Alem da transformacao da string em byte array 
                */
                try {
                    arquivo = new RandomAccessFile("testeula.hex", "rw");
                    arquivo.seek(arquivo.length());
                    byte[] goToArchive = toByteArray(event);
                    arquivo.write(goToArchive);
                    System.out.println(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // System.out.println("-->" + event);
            }

            guarda = ler.nextLine();
        }
        ler.close();

    }
    /*
    * Convoca outras funcoes e deleta o arquivo ja existente a fim de nao poluir os novos valores
    *
    * @param - Parametragem passada se necessaria na convocacao do programa
    */
    public static void main(String[] args) {
        File f = new File("testeula.hex");
        f.delete();
        leitura(0, 0);
    }
}