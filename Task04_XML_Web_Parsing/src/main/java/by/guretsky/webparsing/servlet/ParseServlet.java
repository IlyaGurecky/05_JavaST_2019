package by.guretsky.webparsing.servlet;

import by.guretsky.webparsing.builder.Director;
import by.guretsky.webparsing.builder.TariffsDOMBuilder;
import by.guretsky.webparsing.builder.TariffsSAXBuilder;
import by.guretsky.webparsing.builder.TariffsStAXBuilder;
import by.guretsky.webparsing.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/parser")
public class ParseServlet extends HttpServlet {
    private static final String XML_PATH = "/Users/ilyaguretsky/IdeaProjects/"
            + "epamtraining/05_JavaST_2019/Task04_XML_Web_Parsing/src"
            + "/main/data/tariffs.xml";

    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(final HttpServletRequest request,
                                final HttpServletResponse response) throws
            IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        Director director = new Director();
        String param = request.getParameter("parser");
        List<Tariff> tariffs = new ArrayList<>();
        switch (param) {
            case "dom":
                TariffsDOMBuilder domBuilder = new TariffsDOMBuilder();
                tariffs = director.createTariffs(XML_PATH, domBuilder);
                break;
            case "sax":
                TariffsSAXBuilder saxBuilder = new TariffsSAXBuilder();
                tariffs = director.createTariffs(XML_PATH, saxBuilder);
                break;
            case "stax":
                TariffsStAXBuilder stAXBuilder = new TariffsStAXBuilder();
                tariffs = director.createTariffs(XML_PATH, stAXBuilder);
                break;
            default:
        }
        request.setAttribute("res", tariffs);
        request.getRequestDispatcher("/jsp/result.jsp")
                .forward(request, response);
    }
}
