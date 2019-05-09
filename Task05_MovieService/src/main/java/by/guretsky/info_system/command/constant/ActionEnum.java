package by.guretsky.info_system.command.constant;

import by.guretsky.info_system.command.*;

public enum ActionEnum {
    SIGN_IN(new SignInCommand(), "/signin"),
    SIGN_UP(new SignUpCommand(), "/signup"),
    HOME(new HomeCommand(), "/home"),
    LOGOUT(new LogoutCommand(), "/logout");

    private ActionCommand command;
    private String actionName;

    ActionEnum(ActionCommand actionCommand, String name) {
        command = actionCommand;
        actionName = name;
    }

    public ActionCommand getCommand() {
        return command;
    }

    public String getActionName() {
        return actionName;
    }
}
