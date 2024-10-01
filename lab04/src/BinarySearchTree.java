public class BinarySearchTree <T extends Comparable<T>> {
    class Node {
        private final T value;
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

    public boolean findValue(T val) {
        return findNode(val);
    }

    private boolean findNode(T val) {
        Node node = root;
        while (node != null) {
            int res = node.value.compareTo(val);
            if (res == 0) {
                return true;
            } else if (res < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

    public void insert(T valueToInsert) {
        if (root == null) {
            root = new Node(valueToInsert);
            return;
        }
        Node node = root;
        while (true) {
            if (valueToInsert.compareTo(node.value) < 0) {
                if (node.left == null) {
                    node.left = new Node(valueToInsert);
                    break;
                }
                node = node.left;
            } else if (valueToInsert.compareTo(node.value) > 0) {
                if (node.right == null) {
                    node.right = new Node(valueToInsert);
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

    public void preOrderTraversal() { //Вершина-Левый-Правый
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal() { //Левый-Вершина-Правый
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.print(node.value + " ");
        inOrderTraversal(node.right);
    }

    public void postOrderTraversal() { //Левый-Правый-Вершина
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value + " ");
    }

    private String BinarySearchTreeString(Node node, String prefix, boolean isLeft) {
        StringBuilder builder = new StringBuilder();
        if (node != null) {
            if (node != root) {
                builder.append(prefix);
                builder.append(isLeft ? "├── " : "└── ");
                builder.append(node.value).append("\n");
                builder.append(BinarySearchTreeString(node.left, prefix + (isLeft ? "│   " : "    "), true));
                builder.append(BinarySearchTreeString(node.right, prefix + (isLeft ? "│   " : "    "), false));
            } else {
                builder.append(node.value).append("\n");
                builder.append(BinarySearchTreeString(node.left, prefix, true));
                builder.append(BinarySearchTreeString(node.right, prefix, false));
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Tree is empty";
        }
        return BinarySearchTreeString(root, "", false);
    }
}