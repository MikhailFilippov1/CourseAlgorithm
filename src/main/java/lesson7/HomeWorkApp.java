package lesson7;

class Queue {
    private int maxSize;
    private int[] queue;
    private int front;
    private int rear;
    private int items;

    public Queue(int s) {
        maxSize = s;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
        items = 0;
    }

    public void insert(int i) {
        if (rear == maxSize - 1)
            rear = -1;
        queue[++rear] = i;
        items++;
    }

    public int remove() {
        int temp = queue[front++];
        if (front == maxSize)
            front = 0;
        items--;
        return temp;
    }
    public int peek(){
        return queue[front];
    }

    public boolean isEmpty(){
        return (items==0);
    }

    public boolean isFull(){
        return (items==maxSize);
    }

    public int size(){
        return items;
    }

}

class Vertex{
    public char label;
    public boolean wasVisited;
    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTS = 32;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int size;
    //    private Stack stack;
    private Queue queue;

    public Graph() {
        queue = new Queue(MAX_VERTS);
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        size = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    private int getAdjUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMat[ver][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex].label);
    }

    public int bfs(char label) {
        int stepCount = 0;                          //Количество обходов
        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.insert(0); // Вставка в конец очереди
        int v2;
        while (!queue.isEmpty()) {
            stepCount++;
            int v1 = queue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;   // Пометка узла
                if (vertexList[v2].label == label) {
                    displayVertex(v2);              // Вывод искомого узла по достижении его
                    return stepCount;               // Возвращает количество обходов до момента обнаружения искомого узла
                }
                queue.insert(v2);
            }
        }
        for (int i = 0; i < size; i++)              // Сброс флагов
            vertexList[i].wasVisited = false;
        return stepCount;
    }

}




public class HomeWorkApp {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('I');
        graph.addVertex('J');
        graph.addVertex('K');
        graph.addEdge(0, 2); //AC
        graph.addEdge(2, 3); //CD
        graph.addEdge(2, 5); //CF
        graph.addEdge(1, 3); //BD
        graph.addEdge(1, 4); //BE
        graph.addEdge(3, 4); //DE
        graph.addEdge(0, 6); //DG
        graph.addEdge(4, 8); //EI
        graph.addEdge(6, 7); //GH
        graph.addEdge(5, 9); //FJ
        graph.addEdge(6, 9); //GJ
        graph.addEdge(9, 10); //JK
        graph.addEdge(8, 10); //IK
        System.out.println("Количество шагов: " + graph.bfs('F'));
    }


}
