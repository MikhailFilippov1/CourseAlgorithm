package lesson2;

public class MyArray {

        private int[] arr;
        private int capacity;

        public MyArray(int[] init) {
            this.capacity = init.length;
            this.arr = init;
        }

        void display() {
            for (int i = 0; i < this.capacity; ++i) {
                System.out.print(this.arr[i] + " ");
            }
            System.out.println();
        }

        public int get(int idx) {
            return arr[idx];
        }

        public void set(int value, int idx) {
            arr[idx] = value;
        }


    boolean deleteAll(int value) {              //Удаление из массива по индексу
            int count = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                count++;
            }
        }
        System.out.printf("Deleted number %d > %d times\n", value, count);
        if(count !=0)return true;
        else return false;
    }

    boolean deleteAll(){            // Если я правльно понял задание, конечно. Удаление всего массива
        this.arr = new int[0];
        capacity = 0;
        return true;
    }


    void insert(int idx, int value){
            int[] tempArr = this.arr;
            this.arr = new  int[capacity + 1];
            System.arraycopy(tempArr, 0, arr, 0, idx);
            arr[idx] = value;
            System.arraycopy(tempArr, idx, arr, idx + 1, capacity - idx);
            capacity++;
        }

        private void swap(int a, int b) {
            int tmp = this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }

    public void mySortBubble() {        // Не проходим внешний цикл обязательно, а только если был swap
        boolean moreCycle = true;       // Сложность О(n^2) все равно в худшем случае
        while (moreCycle) {
            moreCycle = false;
            for (int idx = 1; idx < capacity; idx++) {
                if (this.arr[idx] > this.arr[idx - 1]) {        // Верх сосуда справа
                    swap(idx, idx - 1);
                    moreCycle = true;
                }
            }
        }
    }

        public void sortByCount(){      // Алгоритм выгоден, когда очень большой массив, но небольшой диапазон сортируемых чисел
            int MAX_VALUE = 100;        // При большом диапазоне сортируемых чисел, потребляет очень много памяти
            int[] tempArr = new int[MAX_VALUE]; // Не сортирует строки, символы
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                tempArr[arr[i]] = tempArr[arr[i]] + 1;
            }
            for (int i = 0; i < tempArr.length; i++) {
                for (int j = 0; j < tempArr[i]; j++) {
                    arr[count] = i;
                    count++;
                }
            }
        }
}

