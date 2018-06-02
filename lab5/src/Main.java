public class Main {

    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();

        tree = tree.createBinaryTree();
        BTreePrinter.printNode(tree.root);
        System.out.println();
        tree.getNodeValue("ED");
        System.out.println("Node to delete: " + tree.NodeValue);
        tree.delete(tree.NodeValue);
        BTreePrinter.printNode(tree.root);
    }
}
