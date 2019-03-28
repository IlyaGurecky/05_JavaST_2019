package by.guretsky.task04.entity;

import java.math.BigDecimal;

public class Calls extends Tariff {
    private CallPrices callPrices;
    private BigDecimal smsPrice;
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
     * {@link BigDecimal }
     */
    public BigDecimal getSMSPrice() {
        return smsPrice;
    }

    /**
     * Sets the value of the smsPrice property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setSMSPrice(BigDecimal value) {
        this.smsPrice = value;
    }

    /**
     * Gets the value of the tariffication property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTariffication() {
        if (tariffication == null) {
            return "minute";
        } else {
            return tariffication;
        }
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
}
