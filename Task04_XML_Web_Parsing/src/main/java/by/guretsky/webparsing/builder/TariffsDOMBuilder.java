package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.CallPrices;
import by.guretsky.webparsing.entity.Calls;
import by.guretsky.webparsing.entity.CallsAndInternet;
import by.guretsky.webparsing.entity.Internet;
import by.guretsky.webparsing.entity.Parameters;
import by.guretsky.webparsing.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser based on DOM model parsing.
 */
public class TariffsDOMBuilder extends ParseBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TariffsDOMBuilder.class);
    /**
     * API to obtain DOM Document instances.
     */
    private DocumentBuilder builder;

    /**
     * Constructor - initializes document builder.
     */
    public TariffsDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Configuration error", e);
        }
    }

    /**
     * Builds tariffs list.
     *
     * @param filePath XML file path
     */
    @Override
    public void buildTariffs(final String filePath) {
        Document document;
        try {
            document = builder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList callsTariffs = root.getElementsByTagName("calls");
            for (int i = 0; i < callsTariffs.getLength(); i++) {
                Element tariffElement = (Element) callsTariffs.item(i);
                Tariff tariff = buildCallsTariff(tariffElement);
                this.addTariff(tariff);
            }
            NodeList internetTariffs = root.getElementsByTagName("internet");
            for (int i = 0; i < internetTariffs.getLength(); i++) {
                Element tariffElement = (Element) internetTariffs.item(i);
                Tariff tariff = buildInternetTariff(tariffElement);
                this.addTariff(tariff);
            }
            NodeList callsAndInternetTariffs =
                    root.getElementsByTagName("calls-internet");
            for (int i = 0; i < callsAndInternetTariffs.getLength(); i++) {
                Element tariffElement =
                        (Element) callsAndInternetTariffs.item(i);
                Tariff tariff = buildCallsAndInternetTariff(tariffElement);
                this.addTariff(tariff);
            }
        } catch (SAXException e) {
            LOGGER.error("Parsing error", e);
        } catch (IOException e) {
            LOGGER.error("File error or I/O error", e);
        }
    }

    /**
     * Build tariff with common parameters.
     *
     * @param tariff        tariff to build
     * @param tariffElement element
     */
    private void buildTariff(final Tariff tariff, final Element tariffElement) {
        tariff.setName(getElementContext(tariffElement, "name"));
        tariff.setOperator(getElementContext(tariffElement, "operator"));
        Double payroll = Double
                .parseDouble(getElementContext(tariffElement, "payroll"));
        tariff.setPayroll(payroll);
        tariff.setTariffId(tariffElement.getAttribute("tariff-id"));
        tariff.setParameters(buildParameters(tariffElement));
        Date tariffDate = null;
        Date endDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tariffDate = format.parse(getElementContext(tariffElement,
                    "tariff-date"));
            endDate = format.parse(getElementContext(tariffElement,
                    "end-date"));
        } catch (ParseException e) {
            LOGGER.error("Date parse error", e);
        }
        tariff.setTariffDate(tariffDate);
        tariff.setEndDate(endDate);
    }

    /**
     * Build Parameters filed in Tariff.
     *
     * @param tariffElem info
     * @return {@link Parameters}
     */
    private Parameters buildParameters(final Element tariffElem) {
        Parameters parameters = new Parameters();
        parameters.setBlockingWithDebt(Boolean
                .parseBoolean(getElementContext(tariffElem,
                        "blocking-with-debt")));
        parameters.setConnectionPrice(Double
                .parseDouble(getElementContext(tariffElem,
                        "connection-price")));
        return parameters;
    }

    /**
     * Build call prices field of the Tariff.
     *
     * @param tariffElem info
     * @return {@link CallPrices}
     */
    private CallPrices buildCallPrices(final Element tariffElem) {
        CallPrices prices = new CallPrices();
        prices.setInside(Double
                .parseDouble(getElementContext(tariffElem, "inside")));
        prices.setOutside(Double
                .parseDouble(getElementContext(tariffElem, "outside")));
        prices.setLandLine(Double
                .parseDouble(getElementContext(tariffElem, "landline")));
        return prices;
    }

    /**
     * Build Calls tariff.
     *
     * @param tariffElement current tariff
     * @return tariff
     */
    private Tariff buildCallsTariff(final Element tariffElement) {
        Calls tariff = new Calls();
        buildTariff(tariff, tariffElement);
        tariff.setTariffication(tariffElement
                .getAttribute("tariffication"));
        tariff.setCallPrices(buildCallPrices(tariffElement));
        tariff.setSMSPrice(Double.parseDouble(getElementContext(tariffElement,
                "SMS-price")));
        return tariff;
    }

    /**
     * Build Internet tariff.
     *
     * @param tariffElement current tariff
     * @return tariff
     */
    private Tariff buildInternetTariff(final Element tariffElement) {
        Internet tariff = new Internet();
        buildTariff(tariff, tariffElement);
        tariff.setFreeMb(Integer.parseInt(getElementContext(tariffElement,
                "free-mb")));
        tariff.setSpeedLimit(Integer.parseInt(getElementContext(tariffElement,
                "speed-limit")));
        return tariff;
    }

    /**
     * Build calls and internet tariff.
     *
     * @param tariffElement current tariff
     * @return tariff
     */
    private Tariff buildCallsAndInternetTariff(final Element tariffElement) {
        CallsAndInternet tariff = new CallsAndInternet();
        buildTariff(tariff, tariffElement);
        tariff.setFreeMb(Integer
                .parseInt(getElementContext(tariffElement, "free-mb")));
        tariff.setSpeedLimit(Integer
                .parseInt(getElementContext(tariffElement, "speed-limit")));
        tariff.setSmsPrice(Double.parseDouble(getElementContext(tariffElement,
                "SMS-price")));
        tariff.setTariffication(tariffElement
                .getAttribute("tariffication"));
        tariff.setCallPrices(buildCallPrices(tariffElement));
        return tariff;
    }

    /**
     * @param elem current element
     * @param name tag name
     * @return tag content
     */
    private static String getElementContext(final Element elem,
                                            final String name) {
        NodeList list = elem.getElementsByTagName(name);
        Node node = list.item(0);
        return node.getTextContent();
    }
}
