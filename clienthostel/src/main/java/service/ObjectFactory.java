
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _GetAllStudents_QNAME = new QName("http://service/", "getAllStudents");
    private final static QName _UpdateStudent_QNAME = new QName("http://service/", "updateStudent");
    private final static QName _DeleteStudentResponse_QNAME = new QName("http://service/", "deleteStudentResponse");
    private final static QName _UpdateStudentResponse_QNAME = new QName("http://service/", "updateStudentResponse");
    private final static QName _GetHostel_QNAME = new QName("http://service/", "getHostel");
    private final static QName _CreateStudentResponse_QNAME = new QName("http://service/", "createStudentResponse");
    private final static QName _ReadStudent_QNAME = new QName("http://service/", "readStudent");
    private final static QName _ReadStudentResponse_QNAME = new QName("http://service/", "readStudentResponse");
    private final static QName _GetAllStudentsResponse_QNAME = new QName("http://service/", "getAllStudentsResponse");
    private final static QName _GetNextId_QNAME = new QName("http://service/", "getNextId");
    private final static QName _GetHostelResponse_QNAME = new QName("http://service/", "getHostelResponse");
    private final static QName _GetNextIdResponse_QNAME = new QName("http://service/", "getNextIdResponse");
    private final static QName _CreateStudent_QNAME = new QName("http://service/", "createStudent");
    private final static QName _DeleteStudent_QNAME = new QName("http://service/", "deleteStudent");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hostel }
     * 
     */
    public Hostel createHostel() {
        return new Hostel();
    }

    /**
     * Create an instance of {@link Subsidy }
     * 
     */
    public Subsidy createSubsidy() {
        return new Subsidy();
    }

    /**
     * Create an instance of {@link Floor }
     * 
     */
    public Floor createFloor() {
        return new Floor();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link CreateStudentResponse }
     * 
     */
    public CreateStudentResponse createCreateStudentResponse() {
        return new CreateStudentResponse();
    }

    /**
     * Create an instance of {@link GetHostel }
     * 
     */
    public GetHostel createGetHostel() {
        return new GetHostel();
    }

    /**
     * Create an instance of {@link DeleteStudentResponse }
     * 
     */
    public DeleteStudentResponse createDeleteStudentResponse() {
        return new DeleteStudentResponse();
    }

    /**
     * Create an instance of {@link UpdateStudentResponse }
     * 
     */
    public UpdateStudentResponse createUpdateStudentResponse() {
        return new UpdateStudentResponse();
    }

    /**
     * Create an instance of {@link UpdateStudent }
     * 
     */
    public UpdateStudent createUpdateStudent() {
        return new UpdateStudent();
    }

    /**
     * Create an instance of {@link GetAllStudents }
     * 
     */
    public GetAllStudents createGetAllStudents() {
        return new GetAllStudents();
    }

    /**
     * Create an instance of {@link GetAllStudentsResponse }
     * 
     */
    public GetAllStudentsResponse createGetAllStudentsResponse() {
        return new GetAllStudentsResponse();
    }

    /**
     * Create an instance of {@link ReadStudentResponse }
     * 
     */
    public ReadStudentResponse createReadStudentResponse() {
        return new ReadStudentResponse();
    }

    /**
     * Create an instance of {@link ReadStudent }
     * 
     */
    public ReadStudent createReadStudent() {
        return new ReadStudent();
    }

    /**
     * Create an instance of {@link GetNextId }
     * 
     */
    public GetNextId createGetNextId() {
        return new GetNextId();
    }

    /**
     * Create an instance of {@link DeleteStudent }
     * 
     */
    public DeleteStudent createDeleteStudent() {
        return new DeleteStudent();
    }

    /**
     * Create an instance of {@link CreateStudent }
     * 
     */
    public CreateStudent createCreateStudent() {
        return new CreateStudent();
    }

    /**
     * Create an instance of {@link GetHostelResponse }
     * 
     */
    public GetHostelResponse createGetHostelResponse() {
        return new GetHostelResponse();
    }

    /**
     * Create an instance of {@link GetNextIdResponse }
     * 
     */
    public GetNextIdResponse createGetNextIdResponse() {
        return new GetNextIdResponse();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllStudents")
    public JAXBElement<GetAllStudents> createGetAllStudents(GetAllStudents value) {
        return new JAXBElement<GetAllStudents>(_GetAllStudents_QNAME, GetAllStudents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updateStudent")
    public JAXBElement<UpdateStudent> createUpdateStudent(UpdateStudent value) {
        return new JAXBElement<UpdateStudent>(_UpdateStudent_QNAME, UpdateStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteStudentResponse")
    public JAXBElement<DeleteStudentResponse> createDeleteStudentResponse(DeleteStudentResponse value) {
        return new JAXBElement<DeleteStudentResponse>(_DeleteStudentResponse_QNAME, DeleteStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "updateStudentResponse")
    public JAXBElement<UpdateStudentResponse> createUpdateStudentResponse(UpdateStudentResponse value) {
        return new JAXBElement<UpdateStudentResponse>(_UpdateStudentResponse_QNAME, UpdateStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHostel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getHostel")
    public JAXBElement<GetHostel> createGetHostel(GetHostel value) {
        return new JAXBElement<GetHostel>(_GetHostel_QNAME, GetHostel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createStudentResponse")
    public JAXBElement<CreateStudentResponse> createCreateStudentResponse(CreateStudentResponse value) {
        return new JAXBElement<CreateStudentResponse>(_CreateStudentResponse_QNAME, CreateStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "readStudent")
    public JAXBElement<ReadStudent> createReadStudent(ReadStudent value) {
        return new JAXBElement<ReadStudent>(_ReadStudent_QNAME, ReadStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "readStudentResponse")
    public JAXBElement<ReadStudentResponse> createReadStudentResponse(ReadStudentResponse value) {
        return new JAXBElement<ReadStudentResponse>(_ReadStudentResponse_QNAME, ReadStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllStudentsResponse")
    public JAXBElement<GetAllStudentsResponse> createGetAllStudentsResponse(GetAllStudentsResponse value) {
        return new JAXBElement<GetAllStudentsResponse>(_GetAllStudentsResponse_QNAME, GetAllStudentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNextId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getNextId")
    public JAXBElement<GetNextId> createGetNextId(GetNextId value) {
        return new JAXBElement<GetNextId>(_GetNextId_QNAME, GetNextId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHostelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getHostelResponse")
    public JAXBElement<GetHostelResponse> createGetHostelResponse(GetHostelResponse value) {
        return new JAXBElement<GetHostelResponse>(_GetHostelResponse_QNAME, GetHostelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNextIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getNextIdResponse")
    public JAXBElement<GetNextIdResponse> createGetNextIdResponse(GetNextIdResponse value) {
        return new JAXBElement<GetNextIdResponse>(_GetNextIdResponse_QNAME, GetNextIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createStudent")
    public JAXBElement<CreateStudent> createCreateStudent(CreateStudent value) {
        return new JAXBElement<CreateStudent>(_CreateStudent_QNAME, CreateStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteStudent")
    public JAXBElement<DeleteStudent> createDeleteStudent(DeleteStudent value) {
        return new JAXBElement<DeleteStudent>(_DeleteStudent_QNAME, DeleteStudent.class, null, value);
    }

}
