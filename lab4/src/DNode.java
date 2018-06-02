public class DNode {

    static int noOfDLinkedList = 0;
    int data;
    DNode nextDNode;
    DNode previousDNode;

    DNode(int data){

        this.data = data;
        noOfDLinkedList++;

    }

}