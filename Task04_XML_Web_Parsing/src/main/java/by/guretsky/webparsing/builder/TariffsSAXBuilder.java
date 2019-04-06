package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.builder.handler.TariffHandler;
import by.guretsky.webparsing.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Parser which based on SAX parser model.
 */
public class TariffsSAXBuilder extends ParseBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TariffsSAXBuilder.class);
    /**
     * XML reader object.
     */
    private XMLReader reader;
    /**
     * Class helper for SAX parser.
     */
    private TariffHandler handler;

    /**
     * Constructor - initialize tariffsHandler and creates SAX parser.
     */
    public TariffsSAXBuilder() {
        handler = new TariffHandler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException e) {
            LOGGER.error("SAX parser configuration error", e);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error", e);
        }
    }

    /**
     * Add tariffs in tariffs list.
     *
     * @param parsedTariffs tariffs to add
     */
    private void addTariffs(final List<Tariff> parsedTariffs) {
        this.getTariffs().addAll(parsedTariffs);
    }

    /**
     * Creates whole tariffs list. Calls method parse in SAX parser, it involves
     * tariffHandler.
     *
     * @param filePath file path.
     */
    @Override
    public void buildTariffs(final String filePath) {
        try {
            reader.parse(filePath);
        } catch (IOException e) {
            LOGGER.error("File error", e);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error", e);
        }
        addTariffs(handler.getTariffs());
    }
}
