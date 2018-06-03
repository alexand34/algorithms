public class BTree
{
    static int order;
    BNode root;
    public BTree(int order)
    {
        this.order = order;

        root = new BNode(order, null);

    }

    public BNode search(BNode root, int key)
    {
        int i = 0;

        while(i < root.count && key > root.key[i])//keep incrementing
        {
            i++;
        }
        if(i <= root.count && key == root.key[i])//obviously if key is in node
        {
            return root;
        }

        if(root.leaf)
        {
            return null ;
        }
        else
        {
            return search(root.getChild(i),key);
        }
    }

    public void split(BNode x, int i, BNode y)
    {
        BNode z = new BNode(order,null);
        z.leaf = y.leaf;//set boolean to same as y
        z.count = order - 1;//this is updated size
        for(int j = 0; j < order - 1; j++)
        {
            z.key[j] = y.key[j+order];
        }
        if(!y.leaf)
        {
            for(int k = 0; k < order; k++)
            {
                z.child[k] = y.child[k+order];
            }
        }
        y.count = order - 1;
        for(int j = x.count ; j> i ; j--)
        {
            x.child[j+1] = x.child[j];
        }
        x.child[i+1] = z;
        for(int j = x.count; j> i; j--)
        {
            x.key[j + 1] = x.key[j];
        }
        x.key[i] = y.key[order-1];
        y.key[order-1 ] = 0;
        for(int j = 0; j < order - 1; j++)
        {
            y.key[j + order] = 0;
        }
        x.count ++;
    }

    public void nonfullInsert(BNode x, int key)
    {
        int i = x.count;

        if(x.leaf)
        {
            while(i >= 1 && key < x.key[i-1])
            {
                x.key[i] = x.key[i-1];

                i--;
            }
            x.key[i] = key;
            x.count ++;
        }
        else
        {
            int j = 0;
            while(j < x.count  && key > x.key[j])
            {
                j++;
            }
            if(x.child[j].count == order*2 - 1)
            {
                split(x,j,x.child[j]);
                if(key > x.key[j])
                {
                    j++;
                }
            }
            nonfullInsert(x.child[j],key);//recurse
        }
    }

    public void insert(BTree t, int key)
    {
        BNode r = t.root;
        if(r.count == 2*order - 1)//if is full
        {
            BNode s = new BNode(order,null);
            t.root = s;
            s.leaf = false;
            s.count = 0;
            s.child[0] = r;
            split(s,0,r);
            nonfullInsert(s, key);
        }
        else
            nonfullInsert(r,key);
    }

    public void print(BNode n)
    {
        for(int i = 0; i < n.count; i++)
        {
            System.out.print(n.getValue(i)+" " );
        }
        if(!n.leaf)
        {
            for(int j = 0; j <= n.count  ; j++)
            {
                if(n.getChild(j) != null)
                {
                    System.out.println();
                    print(n.getChild(j));
                }
            }
        }
    }

    public void deleteKey(BTree t, int key)
    {
        BNode temp = new BNode(order,null);
        temp = search(t.root,key);

        if(temp.leaf && temp.count > order - 1)
        {
            int i = 0;
            while( key > temp.getValue(i))
            {
                i++;
            }
            for(int j = i; j < 2*order - 2; j++)
            {
                temp.key[j] = temp.getValue(j+1);
            }
            temp.count--;
        }
        else
        {
            System.out.println("This node is either not a leaf or has less than order - 1 keys.");
        }
    }
}