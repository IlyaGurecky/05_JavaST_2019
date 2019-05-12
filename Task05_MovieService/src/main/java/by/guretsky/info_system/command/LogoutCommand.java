package by.guretsky.info_system.command;

import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends Command {
    @Override
    public JspPage execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        return PageManager.createPage(PageEnum.HOME);
    }
}
