
package jaxb;

import model.Hostel;
import model.myNameSpaceWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class JAXB {

    public void marshal(Hostel hostel, String filepath){
        OutputStream fileToWriteTo = null;
        try {
            fileToWriteTo = new FileOutputStream(filepath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Hostel.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new myNameSpaceWrapper());
            //marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");

            marshaller.marshal(hostel, fileToWriteTo);
            marshaller.marshal(hostel, System.out);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
        } finally {
            try {
                fileToWriteTo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Hostel unmarshal(String filePath) {
        try {
            File inputXML = new File(filePath);
            JAXBContext jaxbContext = null;
            jaxbContext = JAXBContext.newInstance(Hostel.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Hostel) unmarshaller.unmarshal(inputXML);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
