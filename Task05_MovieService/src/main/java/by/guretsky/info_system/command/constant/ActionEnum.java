package by.guretsky.info_system.command.constant;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.command.LogoutCommand;
import by.guretsky.info_system.command.SignInCommand;
import by.guretsky.info_system.command.SignUpCommand;
import by.guretsky.info_system.command.admin.UserAddCommand;
import by.guretsky.info_system.command.admin.UserDeleteCommand;
import by.guretsky.info_system.command.user.AddToSeeLaterCommand;

public enum ActionEnum {
    SIGN_IN(new SignInCommand(), "signin"),
    SIGN_UP(new SignUpCommand(), "signup"),
    LOGOUT(new LogoutCommand(), "signout"),
    DELETE_USER(new UserDeleteCommand(), "deleteUser"),
    ADD_USER(new UserAddCommand(), "addUser"),
    ADD_TO_SEE_LATER(new AddToSeeLaterCommand(), "addToSeeLater");

    private Command command;
    private String actionName;

    ActionEnum(Command command, String name) {
        this.command = command;
        actionName = name;
    }

    public Command getCommand() {
        return command;
    }

    public String getActionName() {
        return actionName;
    }
}
