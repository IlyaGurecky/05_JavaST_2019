package by.guretsky.task04.builder;

import by.guretsky.task04.entity.Tariff;

import java.util.List;

public class Director {
    public List<Tariff> createTariffs(String path, ParseBuilder builder) {
        builder.buildTariffs(path);
        return builder.getTariffs();
    }
}
