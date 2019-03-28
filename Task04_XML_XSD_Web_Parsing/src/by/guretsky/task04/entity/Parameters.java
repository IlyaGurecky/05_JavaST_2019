package by.guretsky.task04.entity;

import java.math.BigDecimal;

public class Parameters {
    private BigDecimal connectionPrice;
    private boolean blockingWithDebt;

    /**
     * Gets the value of the connectionPrice property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getConnectionPrice() {
        return connectionPrice;
    }

    /**
     * Sets the value of the connectionPrice property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setConnectionPrice(BigDecimal value) {
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
}
