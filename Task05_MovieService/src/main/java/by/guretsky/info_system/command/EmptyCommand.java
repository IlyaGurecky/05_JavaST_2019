package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmptyCommand extends Command {
    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        JspPage page = (JspPage) request.getAttribute("page");

        if (page.getUri().equals(PageEnum.FILMS.getPageUri())) {
            FilmService service = factory.createService(FilmService.class);
            List<Film> films = service.readAll();
            request.setAttribute("films", films);
            return page;
        }

        if (page.getUri().equals(PageEnum.USERS.getPageUri())) {
            UserService service = factory.createService(UserService.class);
            List<User> users = service.readAll();
            request.setAttribute("users", users);
            return page;
        }

        if (page.getUri().equals(PageEnum.CATEGORY.getPageUri())) {
            String category = request.getParameter("name");
            FilmService service = factory.createService(FilmService.class);
            if(category != null) {
                List<Film> films = service.findByCategory(category);
                request.setAttribute("films", films);
            }
            return page;
        }

        if ((page.getUri().equals(PageEnum.SIGN_IN.getPageUri())
                || page.getUri().equals(PageEnum.SIGN_UP.getPageUri()))
                && request.getSession(false) != null
                && request.getSession().getAttribute("user") != null) {
            page = PageManager.createPage(PageEnum.HOME);
            page.setRedirect(true);
            return page;
        }

        return page;
    }
}
