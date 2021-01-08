package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.ConsoleOutService;
import com.epam.izh.rd.online.service.WithMatchesGameService;

public class RunTwentyMatchesGameOnConsole {

    public static void main(String[] args) {
        WithMatchesGameService twentyMatchesGameService = new WithMatchesGameService(20, 3);
        ConsoleOutService consoleOutService = new ConsoleOutService();

        while (!twentyMatchesGameService.isGameOver()) {
            consoleOutService.showChangesInGame(twentyMatchesGameService.getGameFromRepository()
                    , twentyMatchesGameService.getStatesOfGame());
            twentyMatchesGameService.changeToNextStateInGame();
        }
        consoleOutService.showChangesInGame(twentyMatchesGameService.getGameFromRepository()
                , twentyMatchesGameService.getStatesOfGame());
        twentyMatchesGameService.changeToNextStateInGame();
        consoleOutService.showChangesInGame(twentyMatchesGameService.getGameFromRepository()
                , twentyMatchesGameService.getStatesOfGame());
    }
}