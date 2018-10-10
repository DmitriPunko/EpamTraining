package com.epam.auction.factory;

import com.epam.auction.command.*;
import com.epam.auction.command.admin.*;

public class CommandFactory {

    public static Command create(String command) {

        command = command.toUpperCase();
        CommandEnum commandEnum = CommandEnum.valueOf(command);

        switch (commandEnum) {
            case LOGIN:
                return new LoginCommand();
            case OFFERALOTPAGE:
                return new OfferALotPageCommand();
            case MAIN:
                return new MainCommand();
            case PROFILE:
                return new ProfileCommand();
            case LOTINFO:
                return new LotInfoCommand();
            case USERLOTS:
                return new LotsCommand();
            case SIGNOUT:
                return new SingOutCommand();
            case LOTMANAGEMENT:
                return new LotManagementCommand();
            case USERMANAGEMENT:
                return new UserManagementCommand();
            case FINDLOTS:
                return new FindLotsCommand();
            case OFFERALOT:
                return new OfferALotCommand();
            case REFUSELOT:
                return new RefuseLotCommand();
            case CONFIRMLOT:
                return new ConfirmLotCommand();
            case BANUSER:
                return new BanUserCommand();
            case UNBANUSER:
                return new UnbanUserCommand();
            default:
                throw new IllegalArgumentException("Invalid command" + commandEnum);
        }
    }
}
