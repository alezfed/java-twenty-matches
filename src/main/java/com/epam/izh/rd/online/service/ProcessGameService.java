package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.GameProperties;
import com.epam.izh.rd.online.entity.GameState;
import com.epam.izh.rd.online.entity.PossibleGameStates;
import com.epam.izh.rd.online.entity.User;
import com.epam.izh.rd.online.repository.RamGameRepository;
import com.epam.izh.rd.online.repository.RamUserRepository;

public class ProcessGameService {
    private RamGameRepository gameRepository;
    private RamUserRepository userRepository;
    private GameProperties gameProperties;
    private UserRequestService userRequestServicе;
    private ComputerLogicService computerLogicService;
    private NotificationService notificationService;

    public ProcessGameService() {
        gameRepository = new RamGameRepository();
        userRepository = new RamUserRepository();
        userRequestServicе = new UserRequestService();
        computerLogicService = new ComputerLogicService();
        notificationService = new NotificationService();
    }

    public void createGameProperties() {
        System.out.print(PossibleGameStates.HELLO.message);
        int startNumberMatches = userRequestServicе.getGameParameter
                ("спичек на столе в начальный момент", 10);
        int maxMatchesPerMove = userRequestServicе.getGameParameter
                ("максимально взятых за ход спичек", 2, startNumberMatches - 1);
        gameProperties = new GameProperties(startNumberMatches, maxMatchesPerMove);
    }

    public void createGameUsers() {
        int userCount = userRequestServicе.getGameParameter("игроков", 2);
        for (int i = 0; i < userCount; i++) {
            User newUser = new User(i, userRequestServicе.getUserName(i + 1));
            userRepository.save(newUser);
        }
    }

    public void createStartGameState() {
        GameState startState = new GameState(PossibleGameStates.START, gameProperties.getSTART_MATCHES_ON_TABLE()
                , gameProperties.getMAX_MATCHES_PER_MOVE(), 0);
        gameRepository.save(startState);
        notificationService.showChangesInGame(gameProperties, startState
                , userRepository.findNameById(startState.getUserID()));
    }

    public void toNextGameState() {
        GameState lastGameState = gameRepository.read(gameRepository.count() - 1);
        switch (lastGameState.getId()) {
            case HELLO:
            case GAME_OVER:
            case INPUT_PARAMS:
            case INPUT_USER:
                break;
            case START:
            case ERROR:
                lastGameState.setId(PossibleGameStates.BEFORE_MOVE);
                break;
            case COMPUTER_MOVE:
                lastGameState.setId(PossibleGameStates.BEFORE_MOVE);
                if (isGameOver()) {
                    lastGameState.setId(PossibleGameStates.GAME_OVER);
                }
                lastGameState.setUserID(userRepository.nextId(lastGameState.getUserID()));
                break;
            case BEFORE_MOVE:
                if (userRepository.read(lastGameState.getUserID()).isIAmComputer()) {
                    int matchesChosen = computerLogicService.getMatches(gameProperties, lastGameState);
                    lastGameState.setMatchesPerMove(matchesChosen);
                    lastGameState.setMatchesOnTable(lastGameState.getMatchesOnTable() - matchesChosen);
                    lastGameState.setId(PossibleGameStates.COMPUTER_MOVE);
                } else {
                    lastGameState.setId(PossibleGameStates.GAMER_MOVE);
                }
                break;
            case GAMER_MOVE:
                int matchesChosen = userRequestServicе.getMatches(gameProperties, lastGameState);
                if (matchesChosen <= 0) {
                    lastGameState.setId(PossibleGameStates.ERROR);
                } else {
                    lastGameState.setMatchesPerMove(matchesChosen);
                    lastGameState.setMatchesOnTable(lastGameState.getMatchesOnTable() - matchesChosen);
                    lastGameState.setUserID(userRepository.nextId(lastGameState.getUserID()));
                    lastGameState.setId(PossibleGameStates.BEFORE_MOVE);
                    if (isGameOver()) {
                        lastGameState.setId(PossibleGameStates.GAME_OVER);
                    }
                }
                break;
            default:
                System.out.print("Ошибка в классе ProcessGameService. Неизвестное состояние игры.\n");
                break;
        }
        gameRepository.save(lastGameState);
        notificationService.showChangesInGame(gameProperties, lastGameState
                , userRepository.findNameById(lastGameState.getUserID()));
    }

    public boolean isGameOver() {
        return (gameRepository.read(gameRepository.count() - 1).getMatchesOnTable() == 1);
    }

    public void startGame() {
        createGameProperties();
        createGameUsers();
        createStartGameState();
        while (!isGameOver()) {
            toNextGameState();
        }
        toNextGameState();
    }
}