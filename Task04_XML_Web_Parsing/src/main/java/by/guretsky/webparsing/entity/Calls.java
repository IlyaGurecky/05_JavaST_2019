package by.guretsky.webparsing.entity;

import java.util.Objects;

/**
 * Calls tariff class.
 */
public class Calls extends Tariff {
    /**
     * {@link CallPrices} object.
     */
    private CallPrices callPrices;
    /**
     * SMS price.
     */
    private Double smsPrice;
    /**
     * Tariff tariffication.
     */
    private String tariffication;

    /**
     * Public constructor without parameters.
     */
    public Calls() {
        callPrices = new CallPrices();
    }

    /**
     * Gets the value of the callPrices property.
     *
     * @return possible object is
     * {@link CallPrices }
     */
    public CallPrices getCallPrices() {
        return callPrices;
    }

    /**
     * Sets the value of the callPrices property.
     *
     * @param value allowed object is
     *              {@link CallPrices }
     */
    public void setCallPrices(final CallPrices value) {
        this.callPrices = value;
    }

    /**
     * Gets the value of the smsPrice property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getSmsPrice() {
        return smsPrice;
    }

    /**
     * Sets the value of the smsPrice property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setSMSPrice(final Double value) {
        this.smsPrice = value;
    }

    /**
     * Gets the value of the tariffication property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTariffication() {
        return Objects.requireNonNullElse(tariffication, "minute");
    }

    /**
     * Sets the value of the tariffication property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTariffication(final String value) {
        this.tariffication = value;
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
        if (!super.equals(o)) {
            return false;
        }
        Calls calls = (Calls) o;
        return Objects.equals(callPrices, calls.callPrices)
                && Objects.equals(smsPrice, calls.smsPrice)
                && Objects.equals(tariffication, calls.tariffication);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrices,
                smsPrice, tariffication);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "Calls tariff:\n\t" + super.toString() + "\n\tCall prices: "
                + callPrices + "\n\tSMS price: " + smsPrice
                + "\n\tTariffication: " + getTariffication() + "\n\n";
    }
}
