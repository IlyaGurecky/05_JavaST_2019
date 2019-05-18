package by.guretsky.info_system.command.user;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;

import javax.servlet.http.HttpServletRequest;

public class WatchFilmCommand extends Command {
    @Override
    public JspPage execute(HttpServletRequest request) throws CustomException {
        return null;
    }
}
