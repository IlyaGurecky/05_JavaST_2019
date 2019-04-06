package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract parser builder. Contains common tariffs list.
 */
public abstract class ParseBuilder {
    /**
     * Tariffs list.
     */
    private List<Tariff> tariffs = new ArrayList<>();

    /**
     * Empty method, which should be realized by descendants classes.
     *
     * @param filePath XML file path
     */
    public abstract void buildTariffs(String filePath);

    /**
     * Tariffs list getter.
     *
     * @return List of the tariffs.
     */
    List<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * Add tariff to tariffs list.
     *
     * @param tariff tariff to add
     */
    void addTariff(final Tariff tariff) {
        tariffs.add(tariff);
    }
}
