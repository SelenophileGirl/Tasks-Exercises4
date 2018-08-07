package FileUpload;

import java.io.*;

public abstract class Utility {

    /** Method for receiving the file name from the Client and returning it as a String */
    public static String getFileNameFromClient(InputStream inputStream, DataInputStream dataInputStream) {
        String fileName = "";

        try {

            // Receiving file name from client
            fileName = dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    /** This method is for copying file data from Client into the new file*/
    public static void copyFileData(OutputStream outputStream, DataInputStream dataInputStream) {

        int bytesRead;

        try {

            long size = dataInputStream.readLong();
            byte[] buffer = new byte[ 1024 ];
            while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
