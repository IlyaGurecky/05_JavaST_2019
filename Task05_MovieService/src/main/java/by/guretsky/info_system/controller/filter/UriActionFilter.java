package by.guretsky.info_system.controller.filter;

import by.guretsky.info_system.command.ActionCommand;
import by.guretsky.info_system.command.factory.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UriActionFilter implements Filter {
    private static final Logger LOGGER =
            LogManager.getLogger(UriActionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        LOGGER.debug("Processing uri", uri);
        int beginAction = contextPath.length();
        String actionName = uri.substring(beginAction);
        ActionFactory factory = new ActionFactory();
        ActionCommand action = factory.defineCommand(actionName);
        if (action != null) {
            request.setAttribute("action", action);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getServletContext().getRequestDispatcher("/jsp/error.jsp")
                    .forward(servletRequest, servletResponse);
        }
    }
}
