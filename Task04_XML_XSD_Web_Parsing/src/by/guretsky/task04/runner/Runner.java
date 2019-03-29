package by.guretsky.task04.runner;

import by.guretsky.task04.builder.Director;
import by.guretsky.task04.builder.TariffsDOMBuilder;
import by.guretsky.task04.builder.TariffsSAXBuilder;
import by.guretsky.task04.builder.TariffsStAXBuilder;
import by.guretsky.task04.entity.Tariff;

import java.io.File;
import java.util.List;

public class Runner {
    private static final String XML_PATH = "data"
            + File.separator + "tariffs.xml";

    public static void main(String[] args) {
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
