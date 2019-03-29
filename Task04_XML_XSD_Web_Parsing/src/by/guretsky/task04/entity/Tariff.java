package by.guretsky.task04.entity;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Objects;

public abstract class Tariff {
    private String name;
    private String operator;
    private Double payroll;

    //TODO: DATE
    private String tariffDate;
    private String endDate;

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
     * {@link Double }
     */
    public Double getPayroll() {
        return payroll;
    }

    /**
     * Sets the value of the payroll property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setPayroll(Double value) {
        this.payroll = value;
    }

    /**
     * Gets the value of the tariffDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public String getTariffDate() {
        return tariffDate;
    }

    /**
     * Sets the value of the tariffDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setTariffDate(String value) {
        this.tariffDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setEndDate(String value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name) &&
                Objects.equals(operator, tariff.operator) &&
                Objects.equals(payroll, tariff.payroll) &&
                Objects.equals(tariffDate, tariff.tariffDate) &&
                Objects.equals(endDate, tariff.endDate) &&
                Objects.equals(parameters, tariff.parameters) &&
                Objects.equals(tariffId, tariff.tariffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operator, payroll, tariffDate,
                endDate, parameters, tariffId);
    }

    @Override
    public String toString() {
        return "TariffID: " + getTariffId() + "\n\tName: " + getName()
                + "\n\tOperator: " + getOperator() + "\n\tPayroll: "
                + getPayroll() + "\n\tTariff date: " + getTariffDate()
                + "\n\tEnd date: " + getEndDate() + "\n\tParameters: "
                + getParameters();
    }
}
