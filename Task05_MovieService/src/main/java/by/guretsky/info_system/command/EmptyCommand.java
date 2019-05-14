package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;
import by.guretsky.info_system.service.SeeLaterService;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

public class EmptyCommand extends Command {
    private static final int ONE_PAGE_FILMS_LIMIT = 4;

    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        JspPage jspPage = (JspPage) request.getAttribute("page");

        //This block is used for load films by category
        if (jspPage.getUri().equals(PageEnum.CATEGORY.getPageUri())) {
            String category = request.getParameter("category");
            if (category != null && !category.isEmpty()) {
                FilmService service = factory.createService(FilmService.class);
                List<Film> films = service.findByCategory(category);
                request.setAttribute("films", films);
                return jspPage;
            }
            return PageManager.createPage(PageEnum.ERROR);
        }

        //This block is used for load films, which in the user "See Later" list
        if (jspPage.getUri().equals(PageEnum.FILMS.getPageUri())
                && isAuthorizedUser(request)) {
            SeeLaterService service =
                    factory.createService(SeeLaterService.class);
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            List<SeeLater> seeLater = service.readAllByUserId(user.getId());
            List<Film> seeLaterFilms = new LinkedList<>();
            seeLater.stream().map(SeeLater::getFilm).forEach(seeLaterFilms::add);
            request.setAttribute("seeLater", seeLaterFilms);
        }

        //This block is used for search films
        if (jspPage.getUri().equals(PageEnum.FILMS.getPageUri())
                && request.getParameter("fn") != null) {
            FilmService service = factory.createService(FilmService.class);
            request.setAttribute("films",
                    service.findByName(request.getParameter("fn")));
            request.setAttribute("isAfterSearch", true);
            return jspPage;
        }

        //This block is used for load all films considering pagination
        if (jspPage.getUri().equals(PageEnum.FILMS.getPageUri())) {
            FilmService service = factory.createService(FilmService.class);
            String currentPage = request.getParameter("page");
            int pageNumber = 1;
            if (currentPage != null && !currentPage.isEmpty()) {
                pageNumber = Integer.parseInt(currentPage);
            }
            int amountOfPages = (int) Math.ceil(service.countFilms() * 1.0
                    / ONE_PAGE_FILMS_LIMIT);
            List<Film> films = service.readAll(pageNumber,
                    ONE_PAGE_FILMS_LIMIT);
            request.setAttribute("amount_of_pages", amountOfPages);
            request.setAttribute("pageNumber", pageNumber);

            request.setAttribute("films", films);
        }

        //This block is used for load users list
        if (jspPage.getUri().equals(PageEnum.USERS.getPageUri())) {
            UserService service = factory.createService(UserService.class);
            List<User> users = service.readAll();
            request.setAttribute("users", users);
            return jspPage;
        }
        return jspPage;
    }

    public boolean isAuthorizedUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            return user != null && !user.getRole().equals(Role.UNAUTHORIZED);
        }
        return false;
    }
}
