package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.StatesOfGame;
import com.epam.izh.rd.online.entity.WithMatchesGame;

public interface GameService {

    StatesOfGame getStatesOfGame();

    boolean isGameOver();

    WithMatchesGame getGameFromRepository();

    void changeToNextStateInGame();
}

