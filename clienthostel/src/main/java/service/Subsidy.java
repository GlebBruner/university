
package service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subsidy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subsidy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://nure.ua/hostel}estimate" minOccurs="0"/>
 *         &lt;element ref="{http://nure.ua/hostel}price" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subsidy", propOrder = {
    "estimate",
    "price"
})
public class Subsidy {

    @XmlElement(namespace = "http://nure.ua/hostel")
    protected String estimate;
    @XmlElement(namespace = "http://nure.ua/hostel")
    protected Integer price;

    /**
     * Gets the value of the estimate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstimate() {
        return estimate;
    }

    /**
     * Sets the value of the estimate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstimate(String value) {
        this.estimate = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrice(Integer value) {
        this.price = value;
    }

}
