package sax;

import model.*;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

import static model.Util.*;

public class SAX implements ContentHandler {
    private Hostel wholeHostel;

    private Floor tempFloor;
    private Room tempRoom;

    private Floor currentFloor;
    private Room currentRoom;
    private Student currentStudent;
    private Payment currentPayment;
    private Subsidy currentSubsidy;
    private Medical currentMedical;

    public Hostel unmarshall (String filePath) {

        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(this);
            xmlReader.parse(filePath);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return wholeHostel;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {

    }

    @Override
    public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {


        if (s1.equalsIgnoreCase(Util.HOSTEL)) {
            wholeHostel = new Hostel();
        }

        if (s1.equalsIgnoreCase(FLOOR)) {
            tempFloor = new Floor();

            currentFloor = new Floor();
            currentFloor.setId(Integer.valueOf(attributes.getValue("id")));
            //currentFloor.setId(Integer.valueOf(attributes.ge));
        }
        if (s1.equalsIgnoreCase(ROOM)) {
            tempRoom = new Room();

            currentRoom = new Room();
            currentRoom.setId(Integer.valueOf(attributes.getValue("id")));
            currentRoom.setState(State.fromValue(attributes.getValue("state")));
        }
        if (s1.equalsIgnoreCase(STUDENT)) {
            //tempStudent = new Student();

            currentStudent = new Student();
            currentStudent.setPid(Integer.valueOf(attributes.getValue("pid")));
            currentStudent.setStudyform(attributes.getValue("studyForm"));
        }

        if (s1.equalsIgnoreCase(NAME)) {
            isName = true;
        }
        if (s1.equalsIgnoreCase(SURNAME)) {
            isSurname = true;
        }
        if (s1.equalsIgnoreCase(MIDDLENAME)) {
            isMiddlename = true;
        }
        if (s1.equalsIgnoreCase(PHONE)) {
            isPhone = true;
        }
        if (s1.equalsIgnoreCase(EMAIL)) {
            isEmail = true;
        }
        if (s1.equalsIgnoreCase(DOB)) {
            isDob = true;
        }
        if (s1.equalsIgnoreCase(SPEC)) {
            isSpec = true;
        }
        if (s1.equalsIgnoreCase(LEVEL)) {
            isLevel = true;
        }
        if (s1.equalsIgnoreCase(GRADE)) {
            isGrade = true;
        }
        if (s1.equalsIgnoreCase(PAYMENT)) {
            currentPayment = new Payment();
            isPayment = true;
        }
        if (s1.equalsIgnoreCase(BALANCE)) {
            isBalance = true;
        }
        if (s1.equalsIgnoreCase(SUBSIDY)) {
            currentSubsidy = new Subsidy();
            isSubsudy = true;
        }
        if (s1.equalsIgnoreCase(PRICE)) {
            isPrice = true;
        }
        if (s1.equalsIgnoreCase(ESTIMATE)) {
            isEstimate = true;
        }
        if (s1.equalsIgnoreCase(MEDICAL)) {
            currentMedical = new Medical();
            isMedical = true;
        }
        if (s1.equalsIgnoreCase(ISEXISTS)) {
            isExists = true;
        }
        if (s1.equalsIgnoreCase(EXPIRATIONDATE)) {
            isExpirationDate = true;
        }

    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {

        if (s1.equalsIgnoreCase(FLOOR)) {

            wholeHostel.getFloor().add(currentFloor);
            currentFloor = null;
        }
        if (s1.equalsIgnoreCase(ROOM)) {
            currentFloor.getRoom().add(currentRoom);
            tempFloor.getRoom().add(currentRoom);
            currentRoom = null;
        }
        if (s1.equalsIgnoreCase(STUDENT)) {
            currentRoom.getStudent().add(currentStudent);
            tempRoom.getStudent().add(currentStudent);
            currentStudent = null;
        }

        if (s1.equalsIgnoreCase(NAME)) {
            isName = false;
        }
        if (s1.equalsIgnoreCase(SURNAME)) {
            isSurname = false;
        }
        if (s1.equalsIgnoreCase(MIDDLENAME)) {
            isMiddlename = false;
        }
        if (s1.equalsIgnoreCase(PHONE)) {
            isPhone = false;
        }
        if (s1.equalsIgnoreCase(EMAIL)) {
            isEmail = false;
        }
        if (s1.equalsIgnoreCase(DOB)) {
            isDob = false;
        }
        if (s1.equalsIgnoreCase(SPEC)) {
            isSpec = false;
        }
        if (s1.equalsIgnoreCase(LEVEL)) {
            isLevel = false;
        }
        if (s1.equalsIgnoreCase(GRADE)) {
            isGrade = false;
        }
        if (s1.equalsIgnoreCase(PAYMENT)) {
            currentStudent.setPayment(currentPayment);
            isPayment = false;
        }
        if (s1.equalsIgnoreCase(BALANCE)) {
            isBalance = false;
        }
        if (s1.equalsIgnoreCase(SUBSIDY)) {
            currentPayment.setSubsidy(currentSubsidy);
            isSubsudy = false;
        }
        if (s1.equalsIgnoreCase(PRICE)) {
            isPrice = false;
        }
        if (s1.equalsIgnoreCase(ESTIMATE)) {
            isEstimate = false;
        }
        if (s1.equalsIgnoreCase(MEDICAL)) {
            currentStudent.setMedical(currentMedical);
            isMedical = false;
        }
        if (s1.equalsIgnoreCase(ISEXISTS)) {
            isExists = false;
        }
        if (s1.equalsIgnoreCase(EXPIRATIONDATE)) {
            isExpirationDate = false;
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        String tagValue = new String(chars, i, i1);
        if (isName) {
            currentStudent.setName(tagValue);
        } else if (isSurname) {
            currentStudent.setSurname(tagValue);
        } else if (isMiddlename) {
            currentStudent.setMiddlename(tagValue);
        } else if (isPhone) {
            currentStudent.setPhone(tagValue);
        } else if (isEmail) {
            currentStudent.setEmail(tagValue);
        } else if (isDob) {
            currentStudent.setDob(tagValue);
        } else if (isSpec) {
            currentStudent.setSpec(tagValue);
        } else if (isLevel) {
            currentStudent.setLevel(Integer.valueOf(tagValue));
        } else if (isGrade) {
            currentStudent.setGrade(Grade.fromValue(tagValue));
        } else if (isBalance) {
            currentPayment.setBalance(Integer.valueOf(tagValue));
        } else if (isPrice) {
            currentSubsidy.setPrice(Integer.valueOf(tagValue));
        } else if (isEstimate) {
            currentSubsidy.setEstimate(tagValue);
        } else if (isExists) {
            currentMedical.setMedicalInfo(Boolean.valueOf(tagValue));
        } else if (isExpirationDate) {
            currentMedical.setMedicalInfo(tagValue);
        }
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {

    }

    @Override
    public void processingInstruction(String s, String s1) throws SAXException {

    }

    @Override
    public void skippedEntity(String s) throws SAXException {

    }
}
