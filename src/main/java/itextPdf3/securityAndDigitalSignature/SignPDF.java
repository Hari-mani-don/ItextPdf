package itextPdf3.securityAndDigitalSignature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;

public class SignPDF {
	public static void main(String[] args) throws IOException, GeneralSecurityException {
	
			// Add BouncyCastle as a security provider
			Security.addProvider(new BouncyCastleProvider());
			if (Security.getProvider("BC") != null) {
				System.out.println("Bouncy Castle provider is registered.");
			} else {
				System.out.println("Bouncy Castle provider is not registered.");
			}
			String src = "footer.pdf";
			String dest = "signed_output.pdf";
			String keystorePath = "/home/hari/Documents/keystore.p12"; // Path to your .p12 file
			char[] password = "123456".toCharArray(); // Password for the .p12 file

			// Load the keystore
			KeyStore ks = KeyStore.getInstance("PKCS12", "BC"); // Specify BouncyCastle as the provider
			ks.load(new FileInputStream(keystorePath), password);
			String alias = ks.aliases().nextElement();
			PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
			Certificate[] chain = ks.getCertificateChain(alias);

			// Sign the PDF
			PdfReader reader = new PdfReader(src);
			PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), false);

			// Define signature appearance
			Rectangle rect = new Rectangle(36, 648, 200, 100);
			PdfSignatureAppearance appearance = signer.getSignatureAppearance();
			appearance.setReason("Test Signing").setLocation("Chennai").setPageNumber(1)
					.setRenderingMode(PdfSignatureAppearance.RenderingMode.DESCRIPTION).setPageRect(rect);

			// Sign the document
			PrivateKeySignature pks = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, "BC");
			BouncyCastleDigest digest = new BouncyCastleDigest();
			signer.signDetached(digest, pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);

			System.out.println("PDF signed successfully.");
		
	}
}
