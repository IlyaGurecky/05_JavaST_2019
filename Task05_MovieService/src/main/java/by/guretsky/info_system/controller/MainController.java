package by.guretsky.info_system.controller;

import by.guretsky.info_system.command.ActionCommand;
import by.guretsky.info_system.command.CommandManager;
import by.guretsky.info_system.command.factory.CommandManagerFactory;
import by.guretsky.info_system.dao.connection.ConnectionPool;
import by.guretsky.info_system.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private static final Logger LOGGER =
            LogManager.getLogger(MainController.class);
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "movie_service_user";
    private static final String DB_PASSW = "password";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/movie_service_db?";
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
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest req,
                                HttpServletResponse resp)
            throws IOException, ServletException {
        ActionCommand action = (ActionCommand) req.getAttribute("action");
        try {
            CommandManager commandManager = CommandManagerFactory.getManager();
            String page = commandManager.execute(action, req);
            commandManager.close();
            if (page != null) {
                resp.sendRedirect(req.getContextPath() + page);
            } else {
                getServletContext().getRequestDispatcher("/jsp" + action.getName() + ".jsp").forward(req, resp);
            }

        } catch (CustomException e) {
            LOGGER.error("Data processing error", e);
            req.setAttribute("error", "Data processing error");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().invalidate();
    }
}
