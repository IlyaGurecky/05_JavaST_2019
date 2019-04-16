package by.guretsky.info_system.entity;

import java.util.Date;

public class Watched extends Entity {
    private User user;
    private Film film;
    private Date viewingDate;

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

    public Date getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(final Date viewingDate) {
        this.viewingDate = viewingDate;
    }
}
