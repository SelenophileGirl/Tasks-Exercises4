package FileUpload;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        DataInputStream dataInputStream = null;


        try {

            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            // Creating the new file in server predefined folder
            String serverDirectory = "E:\\Atypon\\Uploaded Files\\";
            File myFile;
            myFile = new File(serverDirectory + Utility.getFileNameFromClient(inputStream, dataInputStream));

            // Copying data to new file
            outputStream = new FileOutputStream(myFile);

            Utility.copyFileData(outputStream, dataInputStream);


            System.out.println(myFile.getName() + " has been uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }




}