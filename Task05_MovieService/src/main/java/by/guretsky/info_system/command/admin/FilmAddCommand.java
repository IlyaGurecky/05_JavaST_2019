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
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FilmAddCommand extends Command {
    private static final Logger LOGGER =
            LogManager.getLogger(FilmAddCommand.class);
    private static final String SPLIT_DIR_REGEX = "/";

    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        String name = request.getParameter("name");
        String premierDate = request.getParameter("premier_date");
        String country = request.getParameter("country");
        String category = request.getParameter("category");

        if (isCorrectData(name, premierDate)) {
            Film film = new Film();
            String imageName = null;
            try {
                Part part = request.getPart("filmImage");
                InputStream fileContent = part.getInputStream();
                imageName =
                        Paths.get(part.getSubmittedFileName()).getFileName()
                                .toString();
                String descPath = request.getServletContext()
                        .getRealPath("");
                String[] dirs = descPath.split(SPLIT_DIR_REGEX);
                StringBuilder buffer = new StringBuilder();
                for (int i = 0; i < dirs.length - 2; i++) {
                    buffer.append(dirs[i]);
                    buffer.append("/");
                }
                String pathToSave = buffer + "src/main/webapp/img/films/"
                        + imageName;
                OutputStream outputStream = new FileOutputStream(pathToSave);
                fileContent.transferTo(outputStream);
            } catch (IOException e) {
                LOGGER.error("File path is incorrect", e);
            } catch (ServletException e) {
                LOGGER.error("Servlet exc", e);
            }

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
