package lesson5;

public class HomeWorkApp {
    static int[][] horseMap = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    static int moveX[] = {1, 2, 2, 1, -1, -2, -2, -1};     //Возможный сдвиг коня по горизонтали
    static int moveY[] = {2, 1, -1, -2, -2, -1, 1, 2};     //Возможный сдвиг коня по вертикали
    static int SIZE = 5;                                     //Размер поля 5*5
    static int moveCount = 0;                               //Количество шагов перебора вариантов

    public static int pow(int value, int powValue) {
        if (powValue == 1) {            // Условие выхода из рекурсии
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }

    public static void Horse(int[][] map, int moveNum, int i0, int j0){     //i0,j0  - начальная позиция коня
        moveCount++;
        int iNew = 0;
        int jNew = 0;
        map[i0][j0] = moveNum++;
        for (int i = 0; i < 8; i++) {
            iNew = i0 + moveY[i];
            jNew = j0 + moveX[i];

            if(moveNum > SIZE * SIZE){      //Условие выхода из рекурсии
                Print(SIZE);
                System.out.println("Количество шагов перебора = " + moveCount);
                System.exit(0);
            }
             if(iNew < 0 || iNew > SIZE - 1 || jNew < 0 || jNew > SIZE - 1 || horseMap[iNew][jNew] != 0){
                 continue;          // Уход на поиск нового хода
             }
             Horse(map, moveNum, iNew, jNew);
             map[iNew][jNew] = 0;               //Точка возврата
        }


    }

    public static void Print(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d", horseMap[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(3,3));   // Это было на первом занятии
        Horse(horseMap, 1, 0, 0);
    }
}
