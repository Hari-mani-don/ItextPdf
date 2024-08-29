package itextPdf3.fetchImageInUnsplash;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {

    public static void downloadImage(String imageUrl, String outputPath) throws IOException {
    	System.out.println("downloading image..........");
    	URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream in = connection.getInputStream(); FileOutputStream out = new FileOutputStream(outputPath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        
        System.out.println("image downloaded..........");
    }
}

