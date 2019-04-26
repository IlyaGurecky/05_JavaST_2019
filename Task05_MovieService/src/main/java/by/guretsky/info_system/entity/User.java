package by.guretsky.info_system.entity;

import by.guretsky.info_system.entity.role.Role;

import java.util.Date;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private Role role;
    private String email;
    private String sex;
    private Date birthDate;
    private String country;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String userEmail) {
        this.email = userEmail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(final String userSex) {
        this.sex = userSex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String userCountry) {
        this.country = userCountry;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String userLogin) {
        this.login = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String userPassword) {
        this.password = userPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role userRole) {
        this.role = userRole;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date userBirthDate) {
        this.birthDate = userBirthDate;
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
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, role);
    }
}
