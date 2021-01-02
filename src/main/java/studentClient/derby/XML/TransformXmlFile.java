import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class TransformXmlFile {
	// Change these file names as appropriate
	public static String XSLFILE = "XmlToUnnested.xsl";
	public static String INFILE  = "students.xml";
	public static String OUTFILE = "xx.txt";

    public static void main(String[] args) {
		try {
			System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");

			// Create Source and Result objects for the files
			Source xsl    = new StreamSource(new FileReader(XSLFILE));
			Source input  = new StreamSource(new FileReader(INFILE));
			Result output = new StreamResult(new FileWriter(OUTFILE));

			// Transform the INFILE to the OUTFILE
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer(xsl);
			trans.transform(input, output);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
