package by.guretsky.webparsing.servlet;

import by.guretsky.webparsing.builder.Director;
import by.guretsky.webparsing.builder.TariffsDOMBuilder;
import by.guretsky.webparsing.builder.TariffsSAXBuilder;
import by.guretsky.webparsing.builder.TariffsStAXBuilder;
import by.guretsky.webparsing.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet class, which process the request for changing the page language and
 * XML parsing.
 */
@WebServlet("/servlet")
@MultipartConfig
public class ParseServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ParseServlet.class);

    /**
     * Process request for changing page localization.
     *
     * @param request  page request
     * @param response response
     */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("sessionLocale", locale);
        try {
            request.getRequestDispatcher("/jsp/index.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception", e);
        } catch (IOException e) {
            LOGGER.error("IO Exception", e);
        }

    }

    /**
     * Process request for parsing XML file.
     *
     * @param request  page request
     * @param response response
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try {
            String newFilePath = "/Users/ilyaguretsky/IdeaProjects/"
                    + "epamtraining/05_JavaST_2019/Task04_XML_Web_Parsing"
                    + "/src/main/webapp/xml/temp.xml";
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            OutputStream stream = new FileOutputStream(newFilePath);
            fileContent.transferTo(stream);

            Director director = new Director();
            String param = request.getParameter("parser");
            List<Tariff> tariffs = new ArrayList<>();
            switch (param) {
                case "dom":
                    TariffsDOMBuilder domBuilder = new TariffsDOMBuilder();
                    tariffs = director.createTariffs(newFilePath, domBuilder);
                    break;
                case "sax":
                    TariffsSAXBuilder saxBuilder = new TariffsSAXBuilder();
                    tariffs = director.createTariffs(newFilePath, saxBuilder);
                    break;
                case "stax":
                    TariffsStAXBuilder stAXBuilder = new TariffsStAXBuilder();
                    tariffs = director.createTariffs(newFilePath, stAXBuilder);
                    break;
                default:
                    break;
            }
            request.setAttribute("res", tariffs);
            request.getRequestDispatcher("/jsp/result.jsp")
                    .forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("Servlet exception", e);
        } catch (IOException e) {
            LOGGER.error("IO Exception", e);
        }
    }
}
