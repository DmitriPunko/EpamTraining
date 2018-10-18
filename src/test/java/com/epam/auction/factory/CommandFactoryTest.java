package com.epam.auction.factory;

import com.epam.auction.command.Command;
import com.epam.auction.command.LoginCommand;
import com.epam.auction.command.ProfileCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandFactoryTest {

    private static final String LOGIN = "login";
    private static final String PROFILE = "profile";

    @Test
    public void shouldCreateAndReturnLoginCommand() {
        Command command = CommandFactory.create(LOGIN);

        Class<? extends Command> commandClass = command.getClass();
        assertEquals(LoginCommand.class, commandClass);
    }

    @Test
    public void shouldCreateAndReturnProfileCommand() {
        Command command = CommandFactory.create(PROFILE);

        Class<? extends Command> commandClass = command.getClass();
        assertEquals(ProfileCommand.class, commandClass);
    }
}