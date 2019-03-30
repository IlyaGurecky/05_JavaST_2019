package by.guretsky.task04.builder;

import by.guretsky.task04.constant.TariffsEnum;
import by.guretsky.task04.entity.CallPrices;
import by.guretsky.task04.entity.Calls;
import by.guretsky.task04.entity.CallsAndInternet;
import by.guretsky.task04.entity.Internet;
import by.guretsky.task04.entity.Parameters;
import by.guretsky.task04.entity.Tariff;
import by.guretsky.task04.exception.IllegalTagNameException;
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

public class TariffsStAXBuilder extends ParseBuilder {
    private static final Logger LOGGER =
            LogManager.getLogger(TariffsStAXBuilder.class);
    private XMLInputFactory inputFactory;

    public TariffsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildTariffs(String filePath) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream =
                     new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TariffsEnum.CALLS.getValue().equals(name)) {
                        Tariff tariff = buildCallsTariff(reader);
                        this.addTariff(tariff);
                    } else if (TariffsEnum
                            .CALLS_AND_INTERNET.getValue().equals(name)) {
                        Tariff tariff =
                                buildCallsAndInternetTariff(reader);
                        this.addTariff(tariff);
                    } else if (TariffsEnum.INTERNET.getValue().equals(name)) {
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

    private Tariff buildCallsTariff(XMLStreamReader reader) throws
            XMLStreamException, IllegalTagNameException {
        Calls tariff = new Calls();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsEnum.TARIFF_ID.getValue()));
        tariff.setTariffication(reader.getAttributeValue(null,
                TariffsEnum.TARIFFICATION.getValue()));
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
                if (name.equals(TariffsEnum.CALLS.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Find tag error");
    }

    private Tariff buildInternetTariff(XMLStreamReader reader) throws
            IllegalTagNameException, XMLStreamException {
        Internet tariff = new Internet();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsEnum.TARIFF_ID.getValue()));
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
                if (name.equals(TariffsEnum.INTERNET.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Unknown element");

    }

    private Tariff buildCallsAndInternetTariff(XMLStreamReader reader) throws
            IllegalTagNameException, XMLStreamException {
        CallsAndInternet tariff = new CallsAndInternet();
        tariff.setTariffId(reader.getAttributeValue(null,
                TariffsEnum.TARIFF_ID.getValue()));
        tariff.setTariffication(reader.getAttributeValue(null,
                TariffsEnum.TARIFFICATION.getValue()));

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
                if (name.equals(TariffsEnum.CALLS_AND_INTERNET.getValue())) {
                    return tariff;
                }
            }
        }
        throw new IllegalTagNameException("Unknown element");
    }

    private void defineCommonProperties(String name, Tariff tariff,
                                        XMLStreamReader reader) throws
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

    private String getXMLText(XMLStreamReader reader) throws
            XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private Parameters getXMLParameters(XMLStreamReader reader) throws
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
                if (name.equals(TariffsEnum.PARAMETERS.getValue())) {
                    return parameters;
                }
            }
        }
        throw new IllegalTagNameException("Unknown tag name");
    }


    private CallPrices getXMLCallPrices(XMLStreamReader reader) throws
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
                if (name.equals(TariffsEnum.CALL_PRICES.getValue())) {
                    return prices;
                }
            }
        }
        throw new IllegalTagNameException("Unknown tag name");
    }
}
