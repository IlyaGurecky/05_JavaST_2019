package by.guretsky.webparsing.builder;

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

public class TariffsSAXBuilder extends ParseBuilder {
    private static final Logger LOGGER =
            LogManager.getLogger(TariffsSAXBuilder.class);
    private XMLReader reader;
    private TariffHandler handler;

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

    private void addTariffs(List<Tariff> parsedTariffs) {
        this.getTariffs().addAll(parsedTariffs);
    }

    @Override
    public void buildTariffs(String filePath) {
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
