package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.Tariff;

import java.util.List;

public class Director {
    public List<Tariff> createTariffs(String path, ParseBuilder builder) {
        builder.buildTariffs(path);
        return builder.getTariffs();
    }
}
