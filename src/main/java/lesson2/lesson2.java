package lesson2;

public class lesson2 {
    public static void main(String[] args) {
        MyArray arr = new MyArray(new int[]{1,2,8,3,4,5,6,7,1,9});

        arr.deleteAll(1);       // Задание 1
        arr.display();
        arr.insert(3, 99); // Задание 3
        arr.display();
        arr.mySortBubble();        // Задание 4 O(n^2)
        arr.display();
        arr.sortByCount();          // Задание 5 O(n)
        arr.display();
        arr.deleteAll();            // Задание 2, чтобы два раза не вставать )
        arr.display();
    }
}
