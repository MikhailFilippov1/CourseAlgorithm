package lesson1;

public class lesson1 {
    public static void main(String[] args) {

        System.out.println(pow(2,8));

        System.out.println(powRecursion(2, 8));

        System.out.println(myPow(2, 11));

        System.out.println(mySum(100));
    }

    public static int pow(int value, int powValue) {    //Задание 1: возведение в степень из библиотеки Math
        return (int) Math.pow(value, powValue);
    }

    public static int powRecursion(int value, int powValue) {   //Задание 1: возведение в степень методом рекурсии
        if (powValue == 1) {
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }

    public static int myPow(int value, int powValue){       //Задание 2: возведение в степень (с использованием свойства чётности степени)
        int count = 1;
        if(powValue < 0) return 1;
        while (powValue >= 1){
            if(powValue%2 == 0){
                powValue = powValue/2;
                value*=value;
            } else {
                powValue--;
                count*=value;
            }
        }
        return count;
    }

    public static int mySum(int value){                  //Задание 3: получение суммы всех целых чисел от 1 до value
        return (value * (value + 1)) / 2;
    }
}
