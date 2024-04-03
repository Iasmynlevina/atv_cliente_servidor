package Socket;

import java.io.*;
import java.net.*;
public class Servidor {

    public static void main(String[] args) {
        int porta = 8080; // Porta para escutar

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor vai escutar na porta " + porta);

            while (true) {
                Socket clientSocket = servidorSocket.accept();
                System.out.println("Conexão recebida de " + clientSocket.getInetAddress());

                // Recebe a imagem do cliente
                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                String caminhoImagem = "socket/girassois.jpg";

                try (FileOutputStream fileOutputStream = new FileOutputStream(caminhoImagem)) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                // Envia o caminho da imagem de volta ao cliente
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(caminhoImagem.getBytes());

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


