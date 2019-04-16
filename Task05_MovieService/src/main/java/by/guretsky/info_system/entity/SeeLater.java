package by.guretsky.info_system.entity;

import java.util.Date;

public class SeeLater extends Entity {
    private User user;
    private Film film;
    private Date addedDate;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(final Film film) {
        this.film = film;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(final Date addedDate) {
        this.addedDate = addedDate;
    }
}
