package itextPdf3.barCodeAndQRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

/**
 * <dependency> <groupId>com.google.zxing</groupId>
 * <artifactId>core</artifactId> <version>3.4.1</version> </dependency>
 * <dependency> <groupId>com.google.zxing</groupId>
 * <artifactId>javase</artifactId> <version>3.4.1</version> </dependency>
 * 
 * 
 * BarcodeFormat.CODE_128: Standard product barcodes.
 *  BarcodeFormat.EAN_13:  International product codes. 
 *  BarcodeFormat.CODE_39: Used in logistics and  inventory systems.
 * 
 */
public class BarcodeAndQRCodeToPDF {
	public static void main(String[] args) throws WriterException {
		try {
			String pdfPath = "BarcodeQRCodeExample.pdf";
			PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);

			// Generate Barcode
			Image barcodeImage = generateBarcodeImage("https://github.com/Hari-mani-don/");
			document.add(new Paragraph("Generated Barcode:"));
			document.add(barcodeImage);

			// Add space between images
			document.add(new Paragraph("\n\n"));
			String biodata = "Name: Selvamani\nPhone: +919361931850\nEmail: binaryDefender@gmail.com";
			// Generate QR Code
			Image qrCodeImage = generateQRCodeImage(biodata);
			document.add(new Paragraph("Generated QR Code:"));
			document.add(qrCodeImage);

			document.close();
			System.out.println("PDF created with barcode and QR code.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Image generateBarcodeImage(String text) throws IOException, WriterException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, 200, 100);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
		ImageData imageData = ImageDataFactory.create(byteArrayOutputStream.toByteArray());
		return new Image(imageData);
	}

	private static Image generateQRCodeImage(String text) throws IOException, WriterException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
		ImageData imageData = ImageDataFactory.create(byteArrayOutputStream.toByteArray());
		return new Image(imageData);
	}
}
