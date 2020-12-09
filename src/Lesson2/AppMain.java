package Lesson2;
//Расулов Фархад Сохельевич

public class AppMain {
    public static void main(String[] args) {
        initArray();
        fillArray();
        changeElementArray();
        fillDiagonal(5);
        findMinMax();

        //Задание 6
        int[] data = {0, 2, 1, 3, 1, 5};
        System.out.println("Равенство левой и правой части массива: " + (findArrayBalance(data) ? "Найдено" : "Не найдено"));

        //Задание 7
        float[] newData = {1f, 5f, 2.5f, -8f};
        if (moveArray(newData, 9))  //отрицательное -двигает вправо, положительное -влево
            printSingleArray(newData);
    }

    private static void initArray() //Задание 1
    {
        System.out.println("Задание 1");
        int[] firstArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < firstArray.length; i++) {

            System.out.print("Before " + firstArray[i]);

            if (firstArray[i] == 1)
                firstArray[i] = 0;
            else
                firstArray[i] = 1;

            System.out.print(" After:" + firstArray[i] + "\n");
        }
    }

    private static void fillArray() //Задание 2
    {
        System.out.println("\nЗадание 2");
        int[] fixArray = new int[8]; //Инициализируем массив длиной 8 ячеек

        for (int i = 0, j = 0; i < fixArray.length; i++, j = j + 3) {
            fixArray[i] = j;
            System.out.print(fixArray[i] + " ");
        }
    }

    private static void changeElementArray() //Задание 3
    {
        System.out.println("\n\nЗадание 3");
        int[] firstArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        //умножим на 2 значения меньше 6
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] < 6)
                firstArray[i] *= 2;
        }
        //Выведем в консоль полученные значения
        for (int elemArray : firstArray) {
            System.out.print(elemArray + " ");
        }
    }

    private static void fillDiagonal(int pSizeArray) //Задание 4
    {
        System.out.println("\n\nЗадание 4");
        int[][] multArray = new int[pSizeArray][pSizeArray];
        int i = 0;
        int j = 0;
        int k = pSizeArray - 1; //т.к элементы начинаются с 0

        for (i = 0; i < multArray.length; i++, k--) { //идем по строчкам. k уменьшаем на 1 для обратной диагонали
            for (j = 0; j < multArray[i].length; j++) //идем по колонкам
            {
                if (i == j) {
                    multArray[i][j] = 1;
                } else
                    multArray[i][j] = 0;
            }
            multArray[i][k] = 1; //Заполняем обратную диагональ
        }

        //Выведем результат на консоль
        for (i = 0; i < multArray.length; i++) {
            for (j = 0; j < multArray[i].length; j++) {
                System.out.printf("%2d ", multArray[i][j]);
            }
            System.out.println();
        }
    }

    private static void findMinMax() {
        System.out.println("\n\nЗадание 5");
        float[] firstArray = {3f, 1.3f, 0.4f, 5f, 9f, 0.3f};
        //инициализируем переменные первым элементом массива
        float minVal = firstArray[0];
        float maxVal = firstArray[0];

        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] <= minVal)
                minVal = firstArray[i];

            if (firstArray[i] >= maxVal)
                maxVal = firstArray[i];
        }

        System.out.printf("Максимальное значение в массиве = %f, минимальное значение = %f", maxVal, minVal);
    }

    private static boolean findArrayBalance(int[] pArray) {
        System.out.println("\n\nЗадание 6");
        if (pArray.length <= 1) {
            System.out.println("Задан не корректный массив. Расчет прерван");
            return false;
        }
        int pos = 1; //начальная граница между нулевым и первым элементом массива
        int leftVal = pArray[0];
        int rightVal = 0;

        while (pos <= pArray.length) {
            for (int i = pos; i < pArray.length; i++) //посчитаем сумму элементов начиная с границы
                rightVal += pArray[i];

            if (leftVal == rightVal) {
                System.out.println("Значение левой и правой части равно: " + leftVal);
                System.out.printf("Граница равенства за %d-ым элементом\n", pos);
                return true;
            } else {
                if (leftVal > rightVal) //Если сумма правых больше чем левая, то нет варианта
                    return false;
                else {
                    leftVal += pArray[pos]; //Скорректируем левую часть от текущей границы
                    rightVal = 0; //Обнулим правую сторону
                }
            }
            pos++; //переходим к следующей границе
        }
        //Если пришли сюда то не нашли границы
        return false;
    }

    private static void printSingleArray(float[] pArray) {
        for (float elemArray : pArray) {
            System.out.print(elemArray + " ");
        }
    }

    private static boolean moveArray(float[] pArray, int pCount) {
        System.out.println("\n\nЗадание 7");
        if (pArray.length <= 0) {
            System.out.println("Задан не корректный массив. Алгоритм прерван");
            return false;
        }
        //Посчитаем кратность сдвига длине массива
        //Посчитаем количество итераций
        int maxIteration = Math.abs(pCount - (pCount / pArray.length * pArray.length)); //Возьмем модуль от количества сдвига. Используем деление и тип Int для получения целого числа

        if (maxIteration == 0 || maxIteration == pArray.length) //Нет смысла продолжать, элементы останутся на тех же местах
            return true;

        int direct = (pCount >= 0 ? 1 : -1); //1 влево. -1 вправо

        //Цикл итераций сдвига
        for (int j = 1; j <= maxIteration; j++) {
            int pos = (direct == 1 ? 1 : pArray.length - 1); //Если влево - то со второго элемента начем, если вправо то с последнего элемента
            float saveVal = (direct == 1 ? pArray[0] : pArray[pArray.length - 1]); //Если идем влево то запомним первый элемент иначе последний

            while (true) //бесконечный цикл для перемещения элементов
            {
                if (direct == 1) //если направление влево
                {
                    if (pos > pArray.length - 1) {  //достигли конца
                        pArray[pArray.length - 1] = saveVal; //восстановим последний элемент значением первого
                        break;
                    }
                    pArray[pos - 1] = pArray[pos];
                } else //при движении вправо
                {
                    if (pos < 1) {
                        pArray[0] = saveVal; //восстановим первый элемент значением последнего
                        break;
                    }
                    pArray[pos] = pArray[pos - 1];
                }
                pos = pos + direct;
            }
        }
        return true;
    }
}
