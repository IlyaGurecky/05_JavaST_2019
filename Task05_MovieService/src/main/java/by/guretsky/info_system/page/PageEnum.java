package by.guretsky.info_system.page;

import by.guretsky.info_system.entity.role.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum PageEnum {
    HOME("/home"),
    SIGN_IN("/signin", Role.UNAUTHORIZED),
    SIGN_UP("/signup", Role.UNAUTHORIZED),
    FILMS("/films"),
    USERS("/admin/users", Role.ADMIN),
    USER_ADD("/admin/user_add", Role.ADMIN),
    ERROR("/error"),
    CATEGORY("/category"),
    FILM_ADD("/admin/film_add", Role.ADMIN),
    SEE_LATER("/user/see_later", Role.USER);

    private String pageUri;
    private Set<Role> allowRoles = new HashSet<>();

    PageEnum(final String uri, final Role... roles) {
        pageUri = uri;
        allowRoles.addAll(Arrays.asList(roles));
    }

    public String getPageUri() {
        return pageUri;
    }

    public Set<Role> getAllowRoles() {
        return allowRoles;
    }
}
