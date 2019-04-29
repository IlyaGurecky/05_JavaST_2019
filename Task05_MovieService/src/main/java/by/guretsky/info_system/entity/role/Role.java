package by.guretsky.info_system.entity.role;

public enum Role {
    ADMIN("admin", 0),
    EDITOR("editor", 1),
    USER("user", 2);

    private String value;
    private int id;

    Role(final String role, int identity) {
        value = role;
        id = identity;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
