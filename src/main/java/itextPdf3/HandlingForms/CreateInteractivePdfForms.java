package itextPdf3.HandlingForms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class CreateInteractivePdfForms {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String args[]) {
		try {
// Create a PDF document with a writer
			PdfWriter writer = new PdfWriter("form.pdf");
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);

			// Add content to the document
			document.add(new Paragraph("Please fill out the following form:"));

			// Create an AcroForm
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

			// Create form fields
			PdfFormField nameField = PdfFormField
					.createText(pdfDoc, new com.itextpdf.kernel.geom.Rectangle(50, 750, 200, 20), "name", "")
					.setColor(ColorConstants.RED);
			nameField.setFieldName("Name");

			PdfFormField ageField = PdfFormField.createText(pdfDoc,
					new com.itextpdf.kernel.geom.Rectangle(50, 700, 200, 20), "age", "");
			ageField.setFieldName("Age");

			// Add fields to the form
			form.addField(nameField);
			form.addField(ageField);

			// Close the document
			document.close();
			logger.info("PDF form created successfully.");

			// Fill out the form programmatically
			PdfDocument filledPdfDoc = new PdfDocument(new PdfReader("form.pdf"), new PdfWriter("filled_form.pdf"));
			PdfAcroForm filledForm = PdfAcroForm.getAcroForm(filledPdfDoc, true);
			filledForm.getField("Name").setValue("John Doe");
			filledForm.getField("Age").setValue("28");

			// Flatten the form so that fields become static content
			filledForm.flattenFields();

			filledPdfDoc.close();
			logger.info("Form filled successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//import com.itextpdf.forms.PdfAcroForm;
//import com.itextpdf.forms.fields.PdfFormField;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//
//public class ValidatePdfFormInputs {
//    public static void main(String[] args) {
//        try {
//            // Open the form for filling
//            PdfDocument pdfDoc = new PdfDocument(new PdfReader("form.pdf"), new PdfWriter("validated_form.pdf"));
//            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
//
//            // Validate the inputs
//            String name = "John Doe";
//            String age = "28";
//
//            if (name.isEmpty()) {
//                System.out.println("Name cannot be empty.");
//                return;
//            }
//
//            if (!age.matches("\\d+")) {
//                System.out.println("Age must be a number.");
//                return;
//            }
//
//            // Fill the form if inputs are valid
//            form.getField("Name").setValue(name);
//            form.getField("Age").setValue(age);
//
//            // Flatten the form to make it static
//            form.flattenFields();
//
//            pdfDoc.close();
//            System.out.println("Form validated and filled successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
