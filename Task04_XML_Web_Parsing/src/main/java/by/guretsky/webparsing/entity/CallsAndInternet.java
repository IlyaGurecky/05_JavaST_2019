package by.guretsky.webparsing.entity;

import java.util.Objects;

/**
 * Calls and internet tariff class.
 */
public class CallsAndInternet extends Tariff {
    /**
     * {@link CallPrices} object.
     */
    private CallPrices callPrices;
    /**
     * SMS price.
     */
    private Double smsPrice;
    /**
     * Free mb.
     */
    private Integer freeMb;
    /**
     * Speed limit.
     */
    private Integer speedLimit;
    /**
     * Tariff tariffication.
     */
    private String tariffication;

    /**
     * Public constructor without parameters.
     */
    public CallsAndInternet() {
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
    public void setSmsPrice(final Double value) {
        this.smsPrice = value;
    }

    /**
     * Gets the value of the freeMb property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getFreeMb() {
        return freeMb;
    }

    /**
     * Sets the value of the freeMb property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setFreeMb(final Integer value) {
        this.freeMb = value;
    }

    /**
     * Gets the value of the speedLimit property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Sets the value of the speedLimit property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSpeedLimit(final Integer value) {
        this.speedLimit = value;
    }

    /**
     * Gets the value of the tariffication property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTariffication() {
        if (tariffication == null || tariffication.isEmpty()) {
            return "minute";
        }
        return tariffication;
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
        CallsAndInternet that = (CallsAndInternet) o;
        return Objects.equals(callPrices, that.callPrices)
                && Objects.equals(smsPrice, that.smsPrice)
                && Objects.equals(freeMb, that.freeMb)
                && Objects.equals(speedLimit, that.speedLimit)
                && Objects.equals(tariffication, that.tariffication);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrices, smsPrice,
                freeMb, speedLimit, tariffication);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "Calls And Internet tariff:\n\t" + super.toString()
                + "\n\tCall prices: " + callPrices + "\n\tSMS price: "
                + smsPrice + "\n\tTariffication: " + getTariffication()
                + "\n\tFree MB: " + freeMb + "\n\tSpeed limit: " + speedLimit
                + "\n\n";
    }
}
