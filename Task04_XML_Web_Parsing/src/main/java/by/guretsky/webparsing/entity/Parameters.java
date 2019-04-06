package by.guretsky.webparsing.entity;

import java.util.Objects;

/**
 * Tariff parameters class.
 */
public class Parameters {
    /**
     * Connection price.
     */
    private Double connectionPrice;
    /**
     * Blocking with debt.
     */
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
    public void setConnectionPrice(final Double value) {
        this.connectionPrice = value;
    }

    /**
     * Gets the value of the blockingWithDebt property.
     *
     * @return true if isBlocking
     */
    public boolean isBlockingWithDebt() {
        return blockingWithDebt;
    }

    /**
     * Sets the value of the blockingWithDebt property.
     *
     * @param value isBlocking value
     */
    public void setBlockingWithDebt(final boolean value) {
        this.blockingWithDebt = value;
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
        Parameters that = (Parameters) o;
        return blockingWithDebt == that.blockingWithDebt
                && Objects.equals(connectionPrice, that.connectionPrice);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(connectionPrice, blockingWithDebt);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "\n\t\tСтоимость подключения: " + connectionPrice
                + "\n\t\tБлокировка при задоженности: " + blockingWithDebt;
    }
}
