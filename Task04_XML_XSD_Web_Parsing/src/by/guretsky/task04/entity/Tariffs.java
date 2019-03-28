package by.guretsky.task04.entity;

import java.util.ArrayList;
import java.util.List;

public class Tariffs {
    private List<Tariff> tariffs;

    public Tariffs() {
        tariffs = new ArrayList<>();
    }

    public void addTariff(final Tariff tariff) {
        tariffs.add(tariff);
    }

    public Tariff getTariff(final int index) {
        return tariffs.get(index);
    }
}
