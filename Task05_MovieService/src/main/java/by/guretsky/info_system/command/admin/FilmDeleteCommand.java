package by.guretsky.info_system.command.admin;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class FilmDeleteCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request)
            throws CustomException {
        FilmService service = factory.createService(FilmService.class);
        Integer filmId = Integer.parseInt(request.getParameter("id"));
        service.delete(filmId);
        return PageManager.createPage(PageEnum.FILMS);
    }
}
