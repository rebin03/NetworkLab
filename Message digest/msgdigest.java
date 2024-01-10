import java.io.FileInputStream;
import java.security.MessageDigest;

public class msgdigest {
    public static void main(String[] args) {
        // Replace "your_file_path.txt" with the path to your file
        String filePath = "C:\\Users\\LENOVO\\OneDrive\\Desktop\\lp\\input.txt";
        try {
            byte[] fileData = readFileData(filePath);
            String digest = generateDigest(fileData);

            System.out.println("Message Digest: " + digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readFileData(String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, bytesRead));
        }

        fileInputStream.close();
        return stringBuilder.toString().getBytes();
    }

    private static String generateDigest(byte[] data) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data);

        byte[] digestBytes = messageDigest.digest();

        // Convert the byte array to a hexadecimal string
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digestBytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }
}

