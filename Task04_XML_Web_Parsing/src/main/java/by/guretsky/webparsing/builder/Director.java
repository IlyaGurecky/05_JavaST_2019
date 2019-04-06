package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.Tariff;

import java.util.List;

/**
 * Class for handling requests to parse document using specified parser.
 */
public class Director {
    /**
     * This method call build method of the special parser.
     *
     * @param path    XML file path
     * @param builder parser
     * @return list of the tariffs
     */
    public List<Tariff> createTariffs(final String path,
                                      final ParseBuilder builder) {
        builder.buildTariffs(path);
        return builder.getTariffs();
    }
}
