import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    Node root;
    public int NodeValue = -222222;
    public void delete(int value) {
        deleteRecursive(root, value);
    }
    public void add(int value, String key) { root = addRecursive(root, value, key); }
    public boolean containsNode(int value) { return containsNodeRecursive(root, value); }
    public void getNodeValue(String key) throws Exception {  getNodeValueRecursive(root, root.value, key); }

    private Node addRecursive(Node current, int value, String key) {
        if (current == null) {
            return new Node(value, key);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value, key);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value, key);
        } else {
            return current;
        }
        return current;
    }

    public BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();
        bt.add(70, "qw");
        bt.add(90, "ED");
        bt.add(43, "vv");
        bt.add(19, "aa" );
        bt.add(37, "cc");
        bt.add(92, "qq");
        bt.add(85, "VQ");
        bt.add(107, "ZX");

        return bt;
    }

    private void getNodeValueRecursive(Node current, int value, String key) throws Exception {
        if (current != null) {
            if (key == current.key) {
                NodeValue = current.value;
            }
            if(value < current.value){
                getNodeValueRecursive(current.left, current.left == null ? 0 : current.left.value, key);
            }
            else {
                getNodeValueRecursive(current.right, current.right == null ? 0 : current.right.value, key);
            }
        }
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }


    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.value);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right!= null) {
                nodes.add(node.right);
            }
        }
    }
}