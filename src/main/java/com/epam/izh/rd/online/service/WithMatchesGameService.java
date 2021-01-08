package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.StatesOfGame;
import com.epam.izh.rd.online.entity.WithMatchesGame;
import com.epam.izh.rd.online.repository.GameRepository;
import com.epam.izh.rd.online.repository.WithMatchesGameRepository;

public class WithMatchesGameService implements GameService {
    private GameRepository gameRepository;
    private StatesOfGame previousStatesOfGame;

    public WithMatchesGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        previousStatesOfGame = StatesOfGame.START;
    }

    public WithMatchesGameService(int maxNumberMatchesOnTable, int maxNumberMatchesPerMove) {
        this.gameRepository = new WithMatchesGameRepository(maxNumberMatchesOnTable, maxNumberMatchesPerMove);
        previousStatesOfGame = StatesOfGame.START;
    }

    public StatesOfGame getStatesOfGame() {
        return previousStatesOfGame;
    }

    public boolean isGameOver() {
        return gameRepository.isGameOver();
    }

    public WithMatchesGame getGameFromRepository() {
        return gameRepository.getWithMatchesGame();
    }

    public void changeToNextStateInGame() {
        checkWinner();
        switch (previousStatesOfGame) {
            case START:
            case COMPUTER_MOVE:
            case ERROR:
                previousStatesOfGame = StatesOfGame.BEFORE_MOVE;
                break;
            case BEFORE_MOVE:
                if (gameRepository.isComputerMoves()) {
                    makesMoveComputer();
                } else {
                    previousStatesOfGame = StatesOfGame.GAMER_MOVE;
                }
                break;
            case GAMER_MOVE:
                makesMoveGamer();
                break;
        }
    }

    private void makesMoveComputer() {
        gameRepository.calculateNumberMatchesForComputer();
        gameRepository.changeGamers();
        previousStatesOfGame = StatesOfGame.COMPUTER_MOVE;
    }

    private void makesMoveGamer() {
        if (gameRepository.allowNumberMatchesChosenGamer()) {
            WithMatchesGame withMatchesGame = gameRepository.getWithMatchesGame();
            withMatchesGame.setCurrentNumberOfMatchesOnTable(withMatchesGame.getCurrentNumberOfMatchesOnTable()
                    - withMatchesGame.getCurrentNumberOfMatchesPerMove());
            gameRepository.changeGamers();
            previousStatesOfGame = StatesOfGame.BEFORE_MOVE;
        } else {
            previousStatesOfGame = StatesOfGame.ERROR;
        }
    }

    private void checkWinner() {
        if (gameRepository.isGameOver() && gameRepository.isComputerMoves()) {
            previousStatesOfGame = StatesOfGame.GAMER_WIN;
        }
        if (gameRepository.isGameOver() && !gameRepository.isComputerMoves()) {
            previousStatesOfGame = StatesOfGame.COMPUTER_WIN;
        }
    }
}

