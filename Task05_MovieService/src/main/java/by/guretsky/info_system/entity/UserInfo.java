package by.guretsky.info_system.entity;

public class UserInfo extends Entity {
    private String email;
    private String sex;
    private Integer age;
    private String country;
    private User user;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer userAge) {
        this.age = userAge;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String userCountry) {
        this.country = userCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
