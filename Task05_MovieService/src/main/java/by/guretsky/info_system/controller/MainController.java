package by.guretsky.info_system.controller;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.command.CommandManager;
import by.guretsky.info_system.command.EmptyCommand;
import by.guretsky.info_system.command.factory.ActionFactory;
import by.guretsky.info_system.command.factory.CommandManagerFactory;
import by.guretsky.info_system.dao.connection.ConnectionPool;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class MainController extends HttpServlet {
    private static final Logger LOGGER =
            LogManager.getLogger(MainController.class);
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "movie_service_user";
    private static final String DB_PASSW = "password";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/movie_service_db";
    private static final int TIMEOUT = 0;
    private static final int START_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 700;

    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().initialize(DB_USER, DB_PASSW, DB_URL,
                    START_POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, DB_DRIVER);
        } catch (CustomException e) {
            LOGGER.fatal("Application initialize error", e);
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CommandManager manager = CommandManagerFactory.getManager();
            JspPage page = manager.execute(new EmptyCommand(), req);
            manager.close();

            if (page.isRedirect()) {
                resp.sendRedirect(req.getContextPath() + page.getUri());
            } else {
                req.getRequestDispatcher("/jsp" + page.getUri() + ".jsp")
                        .forward(req, resp);
            }

        } catch (CustomException | IOException e) {
            LOGGER.error("Data processing error", e);
            req.setAttribute("error", "Server error");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String actionName = req.getParameter("command");
            Command action = ActionFactory.defineCommand(actionName);
            CommandManager manager = CommandManagerFactory.getManager();
            JspPage page = manager.execute(action, req);
            manager.close();

            resp.sendRedirect(req.getContextPath() + page.getUri());

        } catch (CustomException | IOException e) {
            LOGGER.error("Data processing error", e);
            req.setAttribute("error", "Server error");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().invalidate();
    }
}
