package by.guretsky.task04.entity;

import java.util.Objects;

public class CallsAndInternet extends Tariff {
    private CallPrices callPrices;
    private Double smsPrice;
    private Integer freeMb;
    private Integer speedLimit;
    private String tariffication;

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
    public void setCallPrices(CallPrices value) {
        this.callPrices = value;
    }

    /**
     * Gets the value of the smsPrice property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getSMSPrice() {
        return smsPrice;
    }

    /**
     * Sets the value of the smsPrice property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setSMSPrice(Double value) {
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
    public void setFreeMb(Integer value) {
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
    public void setSpeedLimit(Integer value) {
        this.speedLimit = value;
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
    public void setTariffication(String value) {
        this.tariffication = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CallsAndInternet that = (CallsAndInternet) o;
        return Objects.equals(callPrices, that.callPrices) &&
                Objects.equals(smsPrice, that.smsPrice) &&
                Objects.equals(freeMb, that.freeMb) &&
                Objects.equals(speedLimit, that.speedLimit) &&
                Objects.equals(tariffication, that.tariffication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrices, smsPrice,
                freeMb, speedLimit, tariffication);
    }

    @Override
    public String toString() {
        return "Calls And Internet tariff:\n\t" + super.toString()
                + "\n\tCall prices: " + callPrices + "\n\tSMS price: "
                + smsPrice + "\n\tTariffication: " + getTariffication()
                + "\n\tFree MB: " + freeMb + "\n\tSpeed limit: " + speedLimit
                + "\n\n";
    }
}
