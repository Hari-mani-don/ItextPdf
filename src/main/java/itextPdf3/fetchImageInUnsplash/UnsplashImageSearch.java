package itextPdf3.fetchImageInUnsplash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * HTTP Requests: To interact with the Unsplash API and download images.
 * JSON Parsing: To parse the API responses.
 * PDF Creation: To generate PDF files with the downloaded images.
 * 
 * Unsplash account details:
 * Application Id : 647138
 * Access key : fJKNmY0bZ4VwtV05HtU-SwsVSNNRahMJDqPDvgjkt8s
 * Secret key : W-lrosT8kc-MHKbBNjLq45AN8IZyCo4M06Cy7dyIQgo
 */
public class UnsplashImageSearch {

    private static final String ACCESS_KEY = "fJKNmY0bZ4VwtV05HtU-SwsVSNNRahMJDqPDvgjkt8s";
    private static final String SEARCH_QUERY = "barchart"; // Search query

    public static String getImageUrl() throws Exception {
    	System.out.println("Fetching image..........");
        String urlString = "https://api.unsplash.com/search/photos?query=" + SEARCH_QUERY.replace(" ", "%20")
                + "&client_id=" + ACCESS_KEY;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray results = jsonResponse.getJSONArray("results");
        if (results.length() == 0) {
            throw new RuntimeException("No images found for the query.");
        }

        // Get the URL of the first image
        String imageUrl = results.getJSONObject(0).getJSONObject("urls").getString("regular");

        return imageUrl;
    }
}
