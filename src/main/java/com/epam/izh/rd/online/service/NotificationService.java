package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.GameProperties;
import com.epam.izh.rd.online.entity.GameState;

public class NotificationService {
    public void showChangesInGame(GameProperties properties, GameState state, String userName) {
        switch (state.getId()) {
            case START:
                System.out.printf(state.getId().message, properties.getSTART_MATCHES_ON_TABLE()
                        , properties.getMAX_MATCHES_PER_MOVE());
                break;
            case BEFORE_MOVE:
                System.out.printf(state.getId().message, state.getMatchesOnTable());
                break;
            case COMPUTER_MOVE:
                System.out.printf(state.getId().message, state.getMatchesPerMove());
                break;
            case GAMER_MOVE:
            case GAME_OVER:
                System.out.printf(state.getId().message, userName);
                break;
            case ERROR:
                System.out.printf(state.getId().message, "спичек");
                break;
            default:
                System.out.print("Ошибка в классе NotificationService. Неизвестное состояние игры.\n");
                break;
        }
    }
}