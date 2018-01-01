package model;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLtoHTML {

   public static void parse(String filePathXML, String filePathXSL, String filePathHTML) {
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       try {
           Transformer transformer = transformerFactory.newTransformer(new StreamSource(filePathXSL));

           transformer.transform(new StreamSource(filePathXML), new StreamResult(new FileOutputStream(filePathHTML)));


       } catch (FileNotFoundException | TransformerException e) {
           e.printStackTrace();
       }
   }

}
