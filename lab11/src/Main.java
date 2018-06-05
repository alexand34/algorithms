import java.util.List;

public class Main {

    public static void main(String[] args)
    {

        Node nLutsk=new Node("Lutsk");
        Node nKovel=new Node("Kovel");
        Node nRatne=new Node("Ratne");
        Node nKK=new Node("Kamin'-Kashyrs'kyi");
        Node nManevichy=new Node("Manevichy");
        Node nNovovolynsk=new Node("Novovolynsk");
        Node nKivertsy = new Node("Kivertsy");
        Node nLubeshiv = new Node("Lubeshiv");
        Node nGorohiv = new Node("Gorohiv");

        Graph g=new Graph();
        g.addNode(nLutsk);
        g.addNode(nKovel);
        g.addNode(nRatne);
        g.addNode(nKK);
        g.addNode(nManevichy);
        g.addNode(nNovovolynsk);
        g.addNode(nKivertsy);
        g.addNode(nLubeshiv);
        g.addNode(nGorohiv);

        g.setRootNode(nLutsk);

        g.connectNode(nLutsk,nKovel, 0.74);
        g.connectNode(nLutsk,nRatne, 1.24);
        g.connectNode(nLutsk, nKK, 1.26);
        g.connectNode(nLutsk,nManevichy, 0.76);
        g.connectNode(nLutsk,nNovovolynsk, 0.92);
        g.connectNode(nLutsk,nKivertsy, 0.15);
        g.connectNode(nLutsk,nLubeshiv, 1.36);
        g.connectNode(nLutsk,nGorohiv, 0.52);

        Node found_node = g.bfs_find("Lutsk");
        List<Tuple> distanceTuple= g.getDistanceBetweenNodes(found_node);

        for (Tuple node:
             distanceTuple) {
            System.out.println("Distance from " + found_node.label + " to " + node.EndPoint.label + " "
                    +  node.Disttance + "km");
        }
    }

}