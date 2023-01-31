import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String ruta;
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Introduzca la ruta completa del fichero a leer");
        ruta = sc.nextLine();
        InetAddress direccion;
        try {
            direccion = InetAddress.getLocalHost();
            Socket socketClliente = new Socket(direccion, 1500);
            System.out.println("Sevidor: Abriendo flujos de entrada y salida");
            OutputStream os = socketClliente.getOutputStream();
            InputStream is = socketClliente.getInputStream();
            System.out.println("Cliente envia mensaje al servidor");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(ruta);
            bw.newLine();
            bw.flush();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Contenido del fichero: " + br.readLine());
            osw.close();
            is.close();
            os.close();
            bw.close();
            isr.close();
            br.close();
            socketClliente.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
