package itextPdf3.extractText;

public class TextCleaning {
	public static void main(String[] args) {
		String rawText = "  This is a SAMPLE text with Special Characters!   Let's clean it up...   ";

		// Convert to lowercase
		String cleanedText = rawText.toLowerCase();

		// Remove special characters (keep only letters, digits, and spaces)
		cleanedText = cleanedText.replaceAll("[^a-zA-Z0-9\\s]", "");

		// Normalize whitespace (remove extra spaces)
		cleanedText = cleanedText.trim().replaceAll("\\s+", " ");

		System.out.println("Cleaned Text: " + cleanedText);
	}
}
