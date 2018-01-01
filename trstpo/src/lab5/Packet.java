package lab5;

public class Packet {

    private int id;
    private String body;
    private Object sender;
    private Object receiver;


    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    void setBody(String body) {
        this.body = body;
    }

    Object getSender() {
        return sender;
    }

    void setSender(Object sender) {
        this.sender = sender;
    }

    public Object getReciever() {
        return receiver;
    }

    void setReciever(Object reciever) {
        this.receiver = reciever;
    }
}
