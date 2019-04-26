package by.guretsky.info_system.entity;

import java.util.Date;
import java.util.Objects;

public class Film extends Entity {
    private String name;
    private Date premierDate;
    private String country;
    private String producer;
    private String actors;
    private String imageName;
    private String category;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(final String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(final String actors) {
        this.actors = actors;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
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
                && Objects.equals(producer, film.producer)
                && Objects.equals(actors, film.actors)
                && Objects.equals(imageName, film.imageName)
                && Objects.equals(category, film.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, premierDate, country,
                producer, actors, imageName, category);
    }
}
