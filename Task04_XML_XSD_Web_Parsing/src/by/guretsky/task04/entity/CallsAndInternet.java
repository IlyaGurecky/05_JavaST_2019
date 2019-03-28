package by.guretsky.task04.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CallsAndInternet extends Tariff {
    private CallPrices callPrices;
    private BigDecimal smsPrice;
    private BigInteger freeMb;
    private BigInteger speedLimit;
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
     * Gets the value of the freeMb property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getFreeMb() {
        return freeMb;
    }

    /**
     * Sets the value of the freeMb property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setFreeMb(BigInteger value) {
        this.freeMb = value;
    }

    /**
     * Gets the value of the speedLimit property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Sets the value of the speedLimit property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setSpeedLimit(BigInteger value) {
        this.speedLimit = value;
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
