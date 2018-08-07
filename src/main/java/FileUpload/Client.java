package FileUpload;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        InputStream inputStream = null;

        int x = 0;
        while (x < 5) {
            try {
                socket = new Socket("127.0.0.1", 2020); // localhost , port returns socket on the client side
                System.out.println("connection established with server");

                inputStream = socket.getInputStream();

            } catch (Exception e) {
                e.printStackTrace();
            }


            BufferedInputStream bufferedInputStream = null;
            FileInputStream fileInputStream = null;
            BufferedOutputStream bufferedOutputStream = null;
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;


            if (inputStream != null) {

                try {

                    // Getting file path from user
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter the PATH of the file you want to upload!");
                    String fileToUpload = scanner.nextLine();
                    System.out.println("Uploading " + fileToUpload);


                    File myFile = new File(fileToUpload);
                    byte[] myByteArray = new byte[ (int) myFile.length() ];

                    fileInputStream = new FileInputStream(fileToUpload);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    dataInputStream = new DataInputStream(bufferedInputStream);

                    dataInputStream.readFully(myByteArray, 0, myByteArray.length);


                    // Sending file name and file size to the server
                    bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    dataOutputStream = new DataOutputStream(bufferedOutputStream);

                    dataOutputStream.writeUTF(myFile.getName());
                    dataOutputStream.writeLong(myByteArray.length);


                    // Sending file data to the server
                    bufferedOutputStream.write(myByteArray, 0, myByteArray.length);

                    bufferedOutputStream.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        dataOutputStream.close();
                        bufferedOutputStream.close();
                        dataInputStream.close();
                        bufferedInputStream.close();
                        fileInputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            x++;
        }


    }

}
