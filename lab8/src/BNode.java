public class BNode
{
    static int t;
    int count;
    int key[];
    BNode child[];
    boolean leaf;
    BNode parent;

    public BNode(int t, BNode parent)
    {
        this.t = t;
        this.parent = parent;
        key = new int[2*t - 1];
        child = new BNode[2*t];
        leaf = true;
        count = 0;
    }

    public int getValue(int index)
    {
        return key[index];
    }

    public BNode getChild(int index)
    {
        return child[index];
    }
}