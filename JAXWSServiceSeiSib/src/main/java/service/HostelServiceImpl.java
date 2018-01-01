package service;

import model.Floor;
import model.Hostel;
import model.Room;
import model.Student;
import stax.StAX;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "service.HostelService")
public class HostelServiceImpl implements HostelService {

    private static final String DATASOURCE = "/home/gleb/IdeaProjects/clientjaxwsnew/src/main/java/datasource.xml";

    private StAX stAX = new StAX();

    @Override
    public void createStudent(Student s, int floorId, int roomId) {
        Hostel hostelXMLDatabase = stAX.unmarshall(DATASOURCE);
        for (Floor floor :
                hostelXMLDatabase.getFloor()) {
            for (Room room :
                    floor.getRoom()) {
                if (room.getStudent().contains(s)) {
                    System.out.println("This Student is exists");
                    return;
                }
            }
        }
        for (Floor floor :
                hostelXMLDatabase.getFloor()) {
            if (floor.getId().equals(floorId)) {
                System.out.println("Floor found");
                for (Room room :
                        floor.getRoom()) {
                    if (room.getId().equals(roomId)) {
                        System.out.println("Room found");
                        if (room.getStudent().size() < 4) {
                            room.getStudent().add(s);
                            stAX.marshall(hostelXMLDatabase, DATASOURCE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Student updateStudent(Student s) {
        Hostel hostelXMLDataBase = stAX.unmarshall(DATASOURCE);
        Student studentForUpdate = readStudent(s.getPid());
        if (studentForUpdate != null) {

            for (Floor floor :
                    hostelXMLDataBase.getFloor()) {
                for (Room room :
                        floor.getRoom()) {
                    if (room.getStudent().contains(studentForUpdate)) {
                        room.getStudent().remove(studentForUpdate);
                        room.getStudent().add(s);
                        stAX.marshall(hostelXMLDataBase, DATASOURCE);
                        return studentForUpdate;
                    }

                }
            }
        } else {
            System.out.println("nothing to update");
            return null;
        }
        return null;
    }

    @Override
    public Student readStudent(int id) {
        Hostel hostelXMLDataBase = stAX.unmarshall(DATASOURCE);
        for (Floor floor :
                hostelXMLDataBase.getFloor()) {
            for (Room room :
                    floor.getRoom()) {
                for (Student student :
                        room.getStudent()) {
                    if (student.getPid().equals(id)) {
                        return student;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void deleteStudent(int id) {
        Hostel hostelXMLDataBase = stAX.unmarshall(DATASOURCE);
        for (Floor floor :
                hostelXMLDataBase.getFloor()) {
            for (Room room :
                    floor.getRoom()) {
                for (Student student :
                        room.getStudent()) {
                    if (student.getPid().equals(id)) {
                        room.getStudent().remove(student);
                        stAX.marshall(hostelXMLDataBase, DATASOURCE);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        Hostel hostelXMLDataBase = stAX.unmarshall(DATASOURCE);

        for (Floor floor :
                hostelXMLDataBase.getFloor()) {
            for (Room room :
                    floor.getRoom()) {
                allStudents.addAll(room.getStudent());
            }
        }

        return allStudents;

    }

    @Override
    public Hostel getHostel() {
        return stAX.unmarshall(DATASOURCE);
    }

    @Override
    public int getNextId() {
        List<Student> allStudents = getAllStudents();
        int currentMaxId = 0;
        for (Student student:
             allStudents) {
            if (student.getPid() > currentMaxId) currentMaxId = student.getPid();
        }
        currentMaxId++;
        return currentMaxId;
    }
}
