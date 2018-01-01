
package ua.nure.hostel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import service.Floor;
import service.Hostel;
import service.Room;
import service.Subsidy;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.hostel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Estimate_QNAME = new QName("http://nure.ua/hostel", "estimate");
    private final static QName _Floor_QNAME = new QName("http://nure.ua/hostel", "floor");
    private final static QName _Price_QNAME = new QName("http://nure.ua/hostel", "price");
    private final static QName _Balance_QNAME = new QName("http://nure.ua/hostel", "balance");
    private final static QName _Room_QNAME = new QName("http://nure.ua/hostel", "room");
    private final static QName _Hostel_QNAME = new QName("http://nure.ua/hostel", "hostel");
    private final static QName _Student_QNAME = new QName("http://nure.ua/hostel", "student");
    private final static QName _Subsidy_QNAME = new QName("http://nure.ua/hostel", "subsidy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.hostel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link Medical }
     * 
     */
    public Medical createMedical() {
        return new Medical();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "estimate")
    public JAXBElement<String> createEstimate(String value) {
        return new JAXBElement<String>(_Estimate_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Floor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "floor")
    public JAXBElement<Floor> createFloor(Floor value) {
        return new JAXBElement<Floor>(_Floor_QNAME, Floor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "price")
    public JAXBElement<Integer> createPrice(Integer value) {
        return new JAXBElement<Integer>(_Price_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "balance")
    public JAXBElement<Integer> createBalance(Integer value) {
        return new JAXBElement<Integer>(_Balance_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Room }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "room")
    public JAXBElement<Room> createRoom(Room value) {
        return new JAXBElement<Room>(_Room_QNAME, Room.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hostel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "hostel")
    public JAXBElement<Hostel> createHostel(Hostel value) {
        return new JAXBElement<Hostel>(_Hostel_QNAME, Hostel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Student }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "student")
    public JAXBElement<Student> createStudent(Student value) {
        return new JAXBElement<Student>(_Student_QNAME, Student.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Subsidy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nure.ua/hostel", name = "subsidy")
    public JAXBElement<Subsidy> createSubsidy(Subsidy value) {
        return new JAXBElement<Subsidy>(_Subsidy_QNAME, Subsidy.class, null, value);
    }

}
