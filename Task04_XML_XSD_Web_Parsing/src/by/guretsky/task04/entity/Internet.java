package by.guretsky.task04.entity;

import java.math.BigInteger;

public class Internet extends Tariff {
    private BigInteger freeMb;
    private BigInteger speedLimit;

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
}
