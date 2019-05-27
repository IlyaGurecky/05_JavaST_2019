package by.guretsky.info_system.controller.filter;

import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        JspPage page = (JspPage) request.getAttribute("page");

        HttpSession session = request.getSession(false);
        User user;

        if (session == null || session.getAttribute("user") == null) {
            user = new User();
            user.setRole(Role.UNAUTHORIZED);
        } else {
            user = (User) session.getAttribute("user");
        }

        if (page.getAllowRoles().isEmpty()
                || page.getAllowRoles().contains(user.getRole())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (user.getRole().equals(Role.UNAUTHORIZED)) {
            page = PageManager.createPage(PageEnum.SIGN_IN);
            response.sendRedirect(request.getContextPath() + page.getUri());
        } else {
            page = PageManager.createPage(PageEnum.HOME);
            response.sendRedirect(request.getContextPath() + page.getUri());
        }
    }
}
