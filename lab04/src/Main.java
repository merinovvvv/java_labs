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

        System.out.println(tree.remove(5));
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
        tree2.insert(new Student("Vasya", 5));
        tree2.insert(new Student("Petya", 3));
        tree2.insert(new Student("Tolya", 4));
        tree2.insert(new Student("Kesha", 2));
        tree2.insert(new Student("Yaroslav", 10));
        tree2.inOrderTraversal();
        System.out.println();
        System.out.println();
        System.out.println(tree2);
    }
}
