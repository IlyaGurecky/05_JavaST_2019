package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.SeeLaterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteFromSeeLaterCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request) throws CustomException {
        Integer filmId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        SeeLaterService service = factory.createService(SeeLaterService.class);
        service.deleteByUserAndFilmId(userId, filmId);
        return PageManager.createPage(PageEnum.SEE_LATER);
    }
}
