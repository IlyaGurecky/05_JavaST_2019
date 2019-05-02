package by.guretsky.info_system.command.constant;

import by.guretsky.info_system.command.ActionCommand;
import by.guretsky.info_system.command.AuthorizationCommand;
import by.guretsky.info_system.command.SignUpCommand;

public enum ActionEnum {
    AUTHORIZATION(new AuthorizationCommand(), "/authorization"),
    SIGN_UP(new SignUpCommand(), "/signup");

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
