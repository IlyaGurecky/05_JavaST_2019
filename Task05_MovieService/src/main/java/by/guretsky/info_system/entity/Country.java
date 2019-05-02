package by.guretsky.info_system.entity;

import java.util.Objects;

public class Country extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String countryName) {
        this.name = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
