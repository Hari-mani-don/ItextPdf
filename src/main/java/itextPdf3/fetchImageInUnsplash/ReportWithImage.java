package itextPdf3.fetchImageInUnsplash;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;


/** If yor are using spring boot Json dependency is enough
 *   <!-- Apache HttpClient for HTTP requests -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.14</version>
        </dependency>

        <!-- JSON library for parsing responses -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>

 */
public class ReportWithImage {

    public static void main(String[] args) {
        try {
            // Fetch and download image
        	System.out.println("Report generating start.........");
            String imageUrl = UnsplashImageSearch.getImageUrl();
            String localImagePath = "downloaded_image.jpg";
            ImageDownloader.downloadImage(imageUrl, localImagePath);

            // Create PDF
            PdfWriter writer = new PdfWriter("report_with_image.pdf");
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Report with Downloaded Image").setBold().setFontSize(24));

            // Add the image to the PDF
            ImageData imageData = ImageDataFactory.create(localImagePath);
            Image image = new Image(imageData);
            document.add(image);

            document.close();
            
            System.out.println("successfully pdf generated........");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * <!-- Apache HttpClient for HTTP requests --> <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpclient</artifactId> <version>4.5.14</version> </dependency>
 * 
 * <!-- JSON library for parsing responses --> <dependency>
 * <groupId>org.json</groupId> <artifactId>json</artifactId>
 * <version>20210307</version> </dependency>
 * 
 * 
 * package com.example.demo.service;
 * 
 * import org.json.JSONArray; import org.json.JSONObject; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.RestTemplate; import
 * org.springframework.web.util.UriComponentsBuilder;
 * 
 * @Service public class ImageService {
 * 
 * private static final String ACCESS_KEY = "YOUR_ACCESS_KEY"; private static
 * final String SEARCH_QUERY = "chart"; // Adjust query as needed
 * 
 * public String getImageUrl() { String url =
 * UriComponentsBuilder.fromHttpUrl("https://api.unsplash.com/search/photos")
 * .queryParam("query", SEARCH_QUERY) .queryParam("client_id", ACCESS_KEY)
 * .toUriString();
 * 
 * RestTemplate restTemplate = new RestTemplate(); String responseBody =
 * restTemplate.getForObject(url, String.class); JSONObject jsonResponse = new
 * JSONObject(responseBody); JSONArray results =
 * jsonResponse.getJSONArray("results"); if (results.length() == 0) { throw new
 * RuntimeException("No images found for the query."); } return
 * results.getJSONObject(0).getJSONObject("urls").getString("regular"); } }
 * package com.example.demo.controller;
 * 
 * import com.itextpdf.kernel.pdf.PdfDocument; import
 * com.itextpdf.kernel.pdf.PdfWriter; import com.itextpdf.layout.Document;
 * import com.itextpdf.layout.element.Image; import
 * com.itextpdf.io.image.ImageData; import
 * com.itextpdf.io.image.ImageDataFactory; import
 * com.example.demo.service.ImageService; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import java.io.File; import java.io.FileOutputStream; import
 * java.io.InputStream; import java.net.HttpURLConnection; import java.net.URL;
 * 
 * @RestController
 * 
 * @RequestMapping("/report") public class ReportController {
 * 
 * @Autowired private ImageService imageService;
 * 
 * @GetMapping("/generate") public String generateReport() { try { // Fetch the
 * image URL String imageUrl = imageService.getImageUrl();
 * System.out.println("Image URL: " + imageUrl);
 * 
 * // Download and save the image File imageFile = downloadImage(imageUrl,
 * "image.jpg");
 * 
 * // Create the PDF try (PdfWriter writer = new
 * PdfWriter("report_with_image.pdf"); PdfDocument pdfDoc = new
 * PdfDocument(writer); Document document = new Document(pdfDoc)) {
 * 
 * document.add(new
 * com.itextpdf.layout.element.Paragraph("Report with Image").setBold().
 * setFontSize(24));
 * 
 * ImageData imageData = ImageDataFactory.create(imageFile.getAbsolutePath());
 * Image image = new Image(imageData); document.add(image); }
 * 
 * return "PDF created successfully!"; } catch (Exception e) {
 * e.printStackTrace(); return "Failed to create PDF."; } }
 * 
 * private File downloadImage(String imageUrl, String fileName) throws Exception
 * { URL url = new URL(imageUrl); HttpURLConnection connection =
 * (HttpURLConnection) url.openConnection(); connection.setRequestMethod("GET");
 * 
 * try (InputStream inputStream = connection.getInputStream(); FileOutputStream
 * fileOutputStream = new FileOutputStream(fileName)) {
 * 
 * byte[] buffer = new byte[1024]; int bytesRead; while ((bytesRead =
 * inputStream.read(buffer)) != -1) { fileOutputStream.write(buffer, 0,
 * bytesRead); } } return new File(fileName); } }
 */
