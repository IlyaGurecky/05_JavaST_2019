package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;
import by.guretsky.info_system.service.SeeLaterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddToSeeLaterCommand extends Command {
    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            FilmService filmService = factory.createService(FilmService.class);
            Integer filmId = Integer.parseInt(request.getParameter("filmId"));
            Film film = filmService.findById(filmId);
            User user = (User) session.getAttribute("user");
            SeeLater seeLater = new SeeLater();
            seeLater.setUser(user);
            seeLater.setFilm(film);
            SeeLaterService service =
                    factory.createService(SeeLaterService.class);
            if (service.create(seeLater) == 0) {
                return PageManager.createPage(PageEnum.ERROR);
            }

        }
        return PageManager.createPage(PageEnum.FILMS);
    }
}
