package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@XmlType
public class Subsidy {

    private Integer price;
    //private LocalDate estimate;
    private Date estimate;

    public Integer getPrice() {
        return price;
    }

    @XmlElement(name = "price", namespace = "http://nure.ua/hostel")
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getEstimateLocalDate() {
        return estimate;
    }

    public String getEstimate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = this.estimate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(dateTimeFormatter);
    }

    @XmlElement(name = "estimate", namespace = "http://nure.ua/hostel")
    public void setEstimate(Date estimate) {
        this.estimate = estimate;
    }

    @XmlElement(name = "estimate" , namespace = "http://nure.ua/hostel")
    public void setEstimate(String estimate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFormat = LocalDate.parse(estimate, dateTimeFormatter);
        this.estimate = Date.from(localDateFormat.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Subsidy{\n" +
                "           price=" + price + "\n" +
                "           estimate=" + estimate;
    }
}
