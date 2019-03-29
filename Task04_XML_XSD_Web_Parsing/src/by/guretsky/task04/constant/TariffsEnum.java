package by.guretsky.task04.constant;

public enum TariffsEnum {
    TARIFFS("tariffs"),
    TARIFF_ID("tariff-id"),
    TARIFFICATION("tariffication"),
    INTERNET("internet"),
    CALLS("calls"),
    CALLS_AND_INTERNET("calls-internet"),
    NAME("name"),
    OPERATOR("operator"),
    PAYROLL("payroll"),
    TARIFF_DATE("tariff-date"),
    END_DATE("end-date"),
    PARAMETERS("parameters"),
    CONNECTION_PRICE("connection-price"),
    BLOCKING_WITH_DEBT("blocking-with-debt"),
    FREE_MB("free-mb"),
    SPEED_LIMIT("speed-limit"),
    CALL_PRICES("call-prices"),
    INSIDE("inside"),
    OUTSIDE("outside"),
    LANDLINE("landline"),
    SMS_PRICE("SMS-price");

    private String value;

    TariffsEnum(String element) {
        this.value = element;
    }

    public String getValue() {
        return value;
    }
}
