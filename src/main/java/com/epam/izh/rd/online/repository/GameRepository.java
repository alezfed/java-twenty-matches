package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.WithMatchesGame;

public interface GameRepository {

    WithMatchesGame getWithMatchesGame();

    boolean isGameOver();

    boolean isComputerMoves();

    void calculateNumberMatchesForComputer();

    boolean allowNumberMatchesChosenGamer();

    void changeGamers();
}
