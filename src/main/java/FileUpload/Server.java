package FileUpload;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        Socket socket = null;
        ServerSocket server = null; // Listening for requests


        try {
            server = new ServerSocket(2020);
            while(true) {
                System.out.println("Server is listening on port 2050");
                socket = server.accept();
                System.out.println("Connection established with " + socket.getInetAddress());

                new ServerThread(socket).start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                server.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
