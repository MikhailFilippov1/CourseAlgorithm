package lesson4;

public class Main {

    static class Link{
        public String name;
        public int age;
        public Link next;
        public Link previous;

        public Link(String name, int age){
            this.name = name;
            this.age = age;
        }

        public void display(){
            System.out.println("Name: "+this.name+", age: "+this.age);
        }

        @Override
        public String toString() {
            return "Find>" +
                    "name='" + name + '\'' +
                    ", age=" + age
                    ;
        }
    }

    static class LinkedList{
        private Link first;
        public Link last;

        public LinkedList(){
            first = null;
            last = null;
        }

        public Link getFirst() {
            return first;
        }

        public void setFirst(Link first) {
            this.first = first;
        }

        public LinkIterator getIterator(LinkedList list){
            return new LinkIterator(this);
        }

        public boolean isEmpty(){
            return (first == null);
        }

        public void insert(String name, int age){
            Link newLink = new Link(name, age);
                if(this.isEmpty())
                    last = newLink;
                newLink.next = first;
//                newLink.previous = last;
//                last = first;
                first = newLink;
        }

        public void insertLast(String name, int age){   //Вставка перед первым в очереди
            Link newLink = new Link(name, age);
                if(this.isEmpty())
                    first = newLink;
                else
                    last.next = newLink;
            last = newLink;
        }

        public Link deleteLast(){               //Удаление из конца очереди
            Link temp = first;
                if (first.next == null)
                    last = null;
            first.previous = null;
            first = first.next;
            return temp;
        }

        public Link deleteFirst(){               //Удаление из начала очереди НЕ РАБОТАЕТ!!!
            Link temp = last;
            Link prev = last;
            if (last.next == null)
                last = null;
            last = first.previous;
            return temp;
        }

        public void display(){
            Link current  = first;
            while (current != null){
                current.display();
                current = current.next;
            }
        }

        public Link find(String name){
            Link current = first;
            while (current.name != name){
                if(current.next == null)
                    return null;
                else
                    current = current.next;
            }
            return  current;
        }

        public Link delete(String name){    //Удаление по имени
            Link current = first;
            Link previous = first;
            while (current.name != name){
                if(current.next == null)
                    return null;
                else {
                    previous = current;
                    current = current.next;
                }
            }
            if(current == first)
                first = first.next;
            else
                previous.next = current.next;
            return current;
        }
    }

    static class LinkIterator{
        private Link current;
        private Link previous;
        private LinkedList list;

        private LinkIterator(LinkedList list){
            this.list = list;
            this.reset();
        }

        public void reset(){
            current = list.getFirst();
            previous = null;
        }

        public boolean atEnd(){
            return (current.next == null);
        }

        public void nextLink(){
            previous = current;
            current = current.next;
        }

        public Link getCurrent(){
            return current;
        }

        public void insertAfter(String name, int age){
            Link newLink = new Link(name, age);
            if (list.isEmpty()){
                list.setFirst(newLink);
                current = newLink;
            } else {
                newLink.next = current.next;
                current.next = newLink;
                nextLink();
            }
        }

        public void insertBefore(String name, int age){
            Link newLink = new Link(name, age);
            if(previous == null){
                newLink.next = list.getFirst();
                list.setFirst(newLink);
                reset();
            }
            else{
                newLink.next = previous.next;
                previous.next = newLink;
                current = newLink;
            }
        }

        public String deleteCurrent(){
            String name = current.name;
            if (previous == null){
                list.setFirst(current.next);
                reset();
            } else {
                previous.next = current.next;
                if (atEnd()){
                    reset();
                } else {
                    current = current.next;
                }
            }
            return name;
        }


    }

        public static void main(String[] args) {
            LinkedList list = new LinkedList();
            LinkedList listForIterator = new LinkedList();
            LinkIterator iterator = new LinkIterator(listForIterator);

            list.insert("Artem first", 30);
            list.insert("Misha second", 10);
            list.insert("Vova third", 5);
            list.insert("Petr fourth", 99);
            list.insertLast("Artem наглец", 25);

            list.display();

            System.out.println("Удаление из конца очереди");    // должен быть удален Петр и Вова
            list.deleteLast();
            list.deleteLast();
            list.display();
            System.out.println("Удаление из начала очереди"); // должен быть удален Артем наглец
            list.deleteFirst();
            list.display();
            System.out.println("Удаление из очереди по имени"); // должен быть удален Артем наглец
            list.delete("Artem наглец");
            list.display();
            System.out.println(list.find("Artem first").toString());

            System.out.println("\nРабота с итератором");
            iterator.insertAfter("First", 1000);
            iterator.insertAfter("Second", 1000);
            iterator.insertAfter("Third", 1000);
            iterator.insertAfter("Fourth", 1000);
            iterator.insertBefore("Before Fourth", 999);
            listForIterator.display();
            iterator.reset();
            System.out.println(iterator.getCurrent().toString());
            System.out.println("Deleted>" + iterator.deleteCurrent());
            listForIterator.display();
        }
}
