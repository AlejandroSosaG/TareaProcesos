import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        String contenido = null;
        System.out.println("Sevidor: Abriendo conexión");
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(50000);
            System.out.println("Sevidor: aceptando conexión");
            Socket socketCliente = socketServidor.accept();
            System.out.println("Sevidor: Abriendo flujos de entrada y salida");
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Mensaje enviado por el cliente: " + br.readLine());
            File archivo = new File (br.readLine());
            FileReader fr = new FileReader (archivo);
            String linea;
            while ((linea = br.readLine())!=null) contenido += linea;
            System.out.println("Sevidor envia mensaje al cliente");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(contenido);
            bw.newLine();
            bw.flush();
            if (fr != null) fr.close();
            br.close();
            isr.close();
            is.close();
            os.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
