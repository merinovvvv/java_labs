public class Main {
    public static void main(String[] args) throws MyException {
        //BinarySearchTree with ints
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(8);
        tree.insert(16);
        tree.insert(8);
        tree.insert(2);
        tree.insert(9);
        tree.insert(7);
        tree.insert(4);
        tree.insert(10);
        tree.insert(3);
        tree.insert(1);

        //tree.printBinarySearchTree();
        tree.inOrderTraversal();
        System.out.println();

        tree.remove(5);
        //tree.printBinarySearchTree();
        tree.inOrderTraversal();
        System.out.println();

        boolean isFoundValue = tree.findValue(7);
        System.out.println(isFoundValue);

        tree.insert(5);
        tree.inOrderTraversal();
        System.out.println();
        tree.postOrderTraversal();
        System.out.println();
        tree.preOrderTraversal();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(tree);
        System.out.println();

        //BinarySearchTree with Students
        BinarySearchTree<Student> tree2 = new BinarySearchTree<>();

        Student vasya = new Student("Vasya", 5);
        Student petya = new Student("Petya", 3);
        Student tolya = new Student("Tolya", 4);
        Student kesha = new Student("Kesha", 2);
        Student maks = new Student("Maks", 9);
        Student yaroslav = new Student("Yaroslav", 10);
        tree2.insert(vasya);
        tree2.insert(petya);
        tree2.insert(tolya);
        tree2.insert(kesha);
        tree2.insert(maks);
        tree2.insert(yaroslav);
        tree2.inOrderTraversal();
        System.out.println();
        System.out.println();
        System.out.println(tree2);

        tree2.remove(petya);
        System.out.println();
        System.out.println();
        System.out.println(tree2);
        System.out.println();
        System.out.println();
        System.out.println();

        //Another BinarySearchTree with ints

        BinarySearchTree<Integer> tree3 = new BinarySearchTree<>(6);
        tree3.insert(8);
        tree3.insert(16);
        tree3.insert(8);
        tree3.insert(2);
        tree3.insert(9);
        tree3.insert(7);
        tree3.insert(4);
        tree3.insert(10);
        tree3.insert(3);
        tree3.insert(1);
        tree3.inOrderTraversal();
        System.out.println();
        System.out.println(tree3);
        tree3.remove(6);
        tree3.inOrderTraversal();
        System.out.println();
        System.out.println(tree3);
        tree3.remove(2);
        System.out.println();
        System.out.println(tree3);

        tree3.remove(8);
        System.out.println();
        System.out.println(tree3);
        System.out.println();

        tree3.inOrderTraversal();
        System.out.println();
        tree3.postOrderTraversal();
        System.out.println();
        tree3.preOrderTraversal();


        //empty BinarySearchTree
        System.out.println();
        BinarySearchTree<Integer> tree4 = new BinarySearchTree<>();
        System.out.println(tree4);
        tree4.preOrderTraversal();
        tree4.remove(2);
        System.out.println(tree4);

        //Another BinarySearchTree
        BinarySearchTree<Integer> tree5 = new BinarySearchTree<>();
        tree5.insert(52);
        tree5.insert(50);
        tree5.insert(51);
        tree5.insert(104);
        tree5.insert(100);
        tree5.insert(208);
        tree5.insert(200);
        tree5.insert(198);
        System.out.println();
        System.out.println(tree5);
        System.out.println();
        tree5.remove(104);
        System.out.println(tree5);
    }
}
