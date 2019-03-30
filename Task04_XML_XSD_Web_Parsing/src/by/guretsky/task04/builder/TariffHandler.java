package by.guretsky.task04.builder;

import by.guretsky.task04.constant.TariffsEnum;
import by.guretsky.task04.entity.Calls;
import by.guretsky.task04.entity.CallsAndInternet;
import by.guretsky.task04.entity.Internet;
import by.guretsky.task04.entity.Tariff;
import by.guretsky.task04.exception.IllegalTagNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TariffHandler extends DefaultHandler {
    private static final Logger LOGGER =
            LogManager.getLogger(TariffHandler.class);
    private List<Tariff> tariffs;
    private Tariff current = null;
    private TariffsEnum currentEnum = null;
    private EnumSet<TariffsEnum> withText;

    TariffHandler() {
        tariffs = new ArrayList<>();
        withText = EnumSet.range(TariffsEnum.NAME, TariffsEnum.SMS_PRICE);
    }

    List<Tariff> getTariffs() {
        return tariffs;
    }

    private TariffsEnum findConstant(String localName) throws
            IllegalTagNameException {
        for (TariffsEnum elem : EnumSet.allOf(TariffsEnum.class)) {
            if (localName.equals(elem.getValue())) {
                return elem;
            }
        }
        throw new IllegalTagNameException("Incorrect tag name");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        switch (qName) {
            case "internet":
                current = new Internet();
                current.setTariffId(attributes.getValue(0));
                break;
            case "calls":
                current = new Calls();
                current.setTariffId(attributes.getValue(0));
                if (attributes.getLength() == 2) {
                    ((Calls) current)
                            .setTariffication(attributes.getValue(1));
                }
                break;
            case "calls-internet":
                current = new CallsAndInternet();
                current.setTariffId(attributes.getValue(0));
                if (attributes.getLength() == 2) {
                    ((CallsAndInternet) current)
                            .setTariffication(attributes.getValue(1));
                }
                break;
            default:
                try {
                    TariffsEnum temp = findConstant(qName);
                    if (withText.contains(temp)) {
                        currentEnum = temp;
                    }
                } catch (IllegalTagNameException e) {
                    LOGGER.error("Define constant error", e);
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("calls".equals(qName) || "internet".equals(qName)
                || "calls-internet".equals(qName)) {
            tariffs.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum.getValue()) {
                case "connection-price":
                    current.getParameters()
                            .setConnectionPrice(Double.parseDouble(s));
                    break;
                case "blocking-with-debt":
                    current.getParameters()
                            .setBlockingWithDebt(Boolean.parseBoolean(s));
                    break;
                case "outside":
                    definePricesProperties(
                            TariffsEnum.OUTSIDE.getValue(), s);
                    break;
                case "inside":
                    definePricesProperties(
                            TariffsEnum.INSIDE.getValue(), s);
                    break;
                case "landline":
                    definePricesProperties(
                            TariffsEnum.LANDLINE.getValue(), s);
                    break;
                case "SMS-price":
                    definePricesProperties(TariffsEnum.SMS_PRICE.getValue(), s);
                    break;
                case "free-mb":
                    defineInternetProperties(TariffsEnum.FREE_MB.getValue(), s);
                    break;
                case "speed-limit":
                    defineInternetProperties(
                            TariffsEnum.SPEED_LIMIT.getValue(), s);
                    break;
                default:
                    try {
                        defineCommonProperties(s);
                    } catch (IllegalTagNameException e) {
                        LOGGER.error("Get common properties error", e);
                    }
                    break;
            }
            currentEnum = null;
        }
    }

    private void defineCommonProperties(String s) throws
            IllegalTagNameException {
        switch (currentEnum.getValue()) {
            case "name":
                current.setName(s);
                break;
            case "operator":
                current.setOperator(s);
                break;
            case "payroll":
                current.setPayroll(Double.parseDouble(s));
                break;
            case "tariff-date":
                SimpleDateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd");
                try {
                    current.setTariffDate(format.parse(s));
                } catch (ParseException e) {
                    LOGGER.error("Date parse error");
                }
                break;
            case "end-date":
                try {
                    current.setEndDate(
                            new SimpleDateFormat("yyyy-MM-dd")
                                    .parse(s));
                } catch (ParseException e) {
                    LOGGER.error("Date parse error");
                }
                break;
            default:
                throw new IllegalTagNameException("Unknown tag");
        }

    }

    private void definePricesProperties(String property, String s) {
        switch (property) {
            case "outside":
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setOutside(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setOutside(Double.parseDouble(s));
                }
                break;
            case "inside":
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setInside(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setInside(Double.parseDouble(s));
                }
                break;
            case "landline":
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setLandLine(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setLandLine(Double.parseDouble(s));
                }
                break;
            case "SMS-price":
                if (current instanceof Calls) {
                    ((Calls) current).setSMSPrice(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current)
                            .setSMSPrice(Double.parseDouble(s));
                }
                break;
            default:
                break;
        }

    }

    private void defineInternetProperties(String property, String s) {
        switch (property) {
            case "free-mb":
                if (current instanceof Internet) {
                    ((Internet) current).setFreeMb(Integer.parseInt(s));
                } else {
                    ((CallsAndInternet) current)
                            .setFreeMb(Integer.parseInt(s));
                }
                break;
            case "speed-limit":
                if (current instanceof Internet) {
                    ((Internet) current).setSpeedLimit(Integer.parseInt(s));
                } else {
                    ((CallsAndInternet) current)
                            .setSpeedLimit(Integer.parseInt(s));
                }
                break;
            default:
                break;
        }
    }
}
