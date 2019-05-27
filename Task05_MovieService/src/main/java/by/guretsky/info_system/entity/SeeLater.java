package by.guretsky.info_system.entity;

import java.util.Date;
import java.util.Objects;

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

    public void setAddedDate(final Date date) {
        this.addedDate = date;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SeeLater seeLater = (SeeLater) o;
        return Objects.equals(user, seeLater.user)
                && Objects.equals(film, seeLater.film)
                && Objects.equals(addedDate, seeLater.addedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, film, addedDate);
    }
}
