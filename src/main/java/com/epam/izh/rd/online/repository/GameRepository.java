package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.GameState;

public interface GameRepository {

    int count();

    int save(GameState state);

    GameState read(int id);
}