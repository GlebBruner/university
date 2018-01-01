package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "grade")
@XmlEnum
public enum Grade {
    @XmlEnumValue("Bachelor")
    BACHELOR("Bachelor"),
    @XmlEnumValue("Master")
    MASTER("Master"),
    @XmlEnumValue("Phd")
    PHD("Phd");

    private final String grade;

    Grade(String value) {
        this.grade = value;
    }

    public String value() {
        return grade;
    }

    public static Grade fromValue(String value) {
        for (Grade g : Grade.values()) {
            if(g.value().equals(value)) return g;
        }
        throw new IllegalArgumentException(value);
    }
}
