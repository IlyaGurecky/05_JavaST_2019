package by.guretsky.webparsing.builder.handler;

import by.guretsky.webparsing.constant.TariffsTagEnum;
import by.guretsky.webparsing.entity.Calls;
import by.guretsky.webparsing.entity.CallsAndInternet;
import by.guretsky.webparsing.entity.Internet;
import by.guretsky.webparsing.entity.Tariff;
import by.guretsky.webparsing.exception.IllegalTagNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Class helper for SAXParser. Inherit class {@link DefaultHandler}.
 *
 * @see DefaultHandler
 */
public class TariffHandler extends DefaultHandler {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TariffHandler.class);
    /**
     * Tariffs from XML file.
     */
    private List<Tariff> tariffs;
    /**
     * Current tariff.
     */
    private Tariff current = null;
    /**
     * Tariffs tag constant.
     */
    private TariffsTagEnum currentEnum = null;
    /**
     * Tags that have a text. Simple xml elements.
     */
    private EnumSet<TariffsTagEnum> withText;

    /**
     * Constructor - initialize tariffs and enum set with text.
     */
    public TariffHandler() {
        tariffs = new ArrayList<>();
        withText = EnumSet.range(TariffsTagEnum.NAME, TariffsTagEnum.SMS_PRICE);
    }

    /**
     * @return list of tariffs
     */
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * Find enum constant by string.
     *
     * @param localName value of the constant need to find
     * @return {@link TariffsTagEnum}
     * @throws IllegalTagNameException if tag is incorrect
     */
    private TariffsTagEnum findConstant(final String localName) throws
            IllegalTagNameException {
        for (TariffsTagEnum elem : EnumSet.allOf(TariffsTagEnum.class)) {
            if (localName.equals(elem.getValue())) {
                return elem;
            }
        }
        throw new IllegalTagNameException("Incorrect tag name");
    }

    /**
     * Method calls and check if pointer is in start teg.
     *
     * @param uri        namespace uri.
     * @param localName  current tag name.
     * @param qName      uri.
     * @param attributes tag attributes.
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName,
                             final Attributes attributes) {
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
                    TariffsTagEnum temp = findConstant(qName);
                    if (withText.contains(temp)) {
                        currentEnum = temp;
                    }
                } catch (IllegalTagNameException e) {
                    LOGGER.error("Define constant error", e);
                }
        }
    }

    /**
     * Method calls and check if pointer is in last teg.
     *
     * @param uri       namespace uri.
     * @param localName current tag name.
     * @param qName     uri.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if ("calls".equals(qName) || "internet".equals(qName)
                || "calls-internet".equals(qName)) {
            tariffs.add(current);
        }
    }

    /**
     * Method calls when program is observing inner tag.
     *
     * @param ch     tag characters.
     * @param start  first char pos.
     * @param length last char pos.
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case CONNECTION_PRICE:
                    current.getParameters()
                            .setConnectionPrice(Double.parseDouble(s));
                    break;
                case BLOCKING_WITH_DEBT:
                    current.getParameters()
                            .setBlockingWithDebt(Boolean.parseBoolean(s));
                    break;
                case OUTSIDE:
                    definePricesProperties(TariffsTagEnum.OUTSIDE, s);
                    break;
                case INSIDE:
                    definePricesProperties(TariffsTagEnum.INSIDE, s);
                    break;
                case LANDLINE:
                    definePricesProperties(TariffsTagEnum.LANDLINE, s);
                    break;
                case SMS_PRICE:
                    definePricesProperties(TariffsTagEnum.SMS_PRICE, s);
                    break;
                case FREE_MB:
                    defineInternetProperties(TariffsTagEnum.FREE_MB, s);
                    break;
                case SPEED_LIMIT:
                    defineInternetProperties(TariffsTagEnum.SPEED_LIMIT, s);
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

    /**
     * Initialize common param in tariff.
     *
     * @param s info
     * @throws IllegalTagNameException if tag name is incorrect
     */
    private void defineCommonProperties(final String s) throws
            IllegalTagNameException {
        switch (currentEnum) {
            case NAME:
                current.setName(s);
                break;
            case OPERATOR:
                current.setOperator(s);
                break;
            case PAYROLL:
                current.setPayroll(Double.parseDouble(s));
                break;
            case TARIFF_DATE:
                SimpleDateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd");
                try {
                    current.setTariffDate(format.parse(s));
                } catch (ParseException e) {
                    LOGGER.error("Date parse error");
                }
                break;
            case END_DATE:
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

    /**
     * Initialize prices param in tariff.
     *
     * @param property tariff property need to init
     * @param s        info
     */
    private void definePricesProperties(final TariffsTagEnum property,
                                        final String s) {
        switch (property) {
            case OUTSIDE:
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setOutside(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setOutside(Double.parseDouble(s));
                }
                break;
            case INSIDE:
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setInside(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setInside(Double.parseDouble(s));
                }
                break;
            case LANDLINE:
                if (current instanceof Calls) {
                    ((Calls) current).getCallPrices()
                            .setLandLine(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current).getCallPrices()
                            .setLandLine(Double.parseDouble(s));
                }
                break;
            case SMS_PRICE:
                if (current instanceof Calls) {
                    ((Calls) current).setSMSPrice(Double.parseDouble(s));
                } else {
                    ((CallsAndInternet) current)
                            .setSmsPrice(Double.parseDouble(s));
                }
                break;
            default:
                break;
        }

    }

    /**
     * Initialize internet param in tariff.
     *
     * @param property tariff property need to init
     * @param s        info
     */
    private void defineInternetProperties(final TariffsTagEnum property,
                                          final String s) {
        switch (property) {
            case FREE_MB:
                if (current instanceof Internet) {
                    ((Internet) current).setFreeMb(Integer.parseInt(s));
                } else {
                    ((CallsAndInternet) current)
                            .setFreeMb(Integer.parseInt(s));
                }
                break;
            case SPEED_LIMIT:
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
