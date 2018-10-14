package com.epam.auction.factory;

import com.epam.auction.command.*;
import com.epam.auction.command.admin.*;

public class CommandFactory {

    public static Command create(String command) {

        command = command.toUpperCase();
        CommandEnum commandEnum = CommandEnum.valueOf(command);

        Command resultCommand;
        switch (commandEnum) {
            case LOGIN:
                resultCommand = new LoginCommand();
                break;
            case OFFERALOTPAGE:
                resultCommand = new OfferALotPageCommand();
                break;
            case MAIN:
                resultCommand = new MainCommand();
                break;
            case PROFILE:
                resultCommand = new ProfileCommand();
                break;
            case LOTINFO:
                resultCommand = new LotInfoCommand();
                break;
            case USERLOTS:
                resultCommand = new LotsCommand();
                break;
            case SIGNOUT:
                resultCommand = new SingOutCommand();
                break;
            case LOTMANAGEMENT:
                resultCommand = new LotManagementCommand();
                break;
            case USERMANAGEMENT:
                resultCommand = new UserManagementCommand();
                break;
            case FINDLOTS:
                resultCommand = new FindLotsCommand();
                break;
            case OFFERALOT:
                resultCommand = new OfferALotCommand();
                break;
            case REFUSELOT:
                resultCommand = new RefuseLotCommand();
                break;
            case CONFIRMLOT:
                resultCommand = new ConfirmLotCommand();
                break;
            case BANUSER:
                resultCommand = new BanUserCommand();
                break;
            case UNBANUSER:
                resultCommand = new UnbanUserCommand();
                break;
            case TOPUPBALANCE:
                resultCommand = new TopUpBalanceCommand();
                break;
            case PAYLOT:
                resultCommand = new PayLotCommand();
                break;
            case BID:
                resultCommand = new BidCommand();
                break;
            case LANGUAGE:
                resultCommand = new LanguageCommand();
                break;
            default:
                throw new IllegalArgumentException("Invalid command" + commandEnum);
        }
        return resultCommand;
    }
}
