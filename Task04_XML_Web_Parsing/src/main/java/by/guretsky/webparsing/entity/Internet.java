package by.guretsky.webparsing.entity;

import java.util.Objects;

/**
 * Internet tariff class.
 */
public class Internet extends Tariff {
    /**
     * Free mb.
     */
    private Integer freeMb;
    /**
     * Speed limit.
     */
    private Integer speedLimit;

    /**
     * Gets the value of the freeMb property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getFreeMb() {
        return freeMb;
    }

    /**
     * Sets the value of the freeMb property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setFreeMb(final Integer value) {
        this.freeMb = value;
    }

    /**
     * Gets the value of the speedLimit property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Sets the value of the speedLimit property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSpeedLimit(final Integer value) {
        this.speedLimit = value;
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
        if (!super.equals(o)) {
            return false;
        }
        Internet internet = (Internet) o;
        return Objects.equals(freeMb, internet.freeMb)
                && Objects.equals(speedLimit, internet.speedLimit);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), freeMb, speedLimit);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "Internet tariff:\n\t" + super.toString() + "\n\tFree MB: "
                + freeMb + "\n\tSpeed limit: " + speedLimit + "\n\n";
    }
}
