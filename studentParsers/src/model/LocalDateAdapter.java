package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(dateTimeFormatter);
    }
}
