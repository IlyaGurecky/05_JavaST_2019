package by.guretsky.info_system.entity;

public class FilmInfo extends Entity {
    private String country;
    private String producer;
    private String actors;
    private String imageName;
    private Category category;
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(final Film film) {
        this.film = film;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String filmCountry) {
        this.country = filmCountry;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(final String filmProducer) {
        this.producer = filmProducer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(final String filmActors) {
        this.actors = filmActors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(final String name) {
        this.imageName = name;
    }
}
