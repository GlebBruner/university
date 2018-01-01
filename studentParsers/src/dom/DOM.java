package dom;

import model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOM  {

    //Marshaller into XML
    //Unmarshaller into JavaBean

    public static final String HO_NS = "http://nure.ua/hostel";
    public static final String PREFIX = "hos";

    public Element getFloorsFromHostel(Floor floor, Document document) {
      Element floorElement = document.createElementNS(HO_NS, "floor");
        floorElement.setPrefix(PREFIX);
        floorElement.setAttribute("id", String.valueOf(floor.getId()));
        for (Room r : floor.getRoom()) {
            floorElement.appendChild(getRoomFromFloor(r, document));
        }
        return floorElement;
    }

    public Element getRoomFromFloor(Room room, Document document) {
        Element roomElement = document.createElementNS(HO_NS, "room");
        roomElement.setPrefix(PREFIX);
        roomElement.setAttribute("id", String.valueOf(room.getId()));
        roomElement.setAttribute("state", room.getState().value());
        for (Student student : room.getStudent()) {
            roomElement.appendChild(getStudentFromRoom(student, document));
        }
        return roomElement;
    }

    public Element getStudentFromRoom(Student student, Document document) {
        Element studentElement = document.createElementNS(HO_NS, "student");
        studentElement.setPrefix(PREFIX);
        studentElement.setAttribute("pid", String.valueOf(student.getPid()));
        studentElement.setAttribute("studyForm", String.valueOf(student.getStudyform()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "name", student.getName()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "surname", student.getSurname()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "middlename", student.getMiddlename()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "phone", student.getPhone()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "email", student.getEmail()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "dob", student.getDob()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "spec", student.getSpec()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "level", student.getLevel()));
        studentElement.appendChild(getSimpleType(document, HO_NS, "grade", student.getGrade().value()));
        studentElement.appendChild(getPayment(student.getPayment(), document));
        studentElement.appendChild(getMedical(student.getMedical(), document));
        return studentElement;
    }

    public  Element getPayment(Payment payment, Document document) {
        Element paymentElement = document.createElementNS(HO_NS, "payment");
        paymentElement.setPrefix(PREFIX);
        paymentElement.appendChild(getSimpleType(document, HO_NS, "balance", payment.getBalance()));

        if (payment.getSubsidy() != null) {
            paymentElement.appendChild(getSubsidy(payment.getSubsidy(), document));
        }
        //paymentElement.appendChild(getSubsidy(payment.getSubsidy(), document));
        return paymentElement;
    }

    public Element getSubsidy(Subsidy subsidy, Document document) {

        if(subsidy != null){


            Element subsidyElement = document.createElementNS(HO_NS, "subsidy");
            subsidyElement.setPrefix(PREFIX);

            if (subsidyElement != null) {
                subsidyElement.appendChild(getSimpleType(document, HO_NS, "price", subsidy.getPrice()));
                subsidyElement.appendChild(getSimpleType(document, HO_NS, "estimate", subsidy.getEstimate()));
                return subsidyElement;
            }

        }
        return null;


        /*subsidyElement.appendChild(getSimpleType(document, HO_NS, "price", subsidy.getPrice()));
        subsidyElement.appendChild(getSimpleType(document, HO_NS, "estimate", subsidy.getEstimate()));*/
        //return subsidyElement;
    }

    public Element getMedical(Medical medical, Document document) {
        Element medicalElement = document.createElementNS(HO_NS, "medical");
        medicalElement.setPrefix(PREFIX);
        //if (medical.getMedicalInfo().getClass() == Boolean.class) {
            if (medical.getMedicalInfo().getClass().equals(Boolean.class)) {
            medicalElement.appendChild(getSimpleType(document, HO_NS, "isExists", medical.getMedicalInfo()));
            return medicalElement;
        } else {
            medicalElement.appendChild(getSimpleType(document, HO_NS, "expirationDate", medical.getMedicalInfo()));
            return medicalElement;
        }
    }


    public Element getSimpleType(Document document, String ns, String tagName, Object tagValue) {
        Element simpleType = document.createElementNS(ns, tagName);
        simpleType.setPrefix(PREFIX);
        simpleType.setTextContent(String.valueOf(tagValue));
        return simpleType;

    }

    public void marshal (String filePath, Hostel hostel) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(true);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(new File("src/hostel.xsd"));
            documentBuilderFactory.setSchema(schema);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElementHostel = document.createElementNS(HO_NS, "hostel");
            rootElementHostel.setPrefix(PREFIX);



            for (Floor floor : hostel.getFloor()) {
                rootElementHostel.appendChild(getFloorsFromHostel(floor, document));
            }
            document.appendChild(rootElementHostel);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));
            transformer.transform(domSource, streamResult);


        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }


    }


    public Hostel unmarshall (String filePath) {

        Hostel returnHostel = new Hostel();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document inputDocument = documentBuilder.parse(new File(filePath));

            /*SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src/hostel.xsd"));
            Validator validator = schema.newValidator(); //TODO
            validator.validate(new DOMSource(inputDocument));
*/
            if (inputDocument != null) {

                Element hostelElement = inputDocument.getDocumentElement();

                if (hostelElement != null) {
                    NodeList floorsNodes = hostelElement.getChildNodes();
                    //NodeList floorsNodes = hostelElement.getElementsByTagNameNS(HO_NS, "floor"); // получили 4 этажа
                    List<Floor> floorsInHostel = new ArrayList<>(); //чтобы хостелу засетить список этажей


                    for (int iFloorNode = 0; iFloorNode < floorsNodes.getLength(); iFloorNode++) { // для КАЖДОГО ЭТАЖА!

                        NodeList roomNodes = floorsNodes.item(iFloorNode).getChildNodes(); //.. получаем 44 КОМНАТЫ
                        List<Room> roomsAtFloor = new ArrayList<>(); //чтобы этажу засетить список комнат

                        if (roomNodes.getLength() != 0) {

                            for (int iRoomNode = 0; iRoomNode < roomNodes.getLength(); iRoomNode++) { //.. для КАЖДОЙ комнаты

                                NodeList studentNodes = roomNodes.item(iRoomNode).getChildNodes(); //.. получаем СТУДЕНТОВ
                                List<Student> studentsInRoom = new ArrayList<>(); //чтобы комнате засетить список студентов

                                if (studentNodes.getLength() != 0) {

                                    for (int iStudentNode = 0; iStudentNode < studentNodes.getLength(); iStudentNode++) {

                                        //получаем студента

                                        if (studentNodes.item(iStudentNode).getNodeType() == Node.ELEMENT_NODE) {
                                            Student studentFromRoom = parseStudentNodes((Element) studentNodes.item(iStudentNode));
                                            studentsInRoom.add(studentFromRoom);
                                        }

                                        //Student studentFromRoom = parseStudentNodes((Element) studentNodes.item(iStudentNode));
                                        //добавляем его в список студентов комнаты
                                        //studentsInRoom.add(studentFromRoom);

                                    }
                                    Room room = new Room(studentsInRoom); //рума, которую добавим в список рум для этажа
                                    NamedNodeMap roomAttributes = roomNodes.item(iRoomNode).getAttributes();
                                    room.setId(Integer.valueOf(roomAttributes.getNamedItem("id").getTextContent()));
                                    //room.setId(Integer.valueOf(roomAttributes.getNamedItemNS(HO_NS, "id").getTextContent()));
                                    room.setState(State.fromValue(roomAttributes.getNamedItem("state").getTextContent()));
                                    roomsAtFloor.add(room); //добавляем рум в список рум этажа
                                }
                            }
                            Floor floor = new Floor(roomsAtFloor); //этаж, который добавим в список этажей хостела
                            NamedNodeMap floorAttributes = floorsNodes.item(iFloorNode).getAttributes();
                            floor.setId(Integer.valueOf(floorAttributes.getNamedItem("id").getTextContent()));
                            floorsInHostel.add(floor); //добавляем этаж в список этажей
                        }
                    }

                    returnHostel.setFloor(floorsInHostel); //сетим хостелу список этажей
                }

            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return returnHostel;
    }

    public Student parseStudentNodes (Element student) {
        NodeList studentNodes = student.getChildNodes();

        Student studentToReturn = new Student();

        Payment paymentForStudent = new Payment();
        NodeList paymentNodes = studentNodes.item(9).getChildNodes();

        Subsidy subsidyForPayment = new Subsidy();


        //was without if

        if (paymentNodes.getLength() != 1) {
            NodeList subsidy = paymentNodes.item(1).getChildNodes();
            subsidyForPayment.setPrice(Integer.valueOf(subsidy.item(0).getTextContent()));
            subsidyForPayment.setEstimate(subsidy.item(1).getTextContent());
        }

        paymentForStudent.setBalance(Integer.valueOf(paymentNodes.item(0).getTextContent()));
        paymentForStudent.setSubsidy(subsidyForPayment);


        Medical medicalForStudent = new Medical();
        NodeList medicalNodes = studentNodes.item(10).getChildNodes();

        if (medicalNodes.item(0).getTextContent().equals("true") || medicalNodes.item(0).getTextContent().equals("false")) {
            Boolean forSet = Boolean.valueOf(medicalNodes.item(0).getTextContent());
            medicalForStudent.setMedicalInfo(forSet);
        } else {
            medicalForStudent.setMedicalInfo(medicalNodes.item(0).getTextContent());

        }
        //medicalForStudent.setMedicalInfo(medicalNodes.item(0).getTextContent());

        studentToReturn.setName(studentNodes.item(0).getTextContent());
        studentToReturn.setSurname(studentNodes.item(1).getTextContent());
        studentToReturn.setMiddlename(studentNodes.item(2).getTextContent());
        studentToReturn.setPhone(studentNodes.item(3).getTextContent());
        studentToReturn.setEmail(studentNodes.item(4).getTextContent());
        studentToReturn.setDob(studentNodes.item(5).getTextContent());
        studentToReturn.setSpec(studentNodes.item(6).getTextContent());
        //studentToReturn.setLevel(Integer.valueOf(studentNodes.item(7).getNodeValue()));
        studentToReturn.setLevel(Integer.valueOf(studentNodes.item(7).getTextContent()));
        studentToReturn.setGrade(Grade.fromValue(studentNodes.item(8).getTextContent()));
        studentToReturn.setPayment(paymentForStudent);
        studentToReturn.setMedical(medicalForStudent);
        //studentToReturn.setPid(Integer.valueOf(student.getAttributeNS(HO_NS, "pid")));
        studentToReturn.setPid(Integer.valueOf(student.getAttribute("pid")));
        studentToReturn.setStudyform(student.getAttribute("studyForm"));

        return studentToReturn;

    }


}