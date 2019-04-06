package by.guretsky.webparsing.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Abstract tariff class with common properties.
 */
public abstract class Tariff {
    /**
     * Tariff name.
     */
    private String name;
    /**
     * Tariff operator.
     */
    private String operator;
    /**
     * Tariff subscription fee.
     */
    private Double payroll;
    /**
     * Tariff start date.
     */
    private Date tariffDate;
    /**
     * Tariff end date.
     */
    private Date endDate;
    /**
     * Tariff parameters.
     */
    private Parameters parameters;
    /**
     * Tariff ID.
     */
    private String tariffId;

    /**
     * Public constructor without parameters.
     */
    public Tariff() {
        parameters = new Parameters();
    }

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
    public void setName(final String value) {
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
    public void setOperator(final String value) {
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
    public void setPayroll(final Double value) {
        this.payroll = value;
    }

    /**
     * Gets the value of the tariffDate property.
     *
     * @return string date
     */
    public String getTariffDate() {
        if (tariffDate == null) {
            return "No information";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(tariffDate);
        }
    }

    /**
     * Sets the value of the tariffDate property.
     *
     * @param value date
     */
    public void setTariffDate(final Date value) {
        tariffDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return string date
     */
    public String getEndDate() {
        if (endDate == null) {
            return "No information";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(endDate);
        }
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value date
     */
    public void setEndDate(final Date value) {
        endDate = value;
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
    public void setParameters(final Parameters value) {
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
    public void setTariffId(final String value) {
        this.tariffId = value;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name)
                && Objects.equals(operator, tariff.operator)
                && Objects.equals(payroll, tariff.payroll)
                && Objects.equals(tariffDate, tariff.tariffDate)
                && Objects.equals(endDate, tariff.endDate)
                && Objects.equals(parameters, tariff.parameters)
                && Objects.equals(tariffId, tariff.tariffId);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, operator, payroll, tariffDate,
                endDate, parameters, tariffId);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "TariffID: " + getTariffId() + "\n\tName: " + getName()
                + "\n\tOperator: " + getOperator() + "\n\tPayroll: "
                + getPayroll() + "\n\tTariff date: " + getTariffDate()
                + "\n\tEnd date: " + getEndDate() + "\n\tParameters: "
                + getParameters();
    }
}
