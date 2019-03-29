package by.guretsky.task04.entity;

import java.util.Objects;

public class Calls extends Tariff {
    private CallPrices callPrices;
    private Double smsPrice;
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
        Calls calls = (Calls) o;
        return Objects.equals(callPrices, calls.callPrices) &&
                Objects.equals(smsPrice, calls.smsPrice) &&
                Objects.equals(tariffication, calls.tariffication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrices,
                smsPrice, tariffication);
    }

    @Override
    public String toString() {
        return "Calls tariff:\n\t" + super.toString() + "\n\tCall prices: "
                + callPrices + "\n\tSMS price: " + smsPrice
                + "\n\tTariffication: " + getTariffication() + "\n\n";
    }
}
