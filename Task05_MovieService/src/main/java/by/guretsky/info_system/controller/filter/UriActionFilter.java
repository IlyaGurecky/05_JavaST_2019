package by.guretsky.info_system.controller.filter;

import by.guretsky.info_system.page.JspPage;
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
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String contextPath = request.getContextPath();
            String uri = request.getRequestURI();
            LOGGER.debug("Processing uri", uri);
            int beginAction = contextPath.length();
            String pageUri = uri.substring(beginAction);
            JspPage page = PageManager.defineAndGet(pageUri);
            if (page != null) {
                request.setAttribute("page", page);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.getServletContext()
                        .setAttribute("error", "Unknown " + uri + " page");
                request.getServletContext()
                        .getRequestDispatcher("/jsp/error.jsp")
                        .forward(servletRequest, servletResponse);
            }
        } else {
            LOGGER.error("It's impossible to use HTTP filter");
            servletRequest.getServletContext()
                    .getRequestDispatcher("/jsp/error.jsp")
                    .forward(servletRequest, servletResponse);
        }
    }
}
