package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@XmlType(name = "medical", namespace = "http://nure.ua/hostel")
public class Medical {

    private Object medicalInfo;

    public Object getMedicalInfo() {
        /*if (this.medicalInfo instanceof LocalDate) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println("get - " + this.medicalInfo.getClass());
            return ((LocalDate) this.medicalInfo).format(dateTimeFormatter);
        } else {
            return (Boolean)this.medicalInfo;
        }*/

        /*if (this.medicalInfo instanceof Date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            return simpleDateFormat.format(this.medicalInfo);
        } else {
            return new Boolean((Boolean) this.medicalInfo);
        }*/

        if (this.medicalInfo instanceof LocalDate) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return ((LocalDate) this.medicalInfo).format(dateTimeFormatter);
        }

        return this.medicalInfo;
    }


    @XmlElements(value = {
            @XmlElement(name = "expirationDate", type = Date.class, namespace = "http://nure.ua/hostel"),
            @XmlElement(name = "isExists", type = Boolean.class, namespace = "http://nure.ua/hostel")
    })
    public void setMedicalInfo(Object medicalInfo) {
        /*if (medicalInfo instanceof  String) {
            String medicalInfoString = new String((String) medicalInfo);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.medicalInfo = LocalDate.parse(medicalInfoString);
        } else {
            this.medicalInfo = (Boolean)medicalInfo;
        }*/


        /*if (medicalInfo instanceof String) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                this.medicalInfo = simpleDateFormat.parse((String) medicalInfo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.medicalInfo = medicalInfo;
        }*/



        if (medicalInfo instanceof String) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.medicalInfo = LocalDate.parse((String)medicalInfo, dateTimeFormatter);
        }

        this.medicalInfo = medicalInfo;

    }

    @Override
    public String toString() {
        return "Medical{\n" +
                "         medicalInfo = " + medicalInfo;
    }
}
