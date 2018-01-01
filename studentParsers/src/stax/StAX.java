package stax;

import model.*;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;

import static model.Util.*;

public class StAX {

    private static final String NS = "http://nure.ua/hostel";

    public Hostel unmarshall(String filePath) {

        Hostel hostelReturn = new Hostel();
        Floor tempFloor = new Floor();
        Room tempRoom = new Room();

        Floor currentFloor = new Floor();
        Room currentRoom = new Room();
        Student currentStudent = new Student();
        Payment currentPayment = new Payment();
        Subsidy currentSubsidy = new Subsidy();
        Medical currentMedical = new Medical();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equalsIgnoreCase(HOSTEL)) {
                        hostelReturn = new Hostel();

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(FLOOR)) {

                        tempFloor = new Floor();

                        currentFloor = new Floor();
                        Attribute floorId = (startElement.getAttributeByName(new QName("id")));
                        currentFloor.setId(Integer.valueOf(floorId.getValue()));


                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(ROOM)) {

                        tempRoom = new Room();

                        currentRoom = new Room();
                        Attribute roomId = startElement.getAttributeByName(new QName("id"));
                        Attribute roomState = startElement.getAttributeByName(new QName("state"));
                        currentRoom.setId(Integer.valueOf(roomId.getValue()));
                        currentRoom.setState(State.fromValue(roomState.getValue()));


                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(STUDENT)) {

                        currentStudent = new Student();

                        Attribute studentPid = startElement.getAttributeByName(new QName("pid"));
                        Attribute studentStudyForm = startElement.getAttributeByName(new QName("studyForm"));

                        currentStudent.setPid(Integer.valueOf(studentPid.getValue()));

                        if (studentStudyForm != null) {
                            currentStudent.setStudyform(studentStudyForm.getValue());

                        }

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(NAME)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setName(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(SURNAME)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setSurname(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(MIDDLENAME)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setMiddlename(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(PHONE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setPhone(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(EMAIL)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setEmail(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(DOB)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setDob(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(SPEC)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setSpec(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equals(LEVEL)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setLevel(Integer.valueOf(xmlEvent.asCharacters().getData()));

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(GRADE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentStudent.setGrade(Grade.fromValue(xmlEvent.asCharacters().getData()));

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(PAYMENT)) {
                        currentPayment = new Payment();
                        //

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(BALANCE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentPayment.setBalance(Integer.valueOf(xmlEvent.asCharacters().getData()));

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(SUBSIDY)) {
                        currentSubsidy = new Subsidy();
                        //

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(ESTIMATE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentSubsidy.setEstimate(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(PRICE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentSubsidy.setPrice(Integer.valueOf(xmlEvent.asCharacters().getData()));

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(MEDICAL)) {
                        currentMedical = new Medical();
                        //

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(ISEXISTS)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentMedical.setMedicalInfo(Boolean.valueOf(xmlEvent.asCharacters().getData()));

                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase(EXPIRATIONDATE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        currentMedical.setMedicalInfo(xmlEvent.asCharacters().getData());
                    }

                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();

                    if (endElement.getName().getLocalPart().equalsIgnoreCase(SUBSIDY)) {
                        currentPayment.setSubsidy(currentSubsidy);
                        currentSubsidy = null;
                    } else if (endElement.getName().getLocalPart().equalsIgnoreCase(PAYMENT)) {
                        currentStudent.setPayment(currentPayment);
                        currentPayment = null;
                    } else if (endElement.getName().getLocalPart().equalsIgnoreCase(MEDICAL)) {
                        currentStudent.setMedical(currentMedical);
                        currentMedical = null;
                    } else if (endElement.getName().getLocalPart().equalsIgnoreCase(FLOOR)) {
                        hostelReturn.getFloor().add(currentFloor);
                        currentFloor = null;
                    } else if (endElement.getName().getLocalPart().equalsIgnoreCase(ROOM)) {
                        currentFloor.getRoom().add(currentRoom);
                        tempFloor.getRoom().add(currentRoom);
                        currentRoom = null;
                    } else if (endElement.getName().getLocalPart().equalsIgnoreCase(STUDENT)) {
                        currentRoom.getStudent().add(currentStudent);
                        tempRoom.getStudent().add(currentStudent);
                        currentStudent = null;
                    }
                }

            }


        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }



        return hostelReturn;
    }

    public void marshall(Hostel inputHostel, String filePath) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        try {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath));

            xmlStreamWriter.writeStartDocument("utf-8", "1.0");
            xmlStreamWriter.setPrefix("hos", NS);
            xmlStreamWriter.writeStartElement(NS,"hostel");
            xmlStreamWriter.writeNamespace("hos", NS);

            for (Floor f :
                    inputHostel.getFloor()) {
                writeFloor(f, xmlStreamWriter);
            }

            xmlStreamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFloor(Floor floor, XMLStreamWriter writer) {

        try {
            writer.writeStartElement(NS, "floor");
            writer.writeAttribute("id", Integer.toString(floor.getId()));
            for(Room room :
                 floor.getRoom() ) {
                writeRoom(room, writer);
            }
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    private void writeRoom(Room room, XMLStreamWriter writer) {
        try {
            writer.writeStartElement(NS, "room");
            writer.writeAttribute( "id", Integer.toString(room.getId()));
            writer.writeAttribute( "state", room.getState().value());

            for (Student student :
                    room.getStudent()) {
                writeStudent(student, writer);
            }

            writer.writeEndElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeStudent (Student student, XMLStreamWriter writer) {
        try {


            writer.writeStartElement(NS, "student");
            writer.writeAttribute("pid",Integer.toString(student.getPid()));
            writer.writeAttribute("studyForm", student.getStudyform());

            writer.writeStartElement(NS,"name");
            writer.writeCharacters(student.getName());
            writer.writeEndElement();

            writer.writeStartElement(NS,"surname");
            writer.writeCharacters(student.getSurname());
            writer.writeEndElement();

            writer.writeStartElement(NS,"middlename");
            writer.writeCharacters(student.getMiddlename());
            writer.writeEndElement();

            writer.writeStartElement(NS,"phone");
            writer.writeCharacters(student.getPhone());
            writer.writeEndElement();

            writer.writeStartElement(NS,"email");
            writer.writeCharacters(student.getEmail());
            writer.writeEndElement();

            writer.writeStartElement(NS,"dob");
            writer.writeCharacters(student.getDob());
            writer.writeEndElement();

            writer.writeStartElement(NS,"spec");
            writer.writeCharacters(student.getSpec());
            writer.writeEndElement();

            writer.writeStartElement(NS,"level");
            writer.writeCharacters(Integer.toString(student.getLevel()));
            writer.writeEndElement();
            writer.writeStartElement(NS,"grade");
            writer.writeCharacters(student.getGrade().value());
            writer.writeEndElement();

            writePayment(student.getPayment(), writer);

            writeMedical(student.getMedical(), writer);

            writer.writeEndElement();


        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writePayment (Payment payment, XMLStreamWriter writer) {
        try {
            writer.writeStartElement(NS, "payment");
            writer.writeStartElement(NS, "balance");
            writer.writeCharacters(Integer.toString(payment.getBalance()));
            writer.writeEndElement();
            if (payment.getSubsidy() != null) {
                writeSubsidy(payment.getSubsidy(), writer);
            }
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeSubsidy (Subsidy subsidy, XMLStreamWriter writer) {
        try {
            writer.writeStartElement(NS, "subsidy");
            writer.writeStartElement(NS, "estimate");
            writer.writeCharacters(subsidy.getEstimate());
            writer.writeEndElement();
            writer.writeStartElement(NS, "price");
            writer.writeCharacters(Integer.toString(subsidy.getPrice()));
            writer.writeEndElement();
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    private void writeMedical(Medical medical, XMLStreamWriter writer) {
        try {
            writer.writeStartElement(NS, "medical");
            if (medical.getMedicalInfo().getClass() == Boolean.class) {
                writer.writeStartElement(NS, "isExists");
                writer.writeCharacters(medical.getMedicalInfo().toString());
                writer.writeEndElement();
            } else {
                writer.writeStartElement(NS,"expirationDate");
                writer.writeCharacters((String) medical.getMedicalInfo());
                writer.writeEndElement();
            }
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


    }

}
