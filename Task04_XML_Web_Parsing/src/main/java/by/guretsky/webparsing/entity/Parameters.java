package by.guretsky.webparsing.entity;

import java.util.Objects;

public class Parameters {
    private Double connectionPrice;
    private boolean blockingWithDebt;

    /**
     * Gets the value of the connectionPrice property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getConnectionPrice() {
        return connectionPrice;
    }

    /**
     * Sets the value of the connectionPrice property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setConnectionPrice(Double value) {
        this.connectionPrice = value;
    }

    /**
     * Gets the value of the blockingWithDebt property.
     */
    public boolean isBlockingWithDebt() {
        return blockingWithDebt;
    }

    /**
     * Sets the value of the blockingWithDebt property.
     */
    public void setBlockingWithDebt(boolean value) {
        this.blockingWithDebt = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return blockingWithDebt == that.blockingWithDebt &&
                Objects.equals(connectionPrice, that.connectionPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionPrice, blockingWithDebt);
    }

    @Override
    public String toString() {
        return "\n\t\tConnection price: " + connectionPrice
                + "\n\t\tBlocking with debt: " + blockingWithDebt;
    }
}
