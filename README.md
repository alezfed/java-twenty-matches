Сущности:

enum PossibleGameStates - возможные состояния игры;
{START, BEFORE_MOVE, COMPUTER_MOVE, USER_MOVE, ERROR, COMPUTER_WIN, USER_WIN}.

class GameProperties - свойства игры;
int START_MATCHES_ON_TABLE - количество спичек на столе в начале игры;
int MAX_MATCHES_PER_MOVE - максимальное количество спичек, которое можно
взять за один ход.

class User - сущность игрок;
int id - идентификатор игрока;
String name - имя игрока;
boolean IAmComputer - признак "игрок-компьютер";

class GameState - текущее состояние;
PossibleGameStates id - идентификатор состояния;
int matchesPerMove - количество спичек, в текущем ходу;
int matchesOnTable - количество спичек на столе в текущий момент;
int userID - идентификатор игрока, который сейчас ходит.


Репозитории:

class RamGameRepository - хранит состояния и процесс игры в оперативной памяти;
GameState[] gameStates - массив состояний игры;
int save(GameState state) - метод принимает на входе и сохраняет в памяти
состояние игры, возвращает номер-идентификатор записи;
GameState read(int id) - метод принимает идентификатор записи, возвращает
ранее записанное состояние игры;
int count() - метод, возвращает количество записей в массиве состояний.

class RamUserRepository - хранит игроков в оперативной памяти;
User[] gameUsers - массив игроков;
int save(User user) - метод принимает на входе и сохраняет в памяти
игрока, возвращает номер-идентификатор записи;
User read(int id) - метод принимает идентификатор игрока, возвращает
ранее записанного игрока;
int count() - метод, возвращает количество игроков в массиве игроков;
String findNameById(int id) - метод возвращает имя игрока с заданным id.

Дополнительно могут быть описаны методы update, delete для созданных репозиториев,
но в данном случае они не нужны. Потенциально возможные хранилища:
FileGameRepository - хранит процесс игры в файле;
RdbsGameRepository - хранит процесс игры в БД;
FileUserRepository - хранит игроков в файле;
RdbsUserRepository - хранит игроков в БД.


Сервисы:

class UserRequestServicе - сервис обработки запросов игрока;
int getIntNumber() - метод запрашивает целое число, введенное пользователем;
boolean allowNumberMatches(int matchesPerMove, int matchesOnTable, int maxMatchesPerMove) -
метод проверяет допустимое ли число спичек хочет взять игрок;
int int getMatches(GameProperties gameProperties, GameState gameState) - метод
запрашивает количество спичек, выдернутое пользователем;
int int getGameParameter(String paramDescription, int minValue, int maxValue) - метод
запрашивает числовой параметр игры в допустимом диапазоне с соответствующими
текстовыми пояснениями;
String getUserName(int userID) - метод считывает имя игрока с заданным id.

class ComputerLogicServicе - сервис обработки ходов компьютера;
int getMatches(GameProperties gameProperties, GameState gameState) - метод
запрашивает количество спичек, выдернутое компьютером (не всегда правильно
работает для 3х и более игроков).

class NotificationService - сервис уведомления пользователя о ходе игры;
void showChangesInGame(GameProperties properties, GameState state, String userName) -
метод отображает новое состояние игры в консоль.

class ProcessGameService - сервис логики игры;
void createGameProperties() - метод создает основные свойства игры;
void createGameUsers() - метод создает игроков;
void createStartGameState() - метод создает начальное (нулевое) состояние игры;
void toNextGameState() - метод производит переход игры в следующее состояние;
boolean isGameOver() - метод проверяет, окончилась ли игра;
void startGame() - метод содержит реализацию игры.