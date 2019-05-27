package by.guretsky.info_system.command.editor;

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

public class EditFilmCommand extends Command {
    private static final Logger LOGGER =
            LogManager.getLogger(EditFilmCommand.class);

    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        String name = request.getParameter("film_name");
        String premierDateStr = request.getParameter("film_premier_date");
        String description = request.getParameter("description");
        String filmCountry = request.getParameter("film_country");
        String filmCategory = request.getParameter("film_category");
        String filmId = request.getParameter("fId");

        if (isCorrectData(name, premierDateStr)) {
            FilmService service = factory.createService(FilmService.class);
            Film film = new Film();
            String filmImageName = null;
            try {
                filmImageName = uploadImage(request);
            } catch (IOException e) {
                LOGGER.error("Incorrect file path", e);
            } catch (ServletException e) {
                LOGGER.error("Servlet exc", e);
            }
            if (filmImageName != null && !filmImageName.isEmpty()) {
                film.setImageName(filmImageName);
            } else {
                Film oldFilmVersion = service.findById(Integer.parseInt(filmId));
                String oldFilmImage = oldFilmVersion.getImageName();
                film.setImageName(oldFilmImage);
            }
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                film.setPremierDate(format.parse(premierDateStr));
            } catch (ParseException e) {
                LOGGER.error("Date parse exception");
            }
            film.setName(name);
            film.setCountry(filmCountry);
            film.setCategory(filmCategory);
            film.setDescription(description);
            film.setId(Integer.parseInt(filmId));
            if (service.update(film)) {
                return PageManager.createPage(PageEnum.FILM);
            } else {
                return PageManager.createPage(PageEnum.ERROR);
            }

        } else {
            LOGGER.error("Film name or premier date is incorrect");
            throw new CustomException("Incorrect data");
        }
    }

    private boolean isCorrectData(final String name,
                                  final String premierDate) {
        return name != null && !name.isEmpty()
                && premierDate != null && !premierDate.isEmpty();
    }
}
