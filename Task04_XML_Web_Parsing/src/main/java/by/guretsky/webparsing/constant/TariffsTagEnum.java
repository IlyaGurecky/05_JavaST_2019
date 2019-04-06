package by.guretsky.webparsing.constant;

/**
 * Tariffs xml tags.
 */
public enum TariffsTagEnum {
    /**
     * Tariffs tag.
     */
    TARIFFS("tariffs"),
    /**
     * Tariff ID tag.
     */
    TARIFF_ID("tariff-id"),
    /**
     * Tariffication tag.
     */
    TARIFFICATION("tariffication"),
    /**
     * Internet tag.
     */
    INTERNET("internet"),
    /**
     * Calls tag.
     */
    CALLS("calls"),
    /**
     * Calls and internet tag.
     */
    CALLS_AND_INTERNET("calls-internet"),
    /**
     * Name tag.
     */
    NAME("name"),
    /**
     * Operator tag.
     */
    OPERATOR("operator"),
    /**
     * Payroll tag.
     */
    PAYROLL("payroll"),
    /**
     * Tariff date tag.
     */
    TARIFF_DATE("tariff-date"),
    /**
     * End date tag.
     */
    END_DATE("end-date"),
    /**
     * Connection price tag.
     */
    CONNECTION_PRICE("connection-price"),
    /**
     * Blocking with debt tag.
     */
    BLOCKING_WITH_DEBT("blocking-with-debt"),
    /**
     * free mb tag.
     */
    FREE_MB("free-mb"),
    /**
     * Speed limit tag.
     */
    SPEED_LIMIT("speed-limit"),
    /**
     * Inside tag.
     */
    INSIDE("inside"),
    /**
     * Outside tag.
     */
    OUTSIDE("outside"),
    /**
     * Landline tag.
     */
    LANDLINE("landline"),
    /**
     * SMS price tag.
     */
    SMS_PRICE("SMS-price"),
    /**
     * Parameters tag.
     */
    PARAMETERS("parameters"),
    /**
     * Call prices tag.
     */
    CALL_PRICES("call-prices");

    /**
     * Constant value.
     */
    private String value;

    /**
     * Public constructor to create constant with parameter.
     *
     * @param element value
     */
    TariffsTagEnum(final String element) {
        this.value = element;
    }

    /**
     * Gets constant properties.
     *
     * @return constant value
     */
    public final String getValue() {
        return value;
    }
}
