package model;

import javax.xml.bind.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@XmlType(name = "student", propOrder = {"name", "surname", "middlename", "phone", "email", "dob", "spec",
        "level", "grade", "payment", "medical", "pid", "studyform"}, namespace = "http://nure.ua/hostel")
public class Student {

    private String name;
    private String surname;
    private String middlename;
    private String phone;
    private String email;
    private Date dob;
    private String spec;
    private Integer level;
    private Grade grade;
    private Payment payment;
    private Medical medical;
    private Integer pid;
    private String studyform;



    public String getDob() {
        //insert date to localdate converter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(dateTimeFormatter);

    }

    /*@XmlElement(name = "dob", namespace = "http://nure.ua/hostel")
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }*/

    @XmlElement(name = "dob", namespace = "http://nure.ua/hostel")
    public void setDob(Date dob) {
        this.dob = dob;
    }

    @XmlElement(name = "dob", namespace = "http://nure.ua/hostel") //
    public void setDob(String date) {

        //insert from localdate to date converter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDateFormat = LocalDate.parse(date, dateTimeFormatter);
        this.dob = Date.from(localDateFormat.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Integer getPid() {
        return pid;
    }

    @XmlAttribute(name = "pid", required = true)
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStudyform() {
        return studyform;
    }

    @XmlAttribute(name = "studyForm")
    public void setStudyform(String studyform) {
        this.studyform = studyform;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name", namespace = "http://nure.ua/hostel")
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(name = "surname", namespace = "http://nure.ua/hostel")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    @XmlElement(name = "middlename", namespace = "http://nure.ua/hostel")
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "phone", namespace = "http://nure.ua/hostel")
    public void setPhone(String phone) {
        StringBuilder goodPhone = new StringBuilder(phone);
        if (!phone.startsWith("(")) {
            goodPhone.insert(0,"(");
            goodPhone.insert(4,")");
            goodPhone.insert(5," ");
            goodPhone.insert(9,"-");
            goodPhone.insert(12,"-");
        }
        this.phone = goodPhone.toString();
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email", namespace = "http://nure.ua/hostel")
    public void setEmail(String email) {
        this.email = email;
    }



    public String getSpec() {
        return spec;
    }

    @XmlElement(name = "spec", namespace = "http://nure.ua/hostel")
    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getLevel() {
        return level;
    }

    @XmlElement(name = "level" ,namespace = "http://nure.ua/hostel")
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Grade getGrade() {
        return grade;
    }

    @XmlElement(name  = "grade", namespace = "http://nure.ua/hostel")
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Payment getPayment() {
        return payment;
    }

    @XmlElement(name = "payment", namespace = "http://nure.ua/hostel")
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Medical getMedical() {
        return medical;
    }

    @XmlElement(name  = "medical" , namespace = "http://nure.ua/hostel")
    public void setMedical(Medical medical) {
        this.medical = medical;
    }


    @Override
    public String toString() {
        return "Student [\n" +
                "       name=" + name + " " + surname + " " + middlename + "\n" +
                "       phone=" + phone + "\n" +
                "       email=" + email + "\n" +
                "       dob=" + dob + "\n" +
                "       spec=" + spec + "\n" +
                "       level=" + level + "\n" +
                "       grade=" + grade + "\n" +
                "       payment=" + payment +
                "       medical=" + medical + "\n" +
                "       pid=" + pid + "\n" +
                "       studyform='" + studyform + "\n" +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if ( o == null || !(o instanceof Student)) return false;
        Student student = (Student)o;

        if (!this.pid.equals(student.pid)) return false;
        if (!this.name.equals(student.name)) return false;
        if (!this.surname.equals(student.surname)) return false;
        return this.middlename.equals(student.middlename);
    }
}
