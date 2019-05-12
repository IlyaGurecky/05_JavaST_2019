package by.guretsky.info_system.page;

import by.guretsky.info_system.entity.role.Role;

import java.util.HashSet;
import java.util.Set;

public class JspPage {
    private Set<Role> allowRoles = new HashSet<>();
    private String uri;
    private boolean isRedirect = false;
    //private String parameters;

    public JspPage(final String pageUri, final Set<Role> roles) {
        allowRoles.addAll(roles);
        uri = pageUri;
    }

    public void setAllowRoles(final Set<Role> allowRoles) {
        this.allowRoles = allowRoles;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(final boolean redirect) {
        isRedirect = redirect;
    }

    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }


}
