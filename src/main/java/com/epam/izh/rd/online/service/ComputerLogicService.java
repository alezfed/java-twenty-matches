package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.GameProperties;
import com.epam.izh.rd.online.entity.GameState;

public class ComputerLogicService {
    public int getMatches(GameProperties gameProperties, GameState gameState) {
        int matchesChosen = (gameState.getMatchesOnTable() - 1) % (gameProperties.getMAX_MATCHES_PER_MOVE() + 1);
        if (matchesChosen == 0) {
            matchesChosen = 1;
        }
        return matchesChosen;
    }
}