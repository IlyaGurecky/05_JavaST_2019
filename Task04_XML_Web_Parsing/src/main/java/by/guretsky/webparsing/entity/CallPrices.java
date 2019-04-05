package by.guretsky.webparsing.entity;

import java.util.Objects;

public class CallPrices {
    private Double inside;
    private Double outside;
    private Double landLine;

    /**
     * Gets the value of the inside property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getInside() {
        return inside;
    }

    /**
     * Sets the value of the inside property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setInside(Double value) {
        this.inside = value;
    }

    /**
     * Gets the value of the outside property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getOutside() {
        return outside;
    }

    /**
     * Sets the value of the outside property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setOutside(Double value) {
        this.outside = value;
    }

    /**
     * Gets the value of the landLine property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getLandLine() {
        return landLine;
    }

    /**
     * Sets the value of the landLine property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setLandLine(Double value) {
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
