package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.StatesOfGame;
import com.epam.izh.rd.online.entity.WithMatchesGame;

public interface OutService {

    void showChangesInGame(WithMatchesGame game, StatesOfGame statesOfGame);

    void giveReactionGamer(WithMatchesGame game);
}
