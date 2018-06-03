public class Main
{
    public static void main(String[] args)
    {
        BTree tree = new BTree(5);

        tree.insert(tree,6);
        tree.insert(tree,25);
        tree.insert(tree,3);
        tree.insert(tree,17);
        tree.insert(tree,100);
        tree.insert(tree,58);
        tree.insert(tree,54);
        tree.insert(tree,50);
        tree.insert(tree,1);
        tree.insert(tree,30);
        tree.insert(tree,26);

        tree.print(tree.root);
        System.out.println();
        tree.deleteKey(tree,26);
        tree.deleteKey(tree,100);
        tree.deleteKey(tree,17);
        tree.deleteKey(tree,3);
        tree.print(tree.root);
    }
}