import java.awt.color.ICC_ColorSpace;

public class BinarySearchTree <T extends Comparable<T>> {
    class Node {
        private T value;
        private Node left;
        private Node right;
        Node (T value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void printNode() {
            System.out.println("Chosen node has value: " + value);
        }

        @Override
        public String toString() {
            return "Node {\n" +
                    "value = " + value + "\n" +
                    "leftChild = " + left + "\n" +
                    "rightChild = " + right + "\n" +
                    "}";
        }
    }

    private Node root;
    BinarySearchTree() {root = null;}
    BinarySearchTree(T val) {root = new Node(val);}

    private int numberOfNodes (Node n) {
        if (n == null) {
            return 0;
        } else {
            return numberOfNodes(n.left) + numberOfNodes(n.right) + 1;
        }
    }

    public int numberOfNodes() {
        return numberOfNodes(root);
    }

    public T findValue(T val) {
        Node node = findNode(val);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node findNode(T val) {
        Node node = root;
        while (node != null) {
            int res = node.value.compareTo(val);
            if (res == 0) {
                return node;
            } else if (res < 0) {
                node = node.left;;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private void insertElemFunc(Node node, T valueToInsert) {
        node.value = valueToInsert;
        node.left = null;
        node.right = null;
    }

    public void insert(T valueToInsert) { //TODO Check if works
        if (root == null) {
            root = new Node(valueToInsert);
            return;
        }
        Node node = root;
        while (true) {
            if (valueToInsert.compareTo(node.value) < 0) {
                if (node.left.value == null) {
                    node = node.left;
                    insertElemFunc(node, valueToInsert);
                    break;
                }
                node = node.left;
            } else if (valueToInsert.compareTo(node.value) > 0) {
                if (node.right.value == null) {
                    node = node.right;
                    insertElemFunc(node, valueToInsert);
                    break;
                }
                node = node.right;
            } else if (valueToInsert.compareTo(node.value) == 0) {
                return;
            }
        }
    }

    public boolean remove(T valueToRemove) {
        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;
        while (currentNode.value.compareTo(valueToRemove) != 0) {
            parentNode = currentNode;
            if (currentNode.value.compareTo(valueToRemove) > 0) {
                isLeftChild = true;
                currentNode = currentNode.left;
            } else {
                isLeftChild = false;
                currentNode = currentNode.right;
            }
            if (currentNode == null) {
                return false;
            }
        }
        if (currentNode.left == null && currentNode.right == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (currentNode.right == null) {
            if (currentNode == root) {
                root = currentNode.left;
            } else if (isLeftChild) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }
        } else if (currentNode.left == null) {
            if (currentNode == root) {
                root = currentNode.right;
            } else if (isLeftChild) {
                parentNode.left = currentNode.right;
            } else {
                parentNode.right = currentNode.right;
            }
        } else {
            Node heir = getHair(currentNode);
            if (currentNode == root) {
                root = heir;
            } else if (isLeftChild) {
                parentNode.left = heir;
            } else  {
                parentNode.right = heir;
            }
        }
        return true;
    }

    private Node getHair(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.right;
        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.left;
        }
        if (heirNode != node.right) {
            parentNode.left = heirNode.right;
            heirNode.right = node.right;
        }
        return heirNode;
    }

    //TODO Tree traversals
    //TODO Override compareTo

    public static void main(String[] args) {

    }
}