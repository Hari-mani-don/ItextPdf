package itextPdf3.extractText;

import edu.stanford.nlp.pipeline.*;

import java.util.Properties;

/**
 * <dependency>
    <groupId>edu.stanford.nlp</groupId>
    <artifactId>stanford-corenlp</artifactId>
    <version>4.5.3</version>
</dependency>
<dependency>
    <groupId>edu.stanford.nlp</groupId>
    <artifactId>stanford-corenlp-4.5.3-models</artifactId>
    <version>4.5.3</version>
</dependency> this is not available in maven so we should install maually .

 */
public class SentimentAnalysis {
    public static void main(String[] args) {
        // Sample text to analyze
        String text = "The product is really good and I am happy with it.";

        // Set up pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");

        // Build the pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create an annotation object with the input text
        CoreDocument document = new CoreDocument(text);

        // Annotate the document
        pipeline.annotate(document);

        // Analyze sentiment of each sentence
        document.sentences().forEach(sentence -> {
            String sentiment = sentence.sentiment();
            System.out.println("Sentence: \"" + sentence.text() + "\" has sentiment: " + sentiment);
        });
    }
}

