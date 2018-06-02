public class Node {
    int value;
    Node left;
    Node right;
    String key;
    Node(int value, String key) {
        this.value = value;
        right = null;
        left = null;
        this.key = key;
    }
}