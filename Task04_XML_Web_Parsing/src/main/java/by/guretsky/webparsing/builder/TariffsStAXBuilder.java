package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.constant.TariffsTagEnum;
import by.guretsky.webparsing.entity.CallPrices;
import by.guretsky.webparsing.entity.Calls;
import by.guretsky.webparsing.entity.CallsAndInternet;
import by.guretsky.webparsing.entity.Internet;
import by.guretsky.webparsing.entity.Parameters;
import by.guretsky.webparsing.entity.Tariff;
import by.guretsky.webparsing.exception.IllegalTagNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parser based on StAX model parsing.
 */
public class TariffsStAXBuilder extends ParseBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TariffsStAXBuilder.class);
    /**
     * @see XMLInputFactory
     */
    private XMLInputFactory inputFactory;

    /**
     * Constructor - initializes {@link XMLInputFactory}.
     */
    public TariffsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Builds whole list of tariffs.
     *
     * @param filePath file path.
     */
    @Override
    public void buildTariffs(final String filePath) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream =
                     new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TariffsTagEnum.CALLS.getValue().equals(name)) {
                        Tariff tariff = buildCallsTariff(reader);
                        this.addTariff(tariff);
                    } else if (TariffsTagEnum
                            .CALLS_AND_INTERNET.getValue().equals(name)) {
                        Tariff tariff =
                                buildCallsAndInternetTariff(reader);
                        this.addTariff(tariff);
                    } else if (TariffsTagEnum.INTERNET.getValue()
                            .equals(name)) {
                        Tariff tariff = buildInternetTariff(reader);
                        this.addTariff(tariff);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found", e);
        } catch (XMLStreamException e) {
            LOGGER.error("StAX parsing error", e);
        } catch (IOException e) {
            LOGGER.error("Closing stream error", e);
        } catch (IllegalTagNameException e) {
            LOGGER.error("Unknown tag name in tariff", e);
        }
    }

    /**
     * Build Calls tariff.
     *
     * @param reader {@link XMLStreamReader} obj
     * @return tariff
     * @throws XMLStreamException      if tag name is incorrect
     * @throws IllegalTagNameException if tag name is incorrect
     */
    private Tariff buildCallsTariff(final XMLStreamReader reader) throws
            XMLStreamException, IllegalTagNameException {
        Calls tariff = new Calls();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsTagEnum.TARIFF_ID.getValue()));
        tariff.setTariffication(reader.getAttributeValue(null,
                TariffsTagEnum.TARIFFICATION.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "call-prices":
                        tariff.setCallPrices(getXMLCallPrices(reader));
                        break;
                    case "SMS-price":
                        tariff.setSMSPrice(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    default:
                        defineCommonProperties(name, tariff, reader);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(TariffsTagEnum.CALLS.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Find tag error");
    }

    /**
     * Build Internet tariff.
     *
     * @param reader {@link XMLStreamReader} obj
     * @return tariff
     * @throws XMLStreamException      if tag name is incorrect
     * @throws IllegalTagNameException if tag name is incorrect
     */
    private Tariff buildInternetTariff(final XMLStreamReader reader) throws
            IllegalTagNameException, XMLStreamException {
        Internet tariff = new Internet();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsTagEnum.TARIFF_ID.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "free-mb":
                        tariff.setFreeMb(Integer.parseInt(getXMLText(reader)));
                        break;
                    case "speed-limit":
                        tariff.setSpeedLimit(Integer
                                .parseInt(getXMLText(reader)));
                        break;
                    default:
                        defineCommonProperties(name, tariff, reader);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(TariffsTagEnum.INTERNET.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Unknown element");

    }

    /**
     * Build Calls and Internet tariff.
     *
     * @param reader {@link XMLStreamReader} obj
     * @return tariff
     * @throws XMLStreamException      if tag name is incorrect
     * @throws IllegalTagNameException if tag name is incorrect
     */
    private Tariff buildCallsAndInternetTariff(final XMLStreamReader reader)
            throws IllegalTagNameException, XMLStreamException {
        CallsAndInternet tariff = new CallsAndInternet();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsTagEnum.TARIFF_ID.getValue()));
        tariff.setTariffication(reader.getAttributeValue(null,
                TariffsTagEnum.TARIFFICATION.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "call-prices":
                        tariff.setCallPrices(getXMLCallPrices(reader));
                        break;
                    case "SMS-price":
                        tariff.setSmsPrice(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    case "free-mb":
                        tariff.setFreeMb(Integer.parseInt(getXMLText(reader)));
                        break;
                    case "speed-limit":
                        tariff.setSpeedLimit(Integer
                                .parseInt(getXMLText(reader)));
                        break;
                    default:
                        defineCommonProperties(name, tariff, reader);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(TariffsTagEnum.CALLS_AND_INTERNET.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Unknown element");
    }

    /**
     * Initialize common tariffs properties.
     *
     * @param name   tag name
     * @param tariff current tariff
     * @param reader {@link XMLStreamReader} obj
     * @throws XMLStreamException      if tag name is incorrect
     * @throws IllegalTagNameException if tag name is incorrect
     */
    private void defineCommonProperties(final String name, final Tariff tariff,
                                        final XMLStreamReader reader) throws
            XMLStreamException, IllegalTagNameException {
        switch (name) {
            case "name":
                tariff.setName(getXMLText(reader));
                break;
            case "operator":
                tariff.setOperator(getXMLText(reader));
                break;
            case "payroll":
                tariff.setPayroll(Double
                        .parseDouble(getXMLText(reader)));
                break;
            case "tariff-date":
                SimpleDateFormat dateFormat =
                        new SimpleDateFormat("yyyy-MM-dd");
                try {
                    tariff.setTariffDate(dateFormat
                            .parse(getXMLText(reader)));
                } catch (ParseException e) {
                    LOGGER.error("Incorrect date to parse", e);
                }
                break;
            case "end-date":
                SimpleDateFormat date =
                        new SimpleDateFormat("yyyy-MM-dd");
                try {
                    tariff.setEndDate(date
                            .parse(getXMLText(reader)));
                } catch (ParseException e) {
                    LOGGER.error("Incorrect date to parse", e);
                }
                break;
            case "parameters":
                tariff.setParameters(getXMLParameters(reader));
                break;
            default:
                throw new IllegalTagNameException("Unknown operation");
        }
    }

    /**
     * @param reader xml reader.
     * @return text form current tag.
     * @throws XMLStreamException when tag is invalid.
     */
    private String getXMLText(final XMLStreamReader reader) throws
            XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    /**
     * Read and creates data from tag parameters.
     *
     * @param reader xml reader.
     * @return created parameters.
     * @throws XMLStreamException      when tag is invalid.
     * @throws IllegalTagNameException when tag is invalid
     */
    private Parameters getXMLParameters(final XMLStreamReader reader) throws
            XMLStreamException, IllegalTagNameException {
        Parameters parameters = new Parameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "connection-price":
                        parameters.setConnectionPrice(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    case "blocking-with-debt":
                        parameters.setBlockingWithDebt(Boolean
                                .parseBoolean(getXMLText(reader)));
                        break;
                    default:
                        throw new IllegalTagNameException("Unknown tag");
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(TariffsTagEnum.PARAMETERS.getValue())) {
                    return parameters;
                }
            }
        }
        throw new IllegalTagNameException("Unknown tag name");
    }


    /**
     * Read and creates data from tag call-prices.
     *
     * @param reader xml reader.
     * @return created call prices.
     * @throws XMLStreamException      when tag is invalid.
     * @throws IllegalTagNameException when tag is invalid
     */
    private CallPrices getXMLCallPrices(final XMLStreamReader reader) throws
            IllegalTagNameException, XMLStreamException {
        CallPrices prices = new CallPrices();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "inside":
                        prices.setInside(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    case "outside":
                        prices.setOutside(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    case "landline":
                        prices.setLandLine(Double
                                .parseDouble(getXMLText(reader)));
                        break;
                    default:
                        throw new IllegalTagNameException("Unknown tag");
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(TariffsTagEnum.CALL_PRICES.getValue())) {
                    return prices;
                }
            }
        }
        throw new IllegalTagNameException("Unknown tag name");
    }
}
