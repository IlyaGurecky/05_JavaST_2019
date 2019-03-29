package by.guretsky.task04.entity;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices that = (CallPrices) o;
        return Objects.equals(inside, that.inside) &&
                Objects.equals(outside, that.outside) &&
                Objects.equals(landLine, that.landLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inside, outside, landLine);
    }

    @Override
    public String toString() {
        return "\n\t\tInside: " + inside + "\n\t\tOutside: "
                + outside + "\n\t\tLandline: " + landLine;
    }
}
