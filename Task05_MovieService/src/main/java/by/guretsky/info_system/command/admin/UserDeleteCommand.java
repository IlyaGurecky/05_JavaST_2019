package by.guretsky.info_system.command.admin;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;
import by.guretsky.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserDeleteCommand extends Command {
    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        UserService service = factory.createService(UserService.class);
        String id = request.getParameter("id");
        Integer userId = Integer.parseInt(id);
        service.delete(userId);
        return PageManager.createPage(PageEnum.USERS);
    }
}