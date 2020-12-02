package Lesson3;
//Расулов Фархад Сохельевич


import java.util.Random;
import java.util.Scanner;

public class AppMain {

    static Scanner input = new Scanner(System.in);
    //Массив слов
    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};
    static int secretVal = 0;                // Индекс загаданного слова
    static char[] answerWord = new char[15]; // Замаскированный результат
    static boolean easyMode = false;         // Режим игры. Если true, то сохраняем предыдущий результат проверки слова и отображаем его

    public static void main(String[] args) {
        boolean doRepeat = false;
        String inputWord;
        //Начальное меню
        do {
            makeSecretWord();                // Загадаем слово
            printGameMode();                 // Выбор режим игры
            doRepeat = chooseActionMode();   // Ожидаем ввод с консоли и ждем начала игры
        } while (doRepeat);

        System.out.println(words[secretVal]); //Отладка

        while (true) {                       // Процесс игры
            if (!easyMode)                   // Заполним результат проверки решеткой, если режим игры выбран не легкий
                initAnswerWord('#');

            printStartGame();
            inputWord = inputWord();                     // Ожидаем введенное слово. Контроль корректности
            if (!checkSecretWord(inputWord)) {
                fillMaskWord(inputWord);                 // Выставим совпавшие буквы
                printMaskAnswer();                       // Выведем результат
            } else {
                printFinish();
                System.exit(0);                    // Если угадали, то выходим
            }
        }
    }

    //Загадываем слово
    private static void makeSecretWord() {
        Random randVal = new Random();
        secretVal = randVal.nextInt(words.length - 1); // Ограничим до количества слов в массиве
        initAnswerWord('#');                            // Инициализируем ответный массив символом #
    }

    //Инициализация результата сообщения маской
    private static void initAnswerWord(char mask) {
        for (int i = 0; i < answerWord.length; i++) {
            answerWord[i] = mask;
        }
    }

    //Выбор режима игры
    private static void printGameMode() {
        System.out.print("\u001B[33m");
        System.out.println("Привет! Начнем игру - Угадай слово");
        System.out.println("Выберите режим игры: (1-4)");
        System.out.print("\u001B[34m");
        System.out.println("1) Легкий режим (подсказки угаданных букв)");
        System.out.print("\u001B[31m");
        System.out.println("2) Сложный режим");
        System.out.print("\u001B[34m");
        System.out.println("3) Загадать новое слово");
        System.out.print("\u001B[36m");
        System.out.println("4) Выход из игры");
    }

    private static boolean chooseActionMode() {
        boolean doRepeat;
        do {
            doRepeat = false;
            switch (input.next()) {
                case "1":
                    easyMode = true;
                    return false;
                case "2"4:
                    easyMode = false;
                    return false;
                case "3":
                    makeSecretWord();
                    break;
                case "4":
                    doExit();
                    break;
                default:
                    System.out.println("Неверное значение. Повторите ввод");
                    doRepeat = true;
            }
        } while (doRepeat);
        return true;
    }

    // Завершить выполнение программы
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
                    printGameMode(); //Отобразим меню
                    break;
                default:
                    System.out.println("Неверное значение. Повторите ввод");
                    doRepeat = true;
            }
        } while (doRepeat);
    }

    private static void printStartGame() {
        System.out.println();
        System.out.println("Введите загаданное слово");

    }

    private static String inputWord() {
        String word = "";
        boolean doRepeat;
        do {
            word = input.next();
            doRepeat = false;

            if (word.isEmpty()) {
                doRepeat = true;
                System.out.println("Неверное значение. Попробуйте еще раз");
            }
            if (word.length() > answerWord.length) {
                doRepeat = true;
                System.out.println("Слишком длинное слово. Попробуйте еще раз");
            }
        } while (doRepeat);

        return word.toLowerCase(); //вернем слово в нижнем регистре
    }

    private static boolean checkSecretWord(String word) {
        if (words[secretVal].equals(word)) //Если слово совпало полностью
            return true;

        return false;
    }

    private static void printFinish() {
        System.out.println("Ура! Слово угадано верно!");
    }

    private static void fillMaskWord(String word) {
        //Пробежимся по каждому символу слова и сверим этот же символ в введеном слове. Если совпало, то выставим, если нет, то оставим без изменений
        for (int i = 0; i < words[secretVal].length() - 1 & i < word.length()-1; i++) {
            answerWord[i] = (word.charAt(i) == words[secretVal].charAt(i)) ? word.charAt(i) : answerWord[i];
        }

    }

    private static void printMaskAnswer() {
        System.out.println("Угаданы следующие буквы в слове: ");
        for (char word : answerWord) {
            System.out.print(word);
        }
        System.out.println();
    }

}
