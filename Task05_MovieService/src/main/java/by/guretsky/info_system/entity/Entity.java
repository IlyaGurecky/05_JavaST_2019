package by.guretsky.info_system.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer objId) {
        id = objId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
