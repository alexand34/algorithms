import java.util.*;


public class Graph
{
    public Node rootNode;
    public ArrayList nodes=new ArrayList();
    public double[][] adjMatrix;
    int size;
    public void setRootNode(Node n)
    {
        this.rootNode=n;
    }

    public Node getRootNode()
    {
        return this.rootNode;
    }

    public void addNode(Node n)
    {
        nodes.add(n);
    }

    private double getDistanceInKm(double distanceInSm)
    {
        return distanceInSm*100;
    }


    public void connectNode(Node start,Node end, double distanceInSm)
    {
        if(adjMatrix==null)
        {
            size=nodes.size();
            adjMatrix=new double[size][size];
        }

        int startIndex=nodes.indexOf(start);
        int endIndex=nodes.indexOf(end);
        adjMatrix[startIndex][endIndex]=getDistanceInKm(distanceInSm);
        adjMatrix[endIndex][startIndex]=getDistanceInKm(distanceInSm);
    }

    private Tuple getUnvisitedChildNode(Node n)
    {

        int index=nodes.indexOf(n);
        int j=0;
        while(j<size)
        {
            if(adjMatrix[index][j]!=0 && ((Node)nodes.get(j)).visited==false)
            {
                return new Tuple(((Node) nodes.get(j)), adjMatrix[index][j]);
            }
            j++;
        }
        return null;
    }

    public Node bfs_find(String search_label)
    {

        Queue q=new LinkedList();
        q.add(this.rootNode);
        if(this.rootNode.label == search_label) {
            return this.rootNode;
        }
        rootNode.visited=true;
        while(!q.isEmpty())
        {
            Node n=(Node)q.remove();
            Node child=null;
            while((child=getUnvisitedChildNode(n).EndPoint)!=null)
            {
                child.visited=true;
                if(child.label == search_label){
                    return child;
                }
                q.add(child);
            }
        }
        clearNodes();
        return null;
    }

    public List<Tuple> getDistanceBetweenNodes(Node node)
    {
        List<Tuple> distances = new ArrayList<Tuple>();

        int nodeId = nodes.indexOf(node);

        for(int j=0; j<adjMatrix.length; j++)
        {
            distances.add(new Tuple((Node) nodes.get(j), adjMatrix[nodeId][j]));
        }

        return distances;
    }

    private void clearNodes()
    {
        int i=0;
        while(i<size)
        {
            Node n=(Node)nodes.get(i);
            n.visited=false;
            i++;
        }
    }
}