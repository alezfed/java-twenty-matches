package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.GameState;
import com.epam.izh.rd.online.exceptions.NotFoundException;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;

public class RamGameRepository implements GameRepository {
    private GameState[] gameStates;

    public RamGameRepository() {
        gameStates = new GameState[0];
    }

    @Override
    public int count() {
        return gameStates.length;
    }

    @Override
    public int save(@NotNull GameState state) {
        int countStates = count();
        gameStates = Arrays.copyOf(gameStates, countStates + 1);
        gameStates[countStates] = new GameState(state);
        return ++countStates;
    }

    @Override
    public GameState read(int id) {
        GameState gameState;
        try {
            gameState = new GameState(gameStates[id]);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Состояния игры с идентификатором id = " + id + " не существует.");
        }
        return gameState;
    }
}