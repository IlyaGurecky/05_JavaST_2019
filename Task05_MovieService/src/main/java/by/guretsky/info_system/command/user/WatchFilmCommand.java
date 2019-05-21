package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.WatchedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WatchFilmCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request) throws
            CustomException {
        String filmIdStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (filmIdStr != null && !filmIdStr.isEmpty()) {
            Integer filmId = Integer.parseInt(filmIdStr);
            Integer userId = user.getId();
            WatchedService service = factory.createService(WatchedService.class);
            Integer watchedId = service.findIdByUserAndFilmId(userId, filmId);
            if (watchedId != null) {
                service.updateViewingDate(userId, filmId);
            } else {
                Watched watched = new Watched();
                Film film = new Film();
                film.setId(filmId);
                watched.setFilm(film);
                watched.setUser(user);
                service.create(watched);
            }
        }
        return PageManager.createPage(PageEnum.WATCHED);
    }
}
