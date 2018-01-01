package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "floor")
public class Floor {
    private List<Room> room;


    private Integer id;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Room> getRoom() {
        return room;
    }

    @XmlElement(name = "room", namespace = "http://nure.ua/hostel")
    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public Floor() {
        room = new ArrayList<Room>();
    }

    public Floor(List<Room> room){this.room = room;}

    @Override
    public String toString() {
        return "floor contains " + getRoom().size() + " rooms. Number(id) " + getId();
    }
}
