package service;

import model.Hostel;
import model.Student;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface HostelService {

    @WebMethod
    void createStudent(Student s, int floor, int room);

    @WebMethod
    Student updateStudent (Student s);

    @WebMethod
    Student readStudent(int id);

    @WebMethod
    void deleteStudent(int id);

    @WebMethod
    List<Student> getAllStudents();

    @WebMethod
    Hostel getHostel();

    @WebMethod
    int getNextId();

}
