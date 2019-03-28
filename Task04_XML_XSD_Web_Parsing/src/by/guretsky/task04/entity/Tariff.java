package by.guretsky.task04.entity;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

public abstract class Tariff {
    private String name;
    private String operator;
    private BigDecimal payroll;
    private XMLGregorianCalendar tariffDate;
    private XMLGregorianCalendar endDate;
    private Parameters parameters;
    private String tariffId;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the operator property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOperator(String value) {
        this.operator = value;
    }

    /**
     * Gets the value of the payroll property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getPayroll() {
        return payroll;
    }

    /**
     * Sets the value of the payroll property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setPayroll(BigDecimal value) {
        this.payroll = value;
    }

    /**
     * Gets the value of the tariffDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getTariffDate() {
        return tariffDate;
    }

    /**
     * Sets the value of the tariffDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setTariffDate(XMLGregorianCalendar value) {
        this.tariffDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the parameters property.
     *
     * @return possible object is
     * {@link Parameters }
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     *
     * @param value allowed object is
     *              {@link Parameters }
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the tariffId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTariffId() {
        return tariffId;
    }

    /**
     * Sets the value of the tariffId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTariffId(String value) {
        this.tariffId = value;
    }
}
