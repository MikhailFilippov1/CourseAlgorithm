package lesson6;

import java.util.*;

public class HomeWork6 {

/*        private static class Example {
            static int num;
            
            public Example(int num) {
                this.num = num;
            }
           
            
            public int getNum() {
                return num;
            }
            public void setNum(int num) {
                Example.num = num;
            }
            @Override public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Example ex = (Example) o;
                return num == Example.num; 
            }
            @Override public int hashCode() {
                return Objects.hash(num);
            }
            @Override public String toString() {
                return String.format("Node value ( %d)", num);
            }
        }*/

        public static class Tree {
            public static class TreeNode {
                private int ex;
                public TreeNode leftChild;
                public TreeNode rightChild;

                public TreeNode(int ex) {
                    this.ex = ex;
                }

                @Override
                public String toString() {
                    return String.format("TN(%d)", ex);
                }
            }

            private TreeNode root;

            public Tree() {
                root = null;
            }
            
            public void insert(int ex) {
                TreeNode node = new TreeNode(ex);
                if (root == null) {
                    root = node;
                } else {
                    TreeNode current = root;
                    TreeNode parent;
                    while (true) {
                        parent = current;
                        if (ex < current.ex) {
                            current = current.leftChild;
                            if (current == null) {
                                parent.leftChild = node;
                                return;
                            }
                        } else if (ex > current.ex){
                            current = current.rightChild;
                            if (current == null) {
                                parent.rightChild = node;
                                return;
                            }
                        } else {
                            return;
                        }
                    }

                }
            }

            public int find(int ex) {
                TreeNode current = root;
                while (current.ex != ex) {
                    if (ex < current.ex)
                        current = current.leftChild;
                    else
                        current = current.rightChild;

                    if (current == null)
                        return 0;
                }
                return current.ex;
            }

            private void inOrderTravers(TreeNode current) {
                if (current != null) {
                    System.out.println(current);
                    inOrderTravers(current.leftChild);
                    inOrderTravers(current.rightChild);
                }
            }
            public void displayTree() {
                inOrderTravers(root);
            }

 /*           public boolean delete(int age) {
                TreeNode curr = root;
                TreeNode prev = root;
                boolean isLeftChild = true;
                while (curr.cat.age != age) {
                    prev = curr;
                    if (age < curr.cat.age) {
                        isLeftChild = true;
                        curr = curr.leftChild;
                    } else {
                        isLeftChild = false;
                        curr = curr.rightChild;
                    }

                    if (curr == null)
                        return false;
                }

                if (curr.leftChild == null && curr.rightChild == null) {
                    if (curr == root) {
                        root = null;
                    } else if (isLeftChild) {
                        prev.leftChild = null;
                    } else {
                        prev.rightChild = null;
                    }
                } else if (curr.rightChild == null) {
                    if (isLeftChild) {
                        prev.leftChild = curr.leftChild;
                    } else {
                        prev.rightChild = curr.leftChild;
                    }
                } else if (curr.leftChild == null) {
                    if (isLeftChild) {
                        prev.leftChild = curr.rightChild;
                    } else {
                        prev.rightChild = curr.rightChild;
                    }
                } else {
                    TreeNode successor = getSuccessor(curr);
                    if (curr == root) {
                        root = successor;
                    } else if (isLeftChild) {
                        prev.leftChild = successor;
                    } else {
                        prev.rightChild = successor;
                    }
                    successor.leftChild = curr.leftChild;
                }
                return true;
            }*/

 /*           private TreeNode getSuccessor(TreeNode deleted) {
                TreeNode successorParent = deleted;
                TreeNode successor = deleted;
                TreeNode flag = deleted.rightChild;

                while (flag != null) {
                    successorParent = successor;
                    successor = flag;
                    flag = flag.leftChild;
                }
                if (successor != deleted.rightChild) {
                    successorParent.leftChild = successor.rightChild;
                    successor.rightChild = deleted.rightChild;
                }
                return successor;
            }*/
        private boolean isTreeBalanced(TreeNode root) {
            if (root == null)
                    return true;
            Vector<TreeNode> queue = new Vector<>();
            int level = 1;
            int minLevel = Integer.MAX_VALUE;
            int maxLevel = Integer.MIN_VALUE;
            queue.add(root);
            while (!queue.isEmpty()) {
                int elementCount = queue.size();
                while (elementCount > 0) {
                    TreeNode node = queue.remove(0);
                    if (isLeaf(node)) {
                        if (minLevel > level)minLevel = level;
                        if (maxLevel < level)maxLevel = level;
                        } else {
                        if (node.leftChild != null)queue.add(node.leftChild);
                        if (node.rightChild != null)queue.add(node.rightChild);
                        }
                        elementCount--;
                    }
                if (Math.abs(maxLevel - minLevel) > 1) {
                    return false;
                }
                level++;
            }
            return true;
        }

            private boolean isLeaf(TreeNode root) {             // Проверка на окончание ветки
                if (root.leftChild == null && root.rightChild == null)
                    return true;
                return false;
            }
    }



    public static void main(String[] args) {
//        int count = 0;
        int balanced = 0;
        int disbalanced = 0;
        Tree tree = new Tree();

        Integer[] arr = new Integer[201];

        for (int i = 0; i < arr.length; i++) {      //Заполняем массив уникальными числами, ибо идут по порядку
            if(i < arr.length/2 + 1)arr[i] = -i;    //Вы говорили, что каждый понимает ТЗ в силу своей испорченности )))
            else arr[i] = arr.length - i;
        }

        for (int i = 0; i < 200; i++) {                       // цикл на 200 деревьев
                tree.root = null;
                Collections.shuffle(Arrays.asList(arr));    //Как бэ, случайные числа генерит
//                System.out.println(Arrays.toString(arr));

            for (int j = 0; j < 100; j++) {
                tree.insert(arr[j]);
//                count++;
            }
            if(tree.isTreeBalanced(tree.root))balanced++;
            else disbalanced++;
    }
//            tree.displayTree();
//            System.out.println("Count = " + count);
        System.out.println("Count of balanced tree = " + balanced);
        System.out.println("Count of disbalanced tree = " + disbalanced);
        }

}
