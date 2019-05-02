package by.guretsky.info_system.entity;

import java.util.Objects;

public class Category extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String categoryName) {
        this.name = categoryName;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
