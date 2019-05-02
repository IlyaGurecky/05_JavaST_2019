package by.guretsky.info_system.entity;

import java.util.Date;
import java.util.Objects;

public class Film extends Entity {
    private String name;
    private Date premierDate;
    private Country country;
    private String imageName;
    private Category category;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(final String filmName) {
        this.name = filmName;
    }

    public Date getPremierDate() {
        return premierDate;
    }

    public void setPremierDate(final Date filmPremierDate) {
        this.premierDate = filmPremierDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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
        Film film = (Film) o;
        return Objects.equals(name, film.name)
                && Objects.equals(premierDate, film.premierDate)
                && Objects.equals(country, film.country)
                && Objects.equals(imageName, film.imageName)
                && Objects.equals(category, film.category)
                && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, premierDate, country,
                imageName, category, description);
    }
}
