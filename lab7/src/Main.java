public class Main {

    public static void main(String[] args)
    {
        TwoTheeTree tree = new TwoTheeTree();

        tree.add(6);
        tree.add(25);
        tree.add(3);
        tree.add(17);
        tree.add(100);
        tree.add(58);
        tree.add(54);
        tree.add(50);
        tree.add(1);
        tree.add(30);
        tree.add(26);

        System.out.println("Original tree in order:");
        tree.inOrder();

        tree.remove(26);
        tree.remove(100);
        tree.remove(17);
        tree.remove(3);

        System.out.println("After deleting nodes: 26, 100, 17, 3");
        tree.inOrder();
    }
}
