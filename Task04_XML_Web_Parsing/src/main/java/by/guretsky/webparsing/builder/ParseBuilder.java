package by.guretsky.webparsing.builder;

import by.guretsky.webparsing.entity.Tariff;
import java.util.ArrayList;
import java.util.List;

public abstract class ParseBuilder {
    private List<Tariff> tariffs = new ArrayList<>();

    public abstract void buildTariffs(String filePath);

    List<Tariff> getTariffs() {
        return tariffs;
    }

    void addTariff(Tariff tariff) {
        tariffs.add(tariff);
    }
}
