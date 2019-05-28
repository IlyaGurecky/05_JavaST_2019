package by.guretsky.info_system.command;

import by.guretsky.info_system.entity.Film;
import by.guretsky.info_system.entity.SeeLater;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.Watched;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;
import by.guretsky.info_system.service.SeeLaterService;
import by.guretsky.info_system.service.UserService;
import by.guretsky.info_system.service.WatchedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmptyCommand extends Command {
    private static final String MESSAGE_ATTRIBUTE = "msg";
    private static final String SUCCESS_ATTRIBUTE = "success_msg";
    private static final String AMOUNT_OF_PAGES_ATTR = "amount_of_pages";
    private static final String PAGE_NUMBER_ATTR = "pageNumber";
    private static final int ONE_PAGE_FILMS_LIMIT = 4;
    private static final int ONE_PAGE_USERS_LIMIT = 5;
    private static final int ONE_PAGE_WATCHED_FILMS_LIMIT = 5;
    private static final int ONE_PAGE_SEE_LATER_FILMS_LIMIT = 5;

    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        JspPage jspPage = (JspPage) request.getAttribute("page");

        if (jspPage.getUri().equals(PageEnum.SIGN_IN.getPageUri())) {
            if (request.getParameter(MESSAGE_ATTRIBUTE) != null
                    && request.getParameter(MESSAGE_ATTRIBUTE).equals("err")) {
                request.setAttribute(MESSAGE_ATTRIBUTE,
                        "Login or password is incorrect");
            } else if (request.getParameter(MESSAGE_ATTRIBUTE) != null
                    && request.getParameter(MESSAGE_ATTRIBUTE).equals("success")) {
                request.setAttribute(SUCCESS_ATTRIBUTE,
                        "You have successfully registered, please sign in");
            }
            return jspPage;
        }

        if (jspPage.getUri().equals(PageEnum.SIGN_UP.getPageUri())
                && request.getParameter(MESSAGE_ATTRIBUTE) != null) {
            switch (request.getParameter(MESSAGE_ATTRIBUTE)) {
                case "cr_err":
                    request.setAttribute(MESSAGE_ATTRIBUTE,
                            "User create error");
                    break;
                case "em_err":
                    request.setAttribute(MESSAGE_ATTRIBUTE,
                            "This email already exists");
                    break;
                case "log_err":
                    request.setAttribute(MESSAGE_ATTRIBUTE,
                            "This login already exists");
                    break;
                default:
                    return jspPage;
            }
        }

        //This block is used for load films page
        if (jspPage.getUri().equals(PageEnum.FILMS.getPageUri())) {
            try {
                jspPage = loadFilmsPage(request);
            } catch (NumberFormatException e) {
                return PageManager.createPage(PageEnum.ERROR);
            }
        }

        //This block is used for load users page
        if (jspPage.getUri().equals(PageEnum.USERS.getPageUri())) {
            try {
                jspPage = loadUsersPage(request);
            } catch (NumberFormatException e) {
                return PageManager.createPage(PageEnum.ERROR);
            }
        }

        //This block is used for load watched page
        if (jspPage.getUri().equals(PageEnum.WATCHED.getPageUri())) {
            try {
                jspPage = loadWatchedPage(request);
            } catch (NumberFormatException e) {
                return PageManager.createPage(PageEnum.ERROR);
            }
        }

        //This block is used for load "See later" page
        if (jspPage.getUri().equals(PageEnum.SEE_LATER.getPageUri())) {
            try {
                jspPage = loadSeeLaterPage(request);
            } catch (NumberFormatException e) {
                return PageManager.createPage(PageEnum.ERROR);
            }

        }

        //This block is used for load film by id on film.jsp
        if (jspPage.getUri().equals(PageEnum.FILM.getPageUri())) {
            return loadFilmPage(request);
        }

        return jspPage;
    }

    private JspPage loadWatchedPage(final HttpServletRequest request)
            throws CustomException {
        WatchedService service = factory.createService(WatchedService.class);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int pageNumber = 1;
        String currentPage = request.getParameter("page");
        if (currentPage != null && !currentPage.isEmpty()) {
            pageNumber = Integer.parseInt(currentPage);

        }
        Integer amountOfFilms = service.countFilms(user.getId());
        List<Watched> watchedList = service.readAllByUserId(user.getId(),
                pageNumber, ONE_PAGE_WATCHED_FILMS_LIMIT);
        int amountOfPages = (int) Math.ceil(amountOfFilms * 1.0
                / ONE_PAGE_USERS_LIMIT);
        request.setAttribute(AMOUNT_OF_PAGES_ATTR, amountOfPages);
        request.setAttribute(PAGE_NUMBER_ATTR, pageNumber);
        request.setAttribute("amountOfFilms", amountOfFilms);
        request.setAttribute("watchedFilms", watchedList);
        return PageManager.createPage(PageEnum.WATCHED);
    }

    private JspPage loadSeeLaterPage(final HttpServletRequest request)
            throws CustomException {
        SeeLaterService service = factory.createService(SeeLaterService.class);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int pageNumber = 1;
        String currentPage = request.getParameter("page");
        if (currentPage != null && !currentPage.isEmpty()) {
            pageNumber = Integer.parseInt(currentPage);
        }
        int amountOfPages = (int) Math.ceil(service.countFilms(user.getId())
                * 1.0 / ONE_PAGE_USERS_LIMIT);
        List<SeeLater> films = service.readAllByUserId(user.getId(), pageNumber,
                ONE_PAGE_SEE_LATER_FILMS_LIMIT);
        request.setAttribute(AMOUNT_OF_PAGES_ATTR, amountOfPages);
        request.setAttribute(PAGE_NUMBER_ATTR, pageNumber);
        request.setAttribute("seeLaterFilms", films);
        return PageManager.createPage(PageEnum.SEE_LATER);
    }

    private JspPage loadUsersPage(final HttpServletRequest request)
            throws CustomException {
        UserService service = factory.createService(UserService.class);
        List<User> users = new ArrayList<>();
        final String userLoginAttr = "u_login";
        if (request.getParameter(userLoginAttr) != null
                && !request.getParameter(userLoginAttr).isEmpty()) {
            String login = request.getParameter(userLoginAttr);
            User user = service.findByLogin(login);
            if (user != null) {
                users.add(user);
            }
            request.setAttribute("isAfterSearch", true);
        } else {
            int pageNumber = 1;
            String currentPage = request.getParameter("page");
            if (currentPage != null && !currentPage.isEmpty()) {
                pageNumber = Integer.parseInt(currentPage);
            }
            int amountOfPages = (int) Math.ceil(service.countUsers() * 1.0
                    / ONE_PAGE_USERS_LIMIT);
            users = service.readAll(pageNumber,
                    ONE_PAGE_USERS_LIMIT);
            request.setAttribute(AMOUNT_OF_PAGES_ATTR, amountOfPages);
            request.setAttribute(PAGE_NUMBER_ATTR, pageNumber);
        }
        request.setAttribute("users", users);
        return PageManager.createPage(PageEnum.USERS);
    }

    private boolean isAuthorizedUser(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            return user != null && !user.getRole().equals(Role.UNAUTHORIZED);
        }
        return false;
    }

    private List<SeeLater> loadSeeLaterFilmsNoPagination(
            final HttpServletRequest request) throws CustomException {
        SeeLaterService service =
                factory.createService(SeeLaterService.class);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        return service.readAllByUserId(user.getId());
    }

    private JspPage loadFilmPage(final HttpServletRequest request)
            throws CustomException {
        String filmId = request.getParameter("fId");
        if (filmId != null && !filmId.isEmpty()) {
            FilmService service = factory.createService(FilmService.class);
            Film film = service.findById(Integer.parseInt(filmId));
            if (film == null) {
                return PageManager.createPage(PageEnum.ERROR);
            }
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                List<SeeLater> seeLaterFilms =
                        loadSeeLaterFilmsNoPagination(request);
                boolean isInList =
                        seeLaterFilms.stream().map(SeeLater::getFilm)
                                .anyMatch(film1 -> film.getId()
                                        .equals(film1.getId()));
                request.setAttribute("isInList", isInList);
            }
            request.setAttribute("film", film);
            return PageManager.createPage(PageEnum.FILM);
        } else {
            JspPage newPage = PageManager.createPage(PageEnum.FILMS);
            newPage.setRedirect(true);
            return newPage;
        }
    }

    private JspPage loadFilmsPage(final HttpServletRequest request)
            throws CustomException {
        FilmService service = factory.createService(FilmService.class);
        if (isAuthorizedUser(request)) {
            List<SeeLater> seeLater = loadSeeLaterFilmsNoPagination(request);
            List<Film> seeLaterFilms = new LinkedList<>();
            seeLater.stream().map(SeeLater::getFilm).forEach(seeLaterFilms::add);
            request.setAttribute("seeLater", seeLaterFilms);
        }

        if (request.getParameter("fn") != null) {
            request.setAttribute("films",
                    service.findByName(request.getParameter("fn")));
            request.setAttribute("isAfterSearch", true);
            return PageManager.createPage(PageEnum.FILMS);
        }

        if (request.getParameter("cName") != null) {
            String category = request.getParameter("cName");
            if (category != null && !category.isEmpty()) {
                List<Film> films = service.findByCategory(category);
                request.setAttribute("films", films);
                return PageManager.createPage(PageEnum.FILMS);
            } else {
                return PageManager.createPage(PageEnum.ERROR);
            }
        }

        String currentPage = request.getParameter("page");
        int pageNumber = 1;
        if (currentPage != null && !currentPage.isEmpty()) {
            pageNumber = Integer.parseInt(currentPage);
        }
        int amountOfPages = (int) Math.ceil(service.countFilms() * 1.0
                / ONE_PAGE_FILMS_LIMIT);
        List<Film> films = service.readAll(pageNumber,
                ONE_PAGE_FILMS_LIMIT);
        request.setAttribute(AMOUNT_OF_PAGES_ATTR, amountOfPages);
        request.setAttribute(PAGE_NUMBER_ATTR, pageNumber);
        request.setAttribute("films", films);
        return PageManager.createPage(PageEnum.FILMS);
    }
}
