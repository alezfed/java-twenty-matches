package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.WithMatchesGame;

public class WithMatchesGameRepository implements GameRepository {

    private WithMatchesGame withMatchesGame;

    public WithMatchesGameRepository(WithMatchesGame withMatchesGame) {
        this.withMatchesGame = withMatchesGame;
    }

    public WithMatchesGameRepository(int maxNumberMatchesOnTable, int maxNumberMatchesPerMove) {
        withMatchesGame = new WithMatchesGame(maxNumberMatchesOnTable, maxNumberMatchesPerMove);
    }

    public WithMatchesGame getWithMatchesGame() {
        return withMatchesGame;
    }

    public boolean isGameOver() {
        return (withMatchesGame.getCurrentNumberOfMatchesOnTable() == 1);
    }

    public boolean isComputerMoves() {
        return withMatchesGame.isComputerMoves();
    }

    public void calculateNumberMatchesForComputer() {
        int numberMatchesChosen = (withMatchesGame.getCurrentNumberOfMatchesOnTable() - 1) %
                (withMatchesGame.getMAX_NUMBER_MATCHES_PER_MOVE() + 1);
        if (numberMatchesChosen == 0) {
            numberMatchesChosen = 1;
        }
        withMatchesGame.setCurrentNumberOfMatchesPerMove(numberMatchesChosen);
        withMatchesGame.setCurrentNumberOfMatchesOnTable(withMatchesGame.getCurrentNumberOfMatchesOnTable()
                - numberMatchesChosen);
    }

    public boolean allowNumberMatchesChosenGamer() {
        return ((withMatchesGame.getCurrentNumberOfMatchesPerMove() >= 1)
                && (withMatchesGame.getCurrentNumberOfMatchesPerMove() <= Math.min(withMatchesGame
                .getCurrentNumberOfMatchesOnTable() - 1, withMatchesGame.getMAX_NUMBER_MATCHES_PER_MOVE())));
    }

    public void changeGamers() {
        withMatchesGame.setComputerMoves(!withMatchesGame.isComputerMoves());
    }
}
