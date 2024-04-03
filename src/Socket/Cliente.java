package Socket;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        String enderecoServidor = "127.0.0.1";
        int porta = 8080;

        try (Socket clientSocket = new Socket(enderecoServidor, porta)) {

            OutputStream outputStream = clientSocket.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream("socket/girassoi.jpg");
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }


            InputStream inputStream = clientSocket.getInputStream();
            byte[] caminhoBytes = new byte[1024];
            int caminhoLength = inputStream.read(caminhoBytes);
            String caminhoImagem = new String(caminhoBytes, 0, caminhoLength);

            System.out.println("Imagem salva em: " + caminhoImagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
