package by.guretsky.info_system.command.factory;

import by.guretsky.info_system.command.Command;
import by.guretsky.info_system.command.EmptyCommand;
import by.guretsky.info_system.command.constant.ActionEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ActionFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(ActionFactory.class);

    private ActionFactory() {
    }

    public static Command defineCommand(final String actionName) {
        if (actionName == null || actionName.isEmpty()) {
            return new EmptyCommand();
        }
        List<ActionEnum> enums =
                new ArrayList<>(Arrays.asList(ActionEnum.values()));
        ActionEnum constant = enums.stream()
                .filter(actionEnum -> actionEnum.getActionName()
                        .equals(actionName))
                .findAny().orElse(null);
        if (constant != null) {
            LOGGER.debug("Successful search for action: " + actionName);
            return constant.getCommand();
        } else {
            LOGGER.debug("Unknown action");
            return new EmptyCommand();
        }
    }
}
