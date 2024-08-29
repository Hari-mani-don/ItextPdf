package itextPdf3.handlingMetaData;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

public class DrawingAnnotation {
	public static void main(String[] args) {
		try {
			String src = "footer.pdf"; // Path to your source PDF
			String dest = "radio_button_output.pdf"; // Path to the output PDF

			// Initialize PdfDocument
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

			// Get or create the AcroForm
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

			// Create a group for the radio buttons
			String groupName = "radioGroup";

			// Create radio button form fields
			PdfTextFormField radioButton1 = PdfButtonFormField.createText(pdfDoc, new Rectangle(100, 750, 20, 20),
					groupName, "Option1");
			radioButton1.setValue("Option1");
			form.addField(radioButton1);

			PdfTextFormField radioButton2 = PdfButtonFormField.createText(pdfDoc, new Rectangle(100, 720, 20, 20),
					groupName, "Option2");
			radioButton2.setValue("Option2");
			form.addField(radioButton2);

			PdfTextFormField radioButton3 = PdfButtonFormField.createText(pdfDoc, new Rectangle(100, 690, 20, 20),
					groupName, "Option3");
			radioButton3.setValue("Option3");
			form.addField(radioButton3);

			// Close the document
			pdfDoc.close();
			System.out.println("Form fields added successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
