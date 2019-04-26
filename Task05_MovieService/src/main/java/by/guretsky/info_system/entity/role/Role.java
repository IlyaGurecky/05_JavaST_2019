package by.guretsky.info_system.entity.role;

public enum Role {
    ADMIN("admin"),
    EDITOR("editor"),
    USER("user");

    private String value;

    Role(final String role) {
        value = role;
    }

    public String getValue() {
        return value;
    }
}
