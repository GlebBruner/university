package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "room")
public class Room {

    private Integer id;

    private State state;

    private List<Student> student;

    public List<Student> getStudent() {
        return student;
    }

    @XmlElement(name = "student", namespace = "http://nure.ua/hostel")
    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "id", required = true)
    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    @XmlAttribute(name = "state")
    public void setState(State state) {
        this.state = state;
    }

    public Room(List<Student> student) {
        this.student = student;
    }

    public Room() {
        student = new ArrayList<Student>() ;
    }

    @Override
    public String toString() {
        return "Room contains " + getStudent().size() + " students. State " + getState() + " number(id) " + getId();
    }
}
