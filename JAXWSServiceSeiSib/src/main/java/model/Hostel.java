package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "hostel")
@XmlRootElement(namespace = "http://nure.ua/hostel")
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class Hostel {

    private List<Floor> floor;


    public List<Floor> getFloor() {
        return floor;
    }

    @XmlElement(name = "floor", namespace = "http://nure.ua/hostel")
    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }

    public Hostel(List<Floor> floor) {
        this.floor = floor;
    }

    public Hostel() {
        floor = new ArrayList<Floor>();
    }

    @Override
    public String toString() {
        System.out.println("Hostel contains " + getFloor().size());
        for (Floor f :
                getFloor()) {
            System.out.println("  " + f.toString());
            for (Room r :
                    f.getRoom()) {
                System.out.println("    " + r.toString());
                for (Student s :
                        r.getStudent()) {
                    System.out.println("      " + s.toString());
                }
            }
        }
        return null;
    }
}
