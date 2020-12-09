package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class AppMain {
    //вести счетчик заполнения строки/столбца/диагонали при ходе
    //считать результат сразу после хода. Не пользовать цикл

    static Scanner input = new Scanner(System.in);
    static int sizeMap = 3;                             // размер поля по-умолчанию
    static char[][] gameMap;                            // игровое поле х/у
    static char dotHuman = 'X';                         // отметка хода Игрока
    static char dotAI = 'O';                            // отметка хода Компьютера
    static final char DOT_EMPTY = '•';                  // отметка пустого поля
    static final int WIN_LINE_CNT = 3;                  // количество шагов вподряд для выигрыша
    static int roundCount = 3;                          // количество игровых раундов
    static int roundResult = 0;                         // Результат выигрыша игрока (0 -проиграл, 1-выиграл, 2-ничья)
    static int[] humanRoundWinResult;                   // результаты побед игрока в раундах (0 -проиграл, 1-выиграл, 2-ничья)
    static int moveCount = 0;                           // Счетчик ходов
    static int moveMax = 0;                             // Всего количество ходов
    static int lastRow = 0;
    static int lastCol = 0;
    static final char HEADER_FIRST_SYMB = '♦';
    static final String EMPTY_SYMB = " ";

    public static void main(String[] args) {
        boolean doRepeat = false;

        do {
            do {
                printSetupMenu();                           // Игровое меню
                doRepeat = inputSetupMenu();                // Ввод, пока не выбрали начало игры
            } while (doRepeat);
            initRoundResult();                              // Сбросим результаты раундов
            startGame();                                    // Начало игры
        } while (doRepeatGame());                           // Играем, пока пользователь не откажется от повтора
    }

    public static void printSetupMenu() {
        System.out.print("\u001B[34m");
        System.out.printf("Привет! Поиграем в Крестики Нолики %dх%d %n", sizeMap, sizeMap);
        System.out.print("\u001B[35m");
        System.out.println("Настройки игры");
        System.out.println("1) Начать игру");
        System.out.println("2) Установить размер игрового поля");
        System.out.println("3) Установить символ хода Игрока");
        System.out.println("4) Установить символ хода Компьютера");
        System.out.println("5) Установить количество раундов");
        System.out.println("6) Выход");
        System.out.print("\u001B[33m");
    }

    public static boolean inputSetupMenu() {
        boolean doRepeat;
        do {
            doRepeat = false;
            switch (input.next()) {
                case "1":
                    return false; //начнем игру
                case "2":
                    printNewSizeMap();
                    inputNewSizeMap();
                    break;
                case "3":
                    printSetPlayerSymbol(1);
                    dotHuman = inputPlayerSymbol();
                    break;
                case "4":
                    printSetPlayerSymbol(2);
                    dotAI = inputPlayerSymbol();
                    break;
                case "5":
                    printRound();
                    roundCount = inputRound();
                    break;
                case "6":
                    doExit();
                    break;
                default:
                    System.out.println("Неверное значение. Повторите ввод");
                    doRepeat = true;
            }
        } while (doRepeat);

        return true; //крутим меню дальше
    }

    private static void printRound() {
        System.out.printf("Введите количество раундов игры (%d):%n ", roundCount);
    }

    private static int inputRound() {
        int roundCnt;
        boolean doRepeat = false;
        do {
            roundCnt = inputIntValue();
            //Проверим, что оно больше нуля
            if (roundCnt < 1) {
                System.out.println("Должен быть установлен хотя бы один раунд");
                doRepeat = true;              //запросим повтороного ввода
                continue;                     // повторим итерацию цикла
            }
            System.out.printf("Количество раундов \"%d\" принято успешно %n", roundCnt);
        } while (doRepeat);
        return roundCnt;
    }

    private static void printSetPlayerSymbol(int player) {
        switch (player) {
            case 1:
                System.out.println("Введите символ Игрока: ");
                break;
            case 2:
                System.out.println("Введите символ Компьютера: ");
            default:
                System.out.println("Неверный идентификатор игрока");
        }
    }

    private static char inputPlayerSymbol() {
        boolean doRepeat = false;
        char inputSymbol = '0';
        do {
            try {
                inputSymbol = input.next().charAt(0);
                if (inputSymbol == DOT_EMPTY) {
                    System.out.println("Данный символ зарезервирован");
                    doRepeat = true;              //запросим повтороного ввода
                    continue;                     // повторим итерацию цикла
                }
                System.out.printf("Символ \"%s\" принят %n", inputSymbol);
            } catch (Exception ex) {
                System.out.println("Неверное значение");
                input.next();                     //очистим мусор
            }
        } while (doRepeat);
        return inputSymbol;
    }

    private static void doExit() {
        boolean doRepeat;
        do {
            System.out.println("Вы действительно хотите выйти? (y/n)");
            doRepeat = false;
            switch (input.next()) {
                case "y":
                    System.exit(0);
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Неверное значение. Повторите ввод");
                    doRepeat = true;
            }
        } while (doRepeat);
    }

    private static boolean doRepeatGame() {
        boolean doRepeat;
        do {
            System.out.print("\u001B[35m");
            System.out.println("Хотите сыграть еще раз? (y/n)");
            doRepeat = false;
            switch (input.next()) {
                case "y":
                    break;
                case "n":
                    return false;
                default:
                    System.out.println("Неверное значение. Повторите ввод");
                    doRepeat = true;
            }
        } while (doRepeat);
        return true;
    }

    public static void printNewSizeMap() {
        System.out.println("Введите размер игрового поля (минимальное - 3): ");
        System.out.print("\u001B[33m");
    }

    public static void inputNewSizeMap() {
        boolean doRepeat = false;
        do {
            sizeMap = inputIntValue();
            //Проверим, что оно не меньше 3х3
            if (sizeMap < 3) {
                System.out.println("Размер поля не может быть меньше 3");
                doRepeat = true;              //запросим повтороного ввода
                continue;                     // повторим итерацию цикла
            }
            System.out.printf("Размер поля %d х %d установлен успешно %n", sizeMap, sizeMap);
        } while (doRepeat);
    }

    private static int inputIntValue() {
        boolean doRepeat = false;
        int newVal = 0;
        do {
            try {
                doRepeat = !input.hasNextInt();   //Ждем число. Если мусор - заставим повторить ввод
                newVal = input.nextInt();
            } catch (Exception ex) {
                System.out.println("Неверное значение. Введите целое число");
                input.next();                     //очистим мусор
            }
        } while (doRepeat);
        return newVal;
    }

    public static void startGame() {
        int playCnt = 0;                                    //Счетчик раундов игры
        do {
            System.out.printf("%nНачало %d раунда %n", playCnt + 1);
            initGame();                                     // Инициализация игрового процесса
            printGameMap();                                 // Выводим игровое поле
            playGame();
            humanRoundWinResult[playCnt] = getRoundResult();// Записываем результат игры Пользователя
            roundResult = 0;                                // Сбросим флаг результата по текущему раунду
            playCnt++;
        } while (playCnt < roundCount);
        printGameResult();                                  // Итоговый результат по всем раундам
    }

    public static void initGame() {
        initMap();
        moveCount = 0;                                      //Сбросим счетчик ходов
        moveMax = sizeMap * sizeMap;                        //Посчитаем количество доступных ходов на игровом поле
    }

    public static void initMap() {
        gameMap = new char[sizeMap][sizeMap];
        //заполним игровое поле пустышками
        for (int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                gameMap[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void initRoundResult() {                  // Сброс результатов всех раундов
        humanRoundWinResult = new int[roundCount];
        for (int i = 0; i < humanRoundWinResult.length; i++) {
            humanRoundWinResult[i] = 0;
        }
    }

    private static int getRoundResult() {
        if (roundResult == 0) {
            System.out.println("В раунде победил Компьютер");
        } else if (roundResult == 1) {
            System.out.println("В раунде победил Пользователь");
        } else
            System.out.println("В раунде ничья");

        return roundResult;
    }

    private static void printGameResult() {             // Выведем результаты раундов
        System.out.print("\u001B[34m");
        System.out.printf("По результатам %d раундов %s %n", roundCount, getGameResult());
    }

    private static String getGameResult() {
        int countWin = 0;                               //количество выигрышей
        int standoff = 0;                               //количество ничьей
        for (int i = 0; i < humanRoundWinResult.length; i++) {
            if (humanRoundWinResult[i] == 1)            //Посчитаем выигрыши Игрока
                countWin++;
            if (humanRoundWinResult[i] == 2)            //Посчитаем ничью
                standoff++;
        }
        System.out.printf("Debug: win:%d std:%d rnd:%d", countWin, standoff, roundCount);
        if (countWin > (roundCount - countWin - standoff))
            return "победил Пользователь";

        if (countWin == (roundCount - countWin - standoff))
            return "ничья";

        return "победил Компьютер";
    }

    private static void printGameMap() {
        System.out.println();
        printHeaderMap();                               // Выводим заголовк поля
        printBodyMap();                                 // Выводим игровое поле
    }

    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_SYMB + EMPTY_SYMB);
        for (int i = 0; i < sizeMap; i++) {
            System.out.print(i + 1 + EMPTY_SYMB);
        }
        System.out.println();
    }

    private static void printBodyMap() {
        for (int i = 0; i < sizeMap; i++) {
            printMapNumbers(i);
            for (int j = 0; j < sizeMap; j++) {
                System.out.print(gameMap[i][j] + EMPTY_SYMB);
            }
            System.out.println();
        }
    }

    private static void printMapNumbers(int i) {
        System.out.print(i + 1 + EMPTY_SYMB);
    }

    private static void playGame() {                          //Процесс игры. Условия выхода: Закончились ходы или Закрыли линию
        boolean stopGame;
        do {
            humanMove();
            printGameMap();                                   // Выводим игровое поле
            //roundResult = (checkLineCross(dotHuman)) ? 1 : 2; // (0 -пользователь проиграл, 1-выиграл, 2-ничья)
            roundResult = (checkRoundWin(dotHuman)) ? 1 : 2;
            stopGame = roundResult == 1 ? true : false;
            if (!isEmptyMap() || stopGame)                    // играем пока есть доступные ходы
                return;

            aiMove();
            printGameMap();                                   // Выводим игровое поле
            //roundResult = (checkLineCross(dotAI)) ? 0 : 2;    // (0 -пользователь проиграл, 1-выиграл, 2-ничья)
            roundResult = (checkRoundWin(dotAI)) ? 0 : 2;    // (0 -пользователь проиграл, 1-выиграл, 2-ничья)

            stopGame = roundResult == 0 ? true : false;
            if (!isEmptyMap() || stopGame)                    // играем пока есть доступные ходы
                return;

        } while (true);

    }

    private static void aiMove() {
        Random rnd = new Random();
        int rowNumber;
        int colNumber;
        do {
            rowNumber = rnd.nextInt(sizeMap);
            colNumber = rnd.nextInt(sizeMap);
        } while (!isCellNotBusy(rowNumber, colNumber));

        makeStep(rowNumber, colNumber, dotAI);
    }

    private static void humanMove() {
        int rowNumber;
        int colNumber;
        System.out.println("\nХод человека! Введите номер строки и столбца");
        do {
            System.out.print("Строка = ");
            rowNumber = inputIntValue() - 1;

            System.out.print("Столбец = ");
            colNumber = inputIntValue() - 1;
        } while (!isHumanValid(rowNumber, colNumber));

        makeStep(rowNumber, colNumber, dotHuman);
    }

    private static void makeStep(int row, int col, char dot) {
        gameMap[row][col] = dot;
        lastRow = row;
        lastCol = col;
        moveCount++;
    }

    private static boolean checkWinHorizontal(char dot) {
        int cnt = WIN_LINE_CNT;
        for (int i = 0; i < sizeMap; i++) {
            boolean fnd = true;
            for (int j = 1; j < sizeMap && fnd; j++) {
                fnd = gameMap[i][j] == gameMap[i][0] && gameMap[i][j] == dot;
                if (fnd) {
                    cnt--;
                } else cnt = WIN_LINE_CNT;
                if (cnt <= 1) break; //Остановим цикл если достигли нужного кол-ва ходов в линию
            }
            if (fnd)
                return true;
        }
        return false;
    }

    private static boolean checkWinVertical(char dot) {
        int cnt = WIN_LINE_CNT;
        for (int i = 0; i < sizeMap; i++) {
            boolean fnd = true;
            for (int j = 1; j < sizeMap && fnd; j++) {
                fnd = gameMap[j][i] == gameMap[0][i] && gameMap[j][i] == dot;
                if (fnd) {
                    cnt--;
                } else cnt = WIN_LINE_CNT;
                if (cnt <= 1) break; //Остановим цикл если достигли нужного кол-ва ходов в линию
            }
            if (fnd)
                return true;
        }
        return false;
    }

    private static boolean checkWinDiagonals(char dot) {
        int cnt = WIN_LINE_CNT;
        boolean fnd = true;
        for (int i = 1; i < sizeMap && fnd; i++) {
            fnd = gameMap[i][i] == gameMap[0][0] && gameMap[i][i] == dot;
            if (fnd) {
                cnt--;
            } else cnt = WIN_LINE_CNT;
            if (cnt <= 1) break; //Остановим цикл если достигли нужного кол-ва ходов в линию
        }
        if (fnd)
            return true;
        fnd = true;
        for (int i = 1; i < sizeMap && fnd; i++)
            fnd = gameMap[sizeMap - i - 1][i] == gameMap[sizeMap - 1][0] && gameMap[sizeMap - i - 1][i] == dot;
        return fnd;
    }

    private static boolean checkRoundWin(char dot){
        return checkWinHorizontal(dot) || checkWinVertical(dot) || checkWinDiagonals(dot);
    }

    private static boolean checkLineCross(char dot) {
        //Поиск 3-х точек происходит относительно последнего шага.

        //Столбец относительно координаты последнего хода
        if (isNumberValid(lastRow - 1, lastCol) & isNumberValid(lastRow + 1, lastCol)) { //Проверяем, что точки лежат на игровом поле
            if (gameMap[lastRow - 1][lastCol] == dot & gameMap[lastRow + 1][lastCol] == dot){
                return true;
            }
        }

        if (isNumberValid(lastRow - 1, lastCol) & isNumberValid(lastRow - 2, lastCol)) {
            if (gameMap[lastRow - 1][lastCol] == dot & gameMap[lastRow - 2][lastCol] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow + 1, lastCol) & isNumberValid(lastRow + 2, lastCol)) {
            if (gameMap[lastRow + 1][lastCol] == dot & gameMap[lastRow + 2][lastCol] == dot) {
                return true;
            }
        }

        //Строка относительно координаты последнего хода
        if (isNumberValid(lastRow, lastCol - 1) & isNumberValid(lastRow, lastCol + 1)) {
            if (gameMap[lastRow][lastCol - 1] == dot & gameMap[lastRow][lastCol + 1] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow, lastCol - 1) & isNumberValid(lastRow, lastCol - 2)) {
            if (gameMap[lastRow][lastCol - 1] == dot & gameMap[lastRow][lastCol - 2] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow, lastCol + 1) & isNumberValid(lastRow, lastCol + 2)) {
            if (gameMap[lastRow][lastCol + 1] == dot & gameMap[lastRow][lastCol + 2] == dot) {
                return true;
            }
        }

        //Проверка прямой диагонали
        if (isNumberValid(lastRow - 1, lastCol - 1) & isNumberValid(lastRow + 1, lastCol + 1)) {
            if (gameMap[lastRow - 1][lastCol - 1] == dot & gameMap[lastRow + 1][lastCol + 1] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow - 1, lastCol - 1) & isNumberValid(lastRow - 2, lastCol - 2)) {
            if (gameMap[lastRow - 1][lastCol - 1] == dot & gameMap[lastRow - 2][lastCol - 2] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow + 1, lastCol + 1) & isNumberValid(lastRow + 2, lastCol + 2)) {
            if (gameMap[lastRow + 1][lastCol + 1] == dot & gameMap[lastRow + 2][lastCol + 2] == dot) {
                return true;
            }
        }

        //Проверка обратной диагонали
        if (isNumberValid(lastRow - 1, lastCol + 1) & isNumberValid(lastRow + 1, lastCol - 1)) {
            if (gameMap[lastRow - 1][lastCol + 1] == dot & gameMap[lastRow + 1][lastCol - 1] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow - 1, lastCol + 1) & isNumberValid(lastRow - 2, lastCol + 2)) {
            if (gameMap[lastRow - 1][lastCol + 1] == dot & gameMap[lastRow - 2][lastCol + 2] == dot) {
                return true;
            }
        }
        if (isNumberValid(lastRow + 1, lastCol - 1) & isNumberValid(lastRow + 2, lastCol - 2)) {
            if (gameMap[lastRow + 1][lastCol - 1] == dot & gameMap[lastRow + 2][lastCol - 2] == dot) {
                return true;
            }
        }

        return false;
    }

    private static boolean isEmptyMap() {
        return moveCount < moveMax;
    }

    private static boolean isHumanValid(int rowNumber, int colNumber) {
        boolean val = isNumberValid(rowNumber, colNumber);
        if (!val) {
            System.out.println("Вы вышли за рамки игрового поля. Проверьте значения");
            return false;
        }

        boolean busy = isCellNotBusy(rowNumber, colNumber);
        if (!busy)
            System.out.println("Нельзя сделать ход. Ячейка уже занята");
        return val && busy;
    }

    private static boolean isNumberValid(int row, int col) { //Проверим, не вышли ли мы за пределы поля
        if (row >= sizeMap | row < 0 | col >= sizeMap | col < 0) {
            return false;
        }
        return true;
    }

    private static boolean isCellNotBusy(int row, int col)  //Проверим, занята ли ячейка другим значением
    {
        if (gameMap[row][col] != DOT_EMPTY) {
            return false;
        }
        return true;
    }
}
