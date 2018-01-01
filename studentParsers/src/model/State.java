package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Stack;

@XmlType(name = "state")
@XmlEnum
public enum State {
    @XmlEnumValue("good")
    GOOD("good"),
    @XmlEnumValue("norm")
    NORM("norm"),
    @XmlEnumValue("bad")
    BAD("bad");

    private final String state;

    State(String state) {
        this.state = state;
    }

    public String value() {
        return state;
    }

    public static State fromValue(String state) {
        for (State s : State.values()) {
            if(s.value().equals(state)) return s;
        }
        throw new IllegalArgumentException(state);
    }
}
