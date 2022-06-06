package lesson3;

public class Main {

    private static class Stack {
        private int maxSize;
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.maxSize = size;
            this.stack = new int[this.maxSize];
            this.head = -1;
        }

        public boolean isEmpty() { return this.head == -1; }
        public boolean isFull() { return this.head == this.maxSize - 1; }

        public void push(int i) {
            if (isFull()) {
                this.maxSize *= 2;
                int[] newStack = new int[this.maxSize];
                System.arraycopy(this.stack, 0, newStack, 0, this.stack.length);
                this.stack = newStack;
            }
            this.stack[++this.head] = i;
        }

        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty"); //ret -1
            return this.stack[this.head--];
        }

        public int peek() {
            return this.stack[this.head];
        }
    }


    public static class Deck {

        private int maxSize;
        private int[] deck;
        private int headLeft;
        private int headRight;
        private int tail;
        private int items;

        public Deck(int s) {
            maxSize = s;
            deck = new int[maxSize];
            headLeft = 0;
            headRight = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        public void insertLeft(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= headLeft) {
                    System.arraycopy(deck, 0, tmpArr, 0, deck.length);
                } else {
                    System.arraycopy(deck, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(deck, headLeft, tmpArr,
                            maxSize - (deck.length - headLeft), deck.length - headLeft);
                    headLeft = maxSize - headLeft - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            deck[++tail] = i;
            ++items;
        }

        public void insertRight(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail <= headRight) {
                    System.arraycopy(deck, 0, tmpArr, 0, deck.length);
                } else {
                    System.arraycopy(deck, 0, tmpArr, 0, tail - 1);
                    System.arraycopy(deck, headRight, tmpArr,
                            maxSize - (deck.length - headRight), deck.length - headRight);
                    headRight = maxSize - headRight + 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            deck[++tail] = i;
            ++items;
            headRight = items - 1;      // HeadRight присвоили номер конца очереди справа
        }

        public int removeLeft() {
            int temp =  deck[headLeft++];
            headLeft %= maxSize;
            items--;
            return temp;
        }

        public int removeRight() {
            int temp =  deck[headRight++];
            headRight %= maxSize;
            items--;
            return temp;
        }

        public int peekLeft(){
            return deck[headLeft];
        }

        public int peekRight(){
            return deck[headRight];
        }
    }

    public static class PriorityQueue {     //Метод упорядочивает массив сразу при вставке
        private int maxSize;
        private int[] priorityQueue;
        private int head;
        private int tail;
        private int items;

        public PriorityQueue(int s) {
            maxSize = s;
            priorityQueue = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        public int remove(){
            if(isEmpty()) throw new RuntimeException("PriorityQueue is Empty");

            int temp = priorityQueue[0];
            System.arraycopy(priorityQueue, 1, priorityQueue, 0, priorityQueue.length - 1);
            maxSize--;
            return temp;
        }

        public void insert(int value){
            if(isFull()) throw new RuntimeException("PriorityQueue is full");

            int i;
            for (i = 0; i < priorityQueue.length; i++) {
                if(priorityQueue[i] < value) break;
            }
            System.arraycopy(priorityQueue, i, priorityQueue, i + 1, size() - i);
            priorityQueue[i] = value;
            items++;
        }
    }

    public static int checkBracket(String example){             //Проверка скобочной последовательности
        int bracketsTypes = 4;
        char brackets[][] = {{'(',')'}, {'[',']'}, {'{','}'}, {'<','>'}};
        Stack stack = new Stack(10);
        int exampleLength = example.length();

        for (int i = 0; i < exampleLength; i++) {
            for (int j = 0; j < bracketsTypes; ++j) {
                if(example.charAt(i) == brackets[j][0])
                    stack.push(j);                      // Запомнили индекс левой скобки в стеке
            }
            for (int j = 0; j < bracketsTypes; ++j) {
                if(example.charAt(i) == brackets[j][1]){
                    if(stack.isEmpty()) return i;       // Возвращаем позицию ошибочной скобки
                    if(j == stack.peek()) stack.pop();  // Индекс левой скобки соответствует содержимому стека(правой скобке)
                    else return i;
                }
            }
        }
        if(!stack.isEmpty())
            return stack.pop();
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Задание №1");
        Deck deck = new Deck(10);
        deck.insertLeft(1);
        deck.insertLeft(99);
        deck.insertRight(7);
        System.out.println(deck.removeLeft());
        System.out.println(deck.removeLeft());
        System.out.println(deck.removeRight());

        System.out.println("Задание№2");
        PriorityQueue priorityQueue = new PriorityQueue(10);
        priorityQueue.insert(5);
        priorityQueue.insert(99);
        priorityQueue.insert(1);
        priorityQueue.insert(3);
        priorityQueue.insert(77);
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.remove());

        System.out.println("Задание №3");
        System.out.println(checkBracket("{}()[]<>"));
        System.out.println(checkBracket("<>{})[]()"));
    }
}
