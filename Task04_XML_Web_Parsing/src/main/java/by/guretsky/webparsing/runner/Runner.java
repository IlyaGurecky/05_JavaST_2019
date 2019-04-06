package by.guretsky.webparsing.runner;

import by.guretsky.webparsing.builder.Director;
import by.guretsky.webparsing.builder.TariffsDOMBuilder;
import by.guretsky.webparsing.builder.TariffsSAXBuilder;
import by.guretsky.webparsing.builder.TariffsStAXBuilder;
import by.guretsky.webparsing.entity.Tariff;

import java.io.File;
import java.util.List;

/**
 * Runner class.
 */
public final class Runner {
    /**
     * XML file path.
     */
    private static final String XML_PATH = "src"
            + File.separator + "main" + File.separator + "data"
            + File.separator + "tariffs.xml";

    /**
     * Private constructor.
     */
    private Runner() {
    }

    /**
     * This method runs application.
     *
     * @param args arguments from command line
     */
    public static void main(final String[] args) {
        Director director = new Director();

        System.out.println(">---------------DOM-----------------<");

        List<Tariff> tariffs = director.createTariffs(XML_PATH,
                new TariffsDOMBuilder());
        System.out.println(tariffs);

        System.out.println(">---------------StAX----------------<");

        List<Tariff> tariffs2 = director.createTariffs(XML_PATH,
                new TariffsStAXBuilder());
        System.out.println(tariffs2);

        System.out.println(">---------------SAX-----------------<");

        List<Tariff> tariffs1 = director.createTariffs(XML_PATH,
                new TariffsSAXBuilder());
        System.out.println(tariffs1);
    }
}
