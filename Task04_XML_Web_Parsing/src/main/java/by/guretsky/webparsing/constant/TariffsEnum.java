package by.guretsky.webparsing.constant;

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
    CONNECTION_PRICE("connection-price"),
    BLOCKING_WITH_DEBT("blocking-with-debt"),
    FREE_MB("free-mb"),
    SPEED_LIMIT("speed-limit"),
    INSIDE("inside"),
    OUTSIDE("outside"),
    LANDLINE("landline"),
    SMS_PRICE("SMS-price"),
    PARAMETERS("parameters"),
    CALL_PRICES("call-prices");

    private String value;

    TariffsEnum(String element) {
        this.value = element;
    }

    public final String getValue() {
        return value;
    }
}
