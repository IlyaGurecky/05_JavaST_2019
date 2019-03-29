package by.guretsky.task04.entity;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Internet internet = (Internet) o;
        return Objects.equals(freeMb, internet.freeMb) &&
                Objects.equals(speedLimit, internet.speedLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), freeMb, speedLimit);
    }

    @Override
    public String toString() {
        return "Internet tariff:\n\t" + "Name: " + getName() + "\n\tOperator: "
                + getOperator() + "\n\tPayroll: " + getPayroll()
                + "\n\tTariff date: " + getTariffDate() + "\n\tEnd date: "
                + getEndDate() + "\n\tParameters: " + getParameters()
                + "\n\tTariffID: " + getTariffId() + "\n\tFree MB: "
                + freeMb + "\n\tSpeed limit: " + speedLimit;
    }
}
