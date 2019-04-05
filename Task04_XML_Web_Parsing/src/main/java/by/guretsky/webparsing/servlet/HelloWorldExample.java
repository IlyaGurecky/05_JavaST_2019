package by.guretsky.webparsing.servlet;

import by.guretsky.webparsing.builder.Director;
import by.guretsky.webparsing.builder.TariffsDOMBuilder;
import by.guretsky.webparsing.entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/parser")
public class HelloWorldExample extends HttpServlet {
    private static final String XML_PATH = "/Users/ilyaguretsky/IdeaProjects/"
            + "epamtraining/05_JavaST_2019/Task04_XML_Web_Parsing/src"
            + "/main/data/tariffs.xml";

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        Director director = new Director();
        TariffsDOMBuilder domBuilder = new TariffsDOMBuilder();
        List<Tariff> tariffs = director.createTariffs(XML_PATH, domBuilder);
        request.setAttribute("res", tariffs);
        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
    }
}
