package by.guretsky.info_system.entity;

import java.util.Date;

public class Film extends Entity {
    private String name;
    private Date premierDate;

    public String getName() {
        return name;
    }

    public void setName(String filmName) {
        this.name = filmName;
    }

    public Date getPremierDate() {
        return premierDate;
    }

    public void setPremierDate(Date filmPremierDate) {
        this.premierDate = filmPremierDate;
    }
}
