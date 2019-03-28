package by.guretsky.task04.entity;

import java.math.BigDecimal;

public class CallPrices {
    private BigDecimal inside;
    private BigDecimal outside;
    private BigDecimal landLine;

    /**
     * Gets the value of the inside property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getInside() {
        return inside;
    }

    /**
     * Sets the value of the inside property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setInside(BigDecimal value) {
        this.inside = value;
    }

    /**
     * Gets the value of the outside property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getOutside() {
        return outside;
    }

    /**
     * Sets the value of the outside property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setOutside(BigDecimal value) {
        this.outside = value;
    }

    /**
     * Gets the value of the landLine property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getLandLine() {
        return landLine;
    }

    /**
     * Sets the value of the landLine property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setLandLine(BigDecimal value) {
        this.landLine = value;
    }
}
