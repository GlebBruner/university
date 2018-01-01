package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "payment")
public class Payment {

    private Integer balance;

    private Subsidy subsidy;

    public Integer getBalance() {
        return balance;
    }

    @XmlElement(name = "balance", namespace = "http://nure.ua/hostel")
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Subsidy getSubsidy() {
        return subsidy;
    }

    @XmlElement(name = "subsidy", namespace = "http://nure.ua/hostel")
    public void setSubsidy(Subsidy subsidy) {
        this.subsidy = subsidy;
    }

    @Override
    public String toString() {
        if (this.subsidy != null) {
            return "Payment{\n" +
                    "         balance=" + balance + "\n" +
                    "         subsidy =" + subsidy.toString() + "\n";
        } else {
            return "Payment{\n" +
                    "         balance=" + balance + "\n" +
                    "         subsidy = no" + "\n";
        }
    }
}
