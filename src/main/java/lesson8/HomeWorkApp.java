package lesson8;

public class HomeWorkApp {

    public static class LinkedHashEntry {          //Связанный список - цепочка
        private int key;
        private int value;
        private LinkedHashEntry next;

        LinkedHashEntry(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public LinkedHashEntry getNext() {
            return next;
        }

        public void setNext(LinkedHashEntry next) {
            this.next = next;
        }
    }

    public static class HashTable {
        private final int TABLE_SIZE = 32;
        LinkedHashEntry[] table;

        HashTable() {
            table = new LinkedHashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < TABLE_SIZE; i++) {
                LinkedHashEntry entry = table[i];
                    while (entry != null) {              //Идем по цепочке
                        sb.append(entry.getValue() + "|");
                        entry = entry.getNext();        //Берем следующее значение цепочки
                        if (entry != null)              //Если там что-то есть, добавляем в строку
                            sb.append(", " + entry.getValue() );
                    }
                }
            return sb.toString();
        }

        private int hashFunc(int key){
            return key % TABLE_SIZE;
        }

        public int find(int key) {
            int hash = hashFunc(key);
            if (table[hash] == null)
                return -1;
            else {
                LinkedHashEntry entry = table[hash];
                while (entry != null && entry.getKey() != key)  //Идем по цепочке
                    entry = entry.getNext();
                if (entry == null)
                    return -1;
                else
                    return entry.getValue();
            }
        }

        public void insert(int key, int value) {
            int hash = hashFunc(key);
            if (table[hash] == null)
                table[hash] = new LinkedHashEntry(key, value);
            else {
                LinkedHashEntry entry = table[hash];
                while (entry.getNext() != null && entry.getKey() != key) {  //Идем по цепочке
                    entry = entry.getNext();
                    if (entry.getKey() == key)
                        entry.setValue(value);
                    else
                        entry.setNext(new LinkedHashEntry(key, value));     //Добавление в конец цепочки
                }
            }
        }

        public void delete(int key) {
            int hash = hashFunc(key);
            if (table[hash] != null) {
                LinkedHashEntry prevEntry = null;
                LinkedHashEntry entry = table[hash];
                while (entry.getNext() != null && entry.getKey() != key) {
                    prevEntry = entry;
                    entry = entry.getNext();
                }
                if (entry.getKey() == key) {
                    if (prevEntry == null)
                        table[hash] = entry.getNext();
                    else
                        prevEntry.setNext(entry.getNext());
                }
            }
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        String s;
        hashTable.insert(0, 111);
        hashTable.insert(1, 222);
        hashTable.insert(2, 333);
        hashTable.insert(3, 444);
        hashTable.insert(4, 555);
        hashTable.insert(1, 777);
        s = hashTable.toString();
        System.out.println(s);
    }
}

