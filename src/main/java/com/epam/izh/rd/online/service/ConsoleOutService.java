package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.StatesOfGame;
import com.epam.izh.rd.online.entity.WithMatchesGame;

import java.util.Scanner;

public class ConsoleOutService implements OutService {
    private final Scanner scanner;

    public ConsoleOutService() {
        this.scanner = new Scanner(System.in);
    }

    public void showChangesInGame(WithMatchesGame game, StatesOfGame statesOfGame) {
        switch (statesOfGame) {
            case START:
                System.out.printf(statesOfGame.message, game.getMAX_NUMBER_MATCHES_ON_TABLE()
                        , game.getMAX_NUMBER_MATCHES_PER_MOVE());
                break;
            case BEFORE_MOVE:
                System.out.printf(statesOfGame.message, game.getCurrentNumberOfMatchesOnTable());
                break;
            case COMPUTER_MOVE:
                System.out.printf(statesOfGame.message, game.getCurrentNumberOfMatchesPerMove());
                break;
            case GAMER_MOVE:
                System.out.print(statesOfGame.message);
                giveReactionGamer(game);
                break;
            case ERROR:
            case COMPUTER_WIN:
            case GAMER_WIN:
                System.out.print(statesOfGame.message);
                break;
            default:
                System.out.print("Ошибка. Неизвестное состояние игры.\n");
                break;
        }
    }

    public void giveReactionGamer(WithMatchesGame game) {
        if (!scanner.hasNextInt()) {
            return;
        }
        game.setCurrentNumberOfMatchesPerMove(scanner.nextInt());
    }
}