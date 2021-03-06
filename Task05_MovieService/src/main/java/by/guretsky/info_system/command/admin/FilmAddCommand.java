package by.guretsky.info_system.command.admin;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FilmAddCommand extends Command {
    private static final Logger LOGGER =
            LogManager.getLogger(FilmAddCommand.class);

    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        String name = request.getParameter("name");
        String premierDate = request.getParameter("premier_date");
        String country = request.getParameter("country");
        String category = request.getParameter("category");

        if (isCorrectData(name, premierDate)) {
            String imageName = null;
            try {
                imageName = uploadImage(request);
            } catch (IOException e) {
                LOGGER.error("File path is incorrect", e);
            } catch (ServletException e) {
                LOGGER.error("Servlet exc", e);
            }

            Film film = new Film();
            film.setImageName(imageName);
            film.setName(name);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                film.setPremierDate(format.parse(premierDate));
            } catch (ParseException e) {
                LOGGER.error("Date parse error");
            }
            film.setCategory(category);
            film.setCountry(country);
            FilmService service = factory.createService(FilmService.class);
            if (service.create(film) != 0) {
                return PageManager.createPage(PageEnum.FILMS);
            }
        }
        return PageManager.createPage(PageEnum.FILM_ADD);
    }

    private boolean isCorrectData(final String name,
                                  final String date) {
        return name != null && !name.isEmpty()
                && date != null && !date.isEmpty();
    }
}
