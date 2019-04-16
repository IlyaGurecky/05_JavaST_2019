package by.guretsky.info_system.entity;

public class User extends Entity {
    private String login;
    private String password;
    private Role role;

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
}
